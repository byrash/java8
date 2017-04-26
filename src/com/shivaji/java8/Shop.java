package com.shivaji.java8;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {
	private static final Random random = new Random();
	private final String shopName;

	public Shop(String shopName) {
		super();
		this.shopName = shopName;
	}

	public Future<Double> getPriceAsync(String product) {
		// CompletableFuture<Double> completableFuture = new
		// CompletableFuture<>();
		// new Thread(() -> {
		// try {
		// double price = calculatePrice(product);
		// completableFuture.complete(price);
		// } catch (Exception e) {
		// completableFuture.completeExceptionally(e);
		// }
		// }).start();
		return CompletableFuture.supplyAsync(() -> calculatePrice(product));
	}

	// public double getPrice(String product) {
	// return calculatePrice(product);
	// }
	//
	// private double calculatePrice(String product) {
	// delay();
	// return random.nextDouble() * product.charAt(0) + product.charAt(1);
	// }

	// public static void delay() {
	// try {
	// Thread.sleep(1000L);
	// } catch (InterruptedException e) {
	// throw new RuntimeException(e);
	// }
	// }

	public String getShopName() {
		return shopName;
	}

	public String getPrice(String product) {
		double calculatePrice = calculatePrice(product);
		Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
		return String.format("%s:%.2f:%s", this.shopName, calculatePrice, code);
	}

	private double calculatePrice(String product) {
		delay();
		return random.nextDouble() * product.charAt(0) + product.charAt(1);
	}

	public static void delay() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void randomDelay() {
		int dealy = 500 + random.nextInt(2000);
		try {
			Thread.sleep(dealy);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
