package com.shivaji.java8;

import com.shivaji.java8.Discount.Code;

public class Quote {

	private final String shopName;
	private final Double price;
	private final Discount.Code discountCode;

	private Quote(String shopName, Double price, Code discountCode) {
		super();
		this.shopName = shopName;
		this.price = price;
		this.discountCode = discountCode;
	}

	public static Quote parse(String str) {
		String[] split = str.split(":");
		return new Quote(split[0], Double.parseDouble(split[1]), Discount.Code.valueOf(split[2]));
	}

	public String getShopName() {
		return shopName;
	}

	public Double getPrice() {
		return price;
	}

	public Discount.Code getDiscountCode() {
		return discountCode;
	}

}
