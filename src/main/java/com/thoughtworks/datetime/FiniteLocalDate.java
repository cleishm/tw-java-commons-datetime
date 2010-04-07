package com.thoughtworks.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;

public class FiniteLocalDate extends LocalDate {
	protected org.joda.time.LocalDate jodaLocalDate;

	protected FiniteLocalDate(final org.joda.time.LocalDate date) {
		assert date != null;
		jodaLocalDate = date;
	}

	public java.util.Date getDate() {
		return jodaLocalDate.toDateMidnight().toDate();
	}

	@Override
	public FiniteLocalDate toFiniteLocalDate() {
		return this;
	}

	public FiniteLocalDate toFutureDate() {
		return isAfterNow() ? this : LocalDate.daysAfterToday(1);
	}

	public Calendar toCalendar() {
		return jodaLocalDate.toDateMidnight().toCalendar(null);
	}

	public boolean isInfinite() {
		return false;
	}

	public int getDayOfWeek() {
		return jodaLocalDate.getDayOfWeek();
	}

	public int getDayOfMonth() {
		return jodaLocalDate.getDayOfMonth();
	}

	public int getMonthOfYear() {
		return jodaLocalDate.getMonthOfYear();
	}

	@Override
	public int getWeekOfYear() {
		return jodaLocalDate.getWeekOfWeekyear();
	}

	public Day day() {
		final int dayOfWeek = jodaLocalDate.getDayOfWeek();
		return Day.values()[dayOfWeek - 1];
	}

	public Month month() {
		final int monthOfYear = jodaLocalDate.getMonthOfYear();
		return Month.values()[monthOfYear - 1];
	}

	public int getYear() {
		return jodaLocalDate.getYear();
	}

	@Override
	public FiniteLocalDate minusDays(final int days) {
		return new FiniteLocalDate(jodaLocalDate.minusDays(days));
	}

	@Override
	public FiniteLocalDate plusMonths(final int months) {
		return new FiniteLocalDate(jodaLocalDate.plusMonths(months));
	}

	@Override
	public FiniteLocalDate minusMonths(final int months) {
		return new FiniteLocalDate(jodaLocalDate.minusMonths(months));
	}

	@Override
	public FiniteLocalDate plusDays(final int days) {
		return new FiniteLocalDate(jodaLocalDate.plusDays(days));
	}

	public boolean isLastDayOfMonth() {
		FiniteLocalDate date = plusDays(1);
		return date.isFirstDayOfMonth();
	}

	public boolean isFirstDayOfMonth() {
		return getDayOfMonth() == 1;
	}

	public boolean isWeekend() {
		if (day() == Day.SUNDAY || day() == Day.SATURDAY) {
			return true;
		}
		return false;
	}

	@Override
	public LocalDate plusDuration(final Duration duration) {
		assert duration != null;
		return duration.isInfinite() ? LocalDate.infinite() : plusDays(duration.getDays());
	}

	public boolean isAfter(final Date date) {
		if (date.isInfinite()) {
			return false;
		}
		return jodaLocalDate.isAfter(new org.joda.time.LocalDate(date.getDate()));
	}

	public boolean isBefore(final Date date) {
		if (date.isInfinite()) {
			return true;
		}
		return jodaLocalDate.isBefore(new org.joda.time.LocalDate(date.getDate()));
	}

	public boolean isOnOrAfter(final Date date) {
		return !isBefore(date);
	}

	public boolean isOnOrBefore(final Date date) {
		return !isAfter(date);
	}

	public boolean isAfterNow() {
		return jodaLocalDate.isAfter(new org.joda.time.LocalDate());
	}

	public boolean isBeforeNow() {
		return jodaLocalDate.isBefore(new org.joda.time.LocalDate());
	}

	public boolean isBetween(final Date start, final Date end) {
		return isOnOrAfter(start) && isOnOrBefore(end);
	}

	public int compareTo(final LocalDate date) {
		return date.isInfinite() ? -1 : jodaLocalDate.compareTo(new org.joda.time.LocalDate(date.getDate()));
	}

	@Override
	public Duration durationUntil(final Date date) {
		if (date.isInfinite()) {
			return Duration.infinite();
		} else if (isAfter(date)) {
			throw new IllegalArgumentException("cannot get duration to earlier date");
		}
		return Duration.ofDays(daysUntil(date));
	}

	private int daysUntil(final Date date) {
		return new org.joda.time.Period(getDate().getTime(), date.getDate().getTime(), PeriodType.days()).getDays();
	}

	public static FiniteLocalDate parseDate(final String dateString, final String dateFormat) {
		if (StringUtils.isEmpty(dateString)) {
			return null;
		}
		final SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		format.setLenient(false);
		try {
			return FiniteLocalDate.onDate(format.parse(dateString));
		} catch (final ParseException e) {
			return null;
		}
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof FiniteLocalDate)) {
			return false;
		}
		final FiniteLocalDate other = (FiniteLocalDate) obj;
		return new EqualsBuilder().append(jodaLocalDate, other.jodaLocalDate).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(jodaLocalDate).toHashCode();
	}

	public String format(final String pattern) {
		return DateTimeFormat.forPattern(pattern).print(jodaLocalDate);
	}

	@Override
	public String toString() {
		return format("EEE MMM dd yyyy");
	}

	public boolean isBetween(final FiniteLocalDate start, final FiniteLocalDate end) {
		return isOnOrAfter(start) && isOnOrBefore(end);
	}

}
