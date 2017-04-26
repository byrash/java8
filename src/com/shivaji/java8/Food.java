package com.shivaji.java8;

public class Food {
	private String name;
	private Integer calories;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public Food(String name, Integer calories) {
		super();
		this.name = name;
		this.calories = calories;
	}

}
