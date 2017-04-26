package com.shivaji.java8;

import java.util.Optional;
import java.util.Properties;

public class Demo10 {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("1", 5);
		props.put("b", "true");
		props.put("c", -3);
	}

	public static int readDuration(Properties props, String name) {
		return Optional.ofNullable(props.get(name))
				.flatMap(Demo10::stringToInt)
				.filter(i -> i > 0)
				.orElse(0);

	}

	public static Optional<Integer> stringToInt(Object value) {
		try {
			return Optional.ofNullable(Integer.parseInt((String) value));
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}

}
