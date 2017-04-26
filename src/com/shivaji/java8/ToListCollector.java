package com.shivaji.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

	/**
	 * Supplies the accumlator; middle element in the generic types
	 */
	@Override
	public Supplier<List<T>> supplier() {
		return ArrayList::new;
	}

	/**
	 * Does the real job; accumlates the item supplied from stream to the
	 * accumlator object created by supplier which will be used to accumlate the
	 * values. This suupllies a bi fucntion to consume
	 */
	@Override
	public BiConsumer<List<T>, T> accumulator() {
		return List::add;
	}

	/**
	 * In prallel stream does the real combining of the results i.e merging
	 * Workes on two accumleators to generate a single accumlator joining both
	 */
	@Override
	public BinaryOperator<List<T>> combiner() {
		return (list1, list2) -> {
			list1.addAll(list2);
			return list1;
		};
	}

	/**
	 * Says if the accumaltor can be used as teh end item;
	 * 
	 * This is ideally a terminal opeartion to get the end result.
	 */
	@Override
	public Function<List<T>, List<T>> finisher() {
		return Function.identity();
	}

	/**
	 * Provides behavioural charcteristics; saying if it can do parallel or is
	 * accumaltor same as the finsher or does the order matters and so on
	 */
	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
	}

}
