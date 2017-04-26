package com.shivaji.java8;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class BestPriceClient {

	private static final List<Shop> shops = Arrays.asList(new Shop("Shivaji"), new Shop("Aswath"), new Shop("Deepthi"),
			new Shop("My Dad"));

	// public static void main(String[] args) {
	// Shop shop1 = new Shop("Shivaji");
	// long start = System.nanoTime();
	// Future<Double> priceAsync = shop1.getPriceAsync("Aswath");
	// System.out.println("Invocation returned after " + (System.nanoTime() -
	// start) / 1_000_000 + " msecs");
	// doSomethingElse();
	// try {
	// Double price = priceAsync.get();
	// System.out.println("Price is " + price);
	// } catch (InterruptedException | ExecutionException e) {
	// e.printStackTrace();
	// }
	// System.out.println("Price retruned after - " + (System.nanoTime() -
	// start) / 1_000_000 + " msecs");
	// }

	public static void main(String[] args) {
		long start = System.nanoTime();
		System.out.println(getPrices("IPhone27S"));
		System.out.println((System.nanoTime() - start) / 1_000_000 + " msecs");
	}

	private static void doSomethingElse() {
		System.out.println("Doing soemthing else");
	}

	public static List<String> getPrices(final String product) {
		List<CompletableFuture<String>> collect = shops.stream()
				.map(shop -> CompletableFuture.supplyAsync(() -> shop.getShopName() + " " + shop.getPrice(product),
						executor))
				.collect(Collectors.toList());
		return collect.stream()
				.map(CompletableFuture::join)
				.collect(Collectors.toList());
		// return shops.parallelStream()
		// .flatmap(s -> CompletableFuture.supplyAsync(s.getShopName() + " " +
		// s.getPrice(product))).
		// .collect(Collectors.toList());
	}

	private static final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), (Runnable r) -> {
		Thread t = new Thread(r);
		t.setDaemon(true);
		return t;
	});
}
