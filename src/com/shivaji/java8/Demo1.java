package com.shivaji.java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Demo1 {
	 List<Apple> inventory = constructInventory(Apple::new);

	public static void main(String[] args) {
		Demo1 demo = new Demo1();
		Function<Apple, String> func = (a)-> a.getColor() + "( "+a.getSize()+" )";
		demo.printApples(func);
		demo.inventory.sort((a1,a2)-> a1.getSize().compareTo(a2.getSize()));
		demo.printApples(func);
		
		demo.inventory.sort(Comparator.comparing((a)->a.getSize()));
		demo.printApples(func);
		
		demo.inventory.sort(Comparator.comparing(Apple::getSize));
		demo.printApples(func);
		
		demo.inventory.sort(Comparator.comparing(Apple::getSize).reversed());
		demo.printApples(func);
		
		demo.inventory.sort(Comparator.comparing(Apple::getSize).reversed().thenComparing(Apple::getColor));
		demo.printApples(func);
	}
	
	
	private void printApples(Function<Apple,String> func){
		StringBuilder sb = new StringBuilder();
		for(Apple a : inventory){
			sb.append(func.apply(a));
		}
		System.out.println(sb.toString());
	}
	
	private List<Apple> constructInventory(BiFunction<String, Integer, Apple> func){
		List<Apple> inventory = new ArrayList<>();
		inventory.add(func.apply("Red", 120));
		inventory.add(func.apply("Green", 100));
		inventory.add(func.apply("Pink", 150));
		inventory.add(func.apply("Rose Pink", 120));
		return inventory;
	}

}
