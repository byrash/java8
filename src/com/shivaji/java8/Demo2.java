package com.shivaji.java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Demo2 {
	private static final List<Food> inventory = new ArrayList<>();

	public static void main(String[] args) {
		System.out.println("Inventory Size -->" + inventory.size());
		long start = System.nanoTime();
		List<Food> collect = inventory.stream().sorted(Comparator.comparing(Food::getCalories))
				.collect(Collectors.toList());
		long end = System.nanoTime();
		long regular = (end - start);
		System.out.println("Took --> " + regular);

		start = System.nanoTime();
		collect = inventory.parallelStream().sorted(Comparator.comparing(Food::getCalories))
				.collect(Collectors.toList());
		end = System.nanoTime();
		long parallel = (end - start);
		System.out.println("Took --> " + parallel);

		System.out.println(regular - parallel);

		System.out.println(inventory.stream().mapToInt(Food::getCalories).sum());

	}

	static {
		BiFunction<String, Integer, Food> func = Food::new;
		// for (int i = 0; i < 999999; i++) {
		inventory.add(func.apply("Biryani", 15000));
		inventory.add(func.apply("Veg Biryani", 10000));
		inventory.add(func.apply("Pappu", 1000));
		inventory.add(func.apply("Chicken Muglay", 12000));
		inventory.add(func.apply("Mutton Biryani", 19000));
		inventory.add(func.apply("Butter Chicken", 19000));
		inventory.add(func.apply("Chicken Fry", 11000));
		inventory.add(func.apply("Fish", 9000));
		// }
	}

}
