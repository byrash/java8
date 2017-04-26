package com.shivaji.java8;

public class MyLinkedList<T> implements MyList<T> {
	private final T head;
	private final MyList<T> tail;

	@Override
	public T head() {
		return head;
	}

	@Override
	public MyList<T> tail() {
		return tail;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	public MyLinkedList(T head, MyList<T> tail) {
		super();
		this.head = head;
		this.tail = tail;
	}

}
