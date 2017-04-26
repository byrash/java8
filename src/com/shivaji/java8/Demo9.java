package com.shivaji.java8;

import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Demo9 {
	public static void main(String[] args) {
		// System.out.println("Sequential " +
		// measurePerformance(Demo9::sequentialSum, 10_000_000) + " msec");
		// System.out.println("parallel " +
		// measurePerformance(Demo9::parallelSum, 10_000_000) + " msec");
		System.out.println("Ranged Sequential " + measurePerformance(Demo9::rangedSum, 10_000_000) + " msec");
		System.out.println("Ranged Parallel " + measurePerformance(Demo9::parallelRangedSum, 10_000_000) + " msec");
		System.out.println("Ranged Fork Join " + measurePerformance(Demo9::forkedSum, 10_000_000) + " msec");

	}

	public static long forkedSum(long n) {
		long[] numbers = LongStream.rangeClosed(1L, n)
				.toArray();
		ForkJoinSumCalculator forkJoinSum = new ForkJoinSumCalculator(numbers);
		return new ForkJoinPool().invoke(forkJoinSum);
	}

	public static long measurePerformance(Function<Long, Long> adder, long n) {
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			adder.apply(n);
			long end = System.nanoTime();
			if (fastest > (end - start) / 1_000_000)
				fastest = (end - start) / 1_000_000;
		}
		return fastest;
	}

	public static long sequentialSum(Long n) {
		return Stream.iterate(1L, i -> i + 1)
				.limit(n)
				.reduce(0L, Long::sum);
	}

	public static long parallelSum(Long n) {
		return Stream.iterate(1L, i -> i + 1)
				.limit(n)
				.parallel()
				.reduce(0L, Long::sum);
	}

	public static long rangedSum(Long n) {
		return LongStream.rangeClosed(1, n)
				.reduce(0L, Long::sum);
	}

	public static long parallelRangedSum(Long n) {
		return LongStream.rangeClosed(1, n)
				.parallel()
				.reduce(0L, Long::sum);
	}
}
