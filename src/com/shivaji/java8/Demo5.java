package com.shivaji.java8;

import java.util.stream.IntStream;

public class Demo5 {

	public static void main(String[] args) {

		IntStream.rangeClosed(1, 100)
				.boxed()
				.flatMap(a -> IntStream.rangeClosed(a, 100)
						.filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
						.mapToObj(b -> new int[] { a, b, (int) Math.sqrt(a * a + b * b) }))
				.forEach((val) -> System.out.println("( " + val[0] + ", " + val[1] + " ," + val[2] + " )"));
		;

	}

}
