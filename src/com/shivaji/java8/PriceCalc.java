package com.shivaji.java8;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PriceCalc {
	private static final List<Shop> shops = Arrays.asList(new Shop("Shivaji"), new Shop("Aswath"), new Shop("Deepthi"),
			new Shop("My Dad"), new Shop("My Dad 1"), new Shop("My Dad 2"), new Shop("My Dad 3"), new Shop("My Dad 4"));
	private static final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), (Runnable r) -> {
		Thread t = new Thread(r);
		t.setDaemon(true);
		return t;
	});

	public static void main(String[] args) {
		System.out.println("Sequential Took " + getExecutionTime("Boss", PriceCalc::findPrices));
		System.out.println("Parallel Stream Took " + getExecutionTime("Boss", PriceCalc::findPricesUsingParalleStream));
		System.out.println(
				"Completable Future Took " + getExecutionTime("Boss", PriceCalc::findPricesUsingCompletableFuture));
	}

	public static double getExecutionTime(String productName, Function<String, List<String>> function) {
		long start = System.nanoTime();
		function.apply(productName);
		// .stream()
		// .forEach(System.out::print);
		long end = System.nanoTime();
		return (end - start) / 1_000_000;
	}

	public static List<String> findPrices(String productName) {
		return shops.stream()
				.map(shop -> shop.getPrice(productName))
				.map(Quote::parse)
				.map(Discount::applyDiscount)
				.collect(Collectors.toList());
	}

	public static List<String> findPricesUsingParalleStream(String productName) {
		return shops.parallelStream()
				.map(shop -> shop.getPrice(productName))
				.map(Quote::parse)
				.map(Discount::applyDiscount)
				.collect(Collectors.toList());
	}

	public static List<String> findPricesUsingCompletableFuture(String productName) {
		List<CompletableFuture<String>> priceFutures = shops.stream()
				.map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(productName), executor))
				.map(future -> future.thenApply(Quote::parse))
				.map(future -> future.thenCompose(
						quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
				.collect(Collectors.toList());
		return priceFutures.stream()
				.map(CompletableFuture::join)
				.collect(Collectors.toList());
		// .map(Quote::parse)
		// .map(Discount::applyDiscount)
		// .collect(Collectors.toList());
	}

}
