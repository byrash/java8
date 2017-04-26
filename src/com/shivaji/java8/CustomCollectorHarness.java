package com.shivaji.java8;

public class CustomCollectorHarness {

	public static void main(String[] args) {
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			Demo8.partitionPrimesUsingCustomCollector(1_000_000);
			long duration = (System.nanoTime() - start) / 1_000_000;
			if (duration < fastest)
				fastest = duration;
		}
		System.out.println("Fastest Exection done is :" + fastest + " msec");
	}

}
