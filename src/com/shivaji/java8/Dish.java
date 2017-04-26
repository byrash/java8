package com.shivaji.java8;

public class Dish {

	private final String name;
	private final Boolean vegetarian;
	private final Integer calories;
	private final Type type;

	public Dish(String name, Boolean vegetarian, Integer calories, Type type) {
		super();
		this.name = name;
		this.vegetarian = vegetarian;
		this.calories = calories;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public Boolean getVegetarian() {
		return vegetarian;
	}

	public Integer getCalories() {
		return calories;
	}

	public Type getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Dish [name=" + name + "]";
	}

	public enum Type {
		MEAT, FISH, OTHER;
	}

}
