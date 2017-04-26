package com.shivaji.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Demo4 {

	private static final List<Transaction> transctions;

	static {
		Trader roual = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");

		transctions = Arrays.asList(new Transaction(brian, 2011, 300), new Transaction(roual, 2012, 1000),
				new Transaction(roual, 2011, 400), new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700),
				new Transaction(alan, 2012, 950));

	}

	public static void main(String[] args) {
		System.out.println("----1----");
		transctions.stream()
				.filter((t) -> t.getYear() == 2011)
				.sorted(Comparator.comparing(Transaction::getValue))
				.forEach(System.out::println);
		System.out.println("----2----");
		transctions.stream()
				.map((t) -> t.getTrader()
						.getCity())
				.distinct()
				.forEach(System.out::println);
		System.out.println("----3----");
		transctions.stream()
				.filter((t) -> t.getTrader()
						.getCity() == "Cambridge")
				.map(Transaction::getTrader)
				.sorted(Comparator.comparing(Trader::getName))
				.distinct()
				.forEach(System.out::println);
		System.out.println("----4----");
		System.out.println(transctions.stream()
				.map((t) -> t.getTrader()
						.getName())
				.distinct()
				.sorted()
				.collect(Collectors.joining()));
		System.out.println("----5----");
		System.out.println(transctions.stream()
				.anyMatch((t) -> t.getTrader()
						.getCity()
						.equals("Milan")));
		System.out.println("----6----");
		transctions.stream()
				.filter((t) -> t.getTrader()
						.getCity()
						.equals("Cambridge"))
				.distinct()
				.forEach(System.out::println);
		System.out.println("---7----");
		System.out.println(transctions.stream()
				.max(Comparator.comparing(Transaction::getValue)));
		System.out.println("---8----");
		System.out.println(transctions.stream()
				.min(Comparator.comparing(Transaction::getValue)));
	}

}
