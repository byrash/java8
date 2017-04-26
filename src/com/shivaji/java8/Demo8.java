package com.shivaji.java8;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Demo8 {

	public static void main(String[] args) {
		System.out.println(partitionPrimes(100));
	}

	public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
		return IntStream.rangeClosed(2, n)
				.boxed()
				.collect(Collectors.partitioningBy(n1 -> isPrime(n1)));
	}

	public static boolean isPrime(int candidate) {
		int candidateRoot = (int) Math.sqrt((double) candidate);
		return IntStream.rangeClosed(2, candidateRoot)
				.noneMatch(i -> candidate % i == 0);
	}

	public static boolean isPrime(List<Integer> primesFoundSoFar, int candidate) {
		int candidateRoot = (int) Math.sqrt((double) candidate);
		return takeWhile(primesFoundSoFar, i -> i <= candidateRoot).stream()
				.noneMatch(i -> candidate % i == 0);
	}

	public static <T> List<T> takeWhile(List<T> primesFoundSoFar, Predicate<T> predicate) {
		int i = 0;
		for (T primeNo : primesFoundSoFar) {
			if (!predicate.test(primeNo)) {
				return primesFoundSoFar.subList(0, i);
			}
			i++;
		}
		return primesFoundSoFar;
	}

	public static Map<Boolean, List<Integer>> partitionPrimesUsingCustomCollector(int n) {
		return IntStream.range(2, n)
				.boxed()
				.collect(new PrimeNumberCollector());
	}

}
