package com.shivaji.java8;

public class Demo12 {
	public static void main(String[] args) {
		// LazyList<Integer> numbers = from(2);
		// System.out.println(primes(numbers).head() + " " +
		// primes(numbers).tail()
		// .head() + " "
		// + primes(numbers).tail()
		// .tail()
		// .head());
		printAll(primes(from(2)));
	}

	public static LazyList<Integer> from(Integer n) {
		return new LazyList<Integer>(n, () -> from(n + 1));
	}

	public static MyList<Integer> primes(MyList<Integer> numbers) {
		return new LazyList<Integer>(numbers.head(), () -> primes(numbers.tail()
				.filter(n -> n % numbers.head() != 0)));
	}

	public static void printAll(MyList<Integer> list) {
		if (list.isEmpty())
			return;
		System.out.println(list.head());
		printAll(list.tail());
	}

}
