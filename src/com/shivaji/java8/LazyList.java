package com.shivaji.java8;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class LazyList<T> implements MyList<T> {
	private final T head;
	private final Supplier<MyList<T>> tail;

	@Override
	public T head() {
		return head;
	}

	@Override
	public MyList<T> tail() {
		return tail.get();
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	public LazyList(T head, Supplier<MyList<T>> tail) {
		super();
		this.head = head;
		this.tail = tail;
	}

	public MyList<T> filter(Predicate<T> p) {
		return isEmpty() ? this : p.test(head()) ? new LazyList<T>(head(), () -> tail().filter(p)) : tail().filter(p);
	}
}
