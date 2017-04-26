package com.shivaji.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Demo3 {
	private final static List<Dish> menu = Arrays.asList(new Dish("Pork", false, 800, Dish.Type.MEAT),
			new Dish("Beaf", false, 700, Dish.Type.MEAT), new Dish("Chicken", false, 400, Dish.Type.MEAT),
			new Dish("French fries", true, 530, Dish.Type.OTHER), new Dish("Rice", true, 350, Dish.Type.OTHER),
			new Dish("Season fruit", true, 120, Dish.Type.OTHER), new Dish("Pizza", true, 550, Dish.Type.OTHER),
			new Dish("Prawns", false, 300, Dish.Type.FISH), new Dish("Salmon", false, 450, Dish.Type.FISH));
	private final static List<String> words = Arrays.asList("Hello", "World");
	private final static List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4);
	private final static List<Integer> numbers2 = Arrays.asList(1, 2);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// dishes();
		// noDuplicateLetters();
		// numbersMapping();
		// System.out.println(numbers1.stream().reduce(0, Integer::sum));
		// System.out.println(numbers1.stream().reduce(1, Math::multiplyExact));
		// menu.stream()
		// .collect(Collectors.counting());
		// System.out.println(menu.stream()
		// .collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
		// System.out.println(menu.stream()
		// .collect(Collectors.summarizingInt(Dish::getCalories)));
		// System.out.println(menu.stream()
		// .collect(Collectors.groupingBy(Dish::getType,
		// Collectors.collectingAndThen(
		// Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
		// Optional::get))));
		System.out.println(menu.stream()
				.collect(Collectors.partitioningBy(Dish::getVegetarian, Collectors.groupingBy(Dish::getType))));

	}

	private static void numbersMapping() {
		numbers1.stream()
				.flatMap((i) -> numbers2.stream()
						.filter((j) -> (i + j) % 3 == 0)
						.map((j) -> new Integer[] { i, j }))
				.map((i) -> "{ " + i[0].toString() + " , " + i[1].toString() + " }")
				.forEach(System.out::println);
	}

	private static void noDuplicateLetters() {
		System.out.println(words.stream()
				.map(s -> s.split(""))
				.flatMap(Arrays::stream)
				.distinct()
				.collect(Collectors.toList()));
	}

	private static void dishes() {
		List<String> top3CaloriesItems = menu.stream()
				.filter((d) -> {
					// System.out.println("Filtering :" + d.getName());
					return d.getCalories() > 300;
				})
				.map((d) -> {
					// System.out.println("Mapping :" + d.getName());
					return d.getName();
				})
				.limit(3)
				.collect(Collectors.toList());
		System.out.println(top3CaloriesItems);
	}

}
