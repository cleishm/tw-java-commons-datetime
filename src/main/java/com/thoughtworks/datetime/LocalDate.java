package com.thoughtworks.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;

public abstract class LocalDate implements Date, Comparable<LocalDate> {
	public static InfiniteLocalDate infinite() {
		return InfiniteLocalDate.INSTANCE;
	}

	public static FiniteLocalDate today() {
		return new FiniteLocalDate(new org.joda.time.LocalDate());
	}

	public static FiniteLocalDate on(final int year, final int month, final int day) {
		return new FiniteLocalDate(new org.joda.time.LocalDate(year, month, day));
	}

	public static FiniteLocalDate on(final int year, final Month month, final int day) {
		return new FiniteLocalDate(new org.joda.time.LocalDate(year, month.ordinal() + 1, day));
	}

	public static FiniteLocalDate onDate(final java.util.Date time) {
		return new FiniteLocalDate(new org.joda.time.LocalDate(time));
	}

	public static FiniteLocalDate daysAfterToday(final int days) {
		return FiniteLocalDate.today().plusDays(days);
	}

	public static FiniteLocalDate daysBeforeToday(final int days) {
		return FiniteLocalDate.today().minusDays(days);
	}

	public static FiniteLocalDate monthsAfterToday(final int months) {
		return FiniteLocalDate.today().plusMonths(months);
	}

	public abstract FiniteLocalDate toFiniteLocalDate();

	public abstract int getWeekOfYear();

	public abstract boolean isFirstDayOfMonth();

	public abstract boolean isLastDayOfMonth();

	public abstract boolean isWeekend();

	public abstract LocalDate minusDays(final int days);

	public abstract LocalDate plusMonths(final int months);

	public abstract LocalDate minusMonths(final int months);

	public abstract LocalDate plusDays(final int days);

	public abstract LocalDate plusDuration(final Duration duration);

	public abstract Duration durationUntil(final Date date);

	public static LocalDate parseDate(final String dateString, final String pattern) {
		if (StringUtils.isEmpty(dateString)) {
			return null;
		}
		final SimpleDateFormat format = new SimpleDateFormat(pattern);
		format.setLenient(false);
		try {
			return LocalDate.onDate(format.parse(dateString));
		} catch (final ParseException e) {
			return null;
		}
	}

	@Override
	public abstract String toString();

}
