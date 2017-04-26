package com.shivaji.java8;

public class Discount {

	public enum Code {
		NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
		private final int percentage;

		private Code(int percentage) {
			this.percentage = percentage;
		}
	}

	public static String applyDiscount(Quote quote) {
		return quote.getShopName() + " price is "
				+ String.format("%.2f", apply(quote.getPrice(), quote.getDiscountCode()));
	}

	private static double apply(double price, Code code) {
		Shop.delay();
		return price * (100 - code.percentage) / 100;
	}

}
