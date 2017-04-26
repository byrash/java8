package com.shivaji.java8;

import java.util.function.Predicate;

public interface MyList<T> {

	T head();

	MyList<T> tail();

	default boolean isEmpty() {
		return true;
	}

	default public MyList<T> filter(Predicate<T> p) {
		throw new UnsupportedOperationException();
	}

}
