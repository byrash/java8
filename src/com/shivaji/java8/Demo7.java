package com.shivaji.java8;

import java.util.stream.Stream;

public class Demo7 {

	public static void main(String[] args) {
		Stream.iterate(new int[] { 0, 1 }, n -> new int[] { n[1], n[0] + n[1] })
				.limit(20)
				.map(i -> i[0] + " ")
				.forEach(System.out::print);
		;
	}
}
