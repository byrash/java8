package com.shivaji.java8;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

	private final long[] numbers;
	private final int start;
	private final int end;

	public static final long THRESHOLD = 10_000;

	@Override
	protected Long compute() {
		int length = start - end;
		if (length <= THRESHOLD) {
			return computeSequentially();
		} else {
			ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
			leftTask.fork();
			ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
			Long rightResult = rightTask.compute();
			Long leftResult = leftTask.join();
			return rightResult + leftResult;
		}
	}

	private long computeSequentially() {
		long sum = 0;
		for (int i = start; i < end; i++) {
			sum += numbers[i];
		}
		return sum;
	}

	public ForkJoinSumCalculator(long[] numbers) {
		this(numbers, 0, numbers.length);
	}

	public ForkJoinSumCalculator(long[] numbers, int start, int end) {
		super();
		this.numbers = numbers;
		this.start = start;
		this.end = end;
	}

}
