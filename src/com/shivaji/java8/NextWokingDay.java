package com.shivaji.java8;

import java.time.DayOfWeek;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class NextWokingDay implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {
		return temporal.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
	}

}
