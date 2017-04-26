package com.shivaji.java8;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import sun.util.BuddhistCalendar;

public class Demo11 {

	public static void main(String[] args) {
		System.out.println(LocalDate.now());
		System.out.println(LocalTime.now());
		System.out.println(LocalDateTime.now());
		// System.out.println(LocalDate.parse("2016 - 10 - 3"));
		System.out.println(LocalDate.of(2014, 10, 03));
		System.out.println(Instant.now());
		LocalDateTime ldt = LocalDateTime.now();
		LocalDateTime ldt1 = LocalDateTime.now()
				.minusDays(90);
		Duration between = Duration.between(ldt, ldt1);
		System.out.println(between);
		System.out.println(LocalDateTime.now()
				.format(DateTimeFormatter.BASIC_ISO_DATE));
		System.out.println(ZonedDateTime.now());
		System.out.println(BuddhistCalendar.from(ZonedDateTime.now()));
	}

}
