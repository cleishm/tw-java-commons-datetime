package com.thoughtworks.util;

import static junit.framework.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.Test;

public class LocalDateTest {

	@After
	public void resetDateTime() throws Exception {
		LocalDateTime.resetSystemDateTime();
	}

	@Test
	public void shouldReturnInfiniteLocalDateWhenCreateWithInfiniteFactoryMethod() throws Exception {
		final InfiniteLocalDate localDate = LocalDate.infinite();
		assertTrue(localDate.isInfinite());
	}

	@Test
	public void shouldReturnDateComponentsWhenConstructedWithOnDateFactoryMethod() throws Exception {
		final Calendar cal = createCalendarForDateAtMidnight(2001, Calendar.OCTOBER, 25);
		final FiniteLocalDate date = LocalDate.onDate(cal.getTime());
		assertEquals(2001, date.getYear());
		assertEquals(10, date.getMonthOfYear());
		assertEquals(Date.Month.OCTOBER, date.month());
		assertEquals(25, date.getDayOfMonth());
	}

	@Test
	public void shouldReturnCorrectDateWhenConstructedWithOnFactoryMethodTakingDateComponents() throws Exception {
		final FiniteLocalDate date = LocalDate.on(2001, 10, 25);
		assertEquals(2001, date.getYear());
		assertEquals(10, date.getMonthOfYear());
		assertEquals(Date.Month.OCTOBER, date.month());
		assertEquals(25, date.getDayOfMonth());
		final Calendar calAtMidnight = createCalendarForDateAtMidnight(2001, Calendar.OCTOBER, 25);
		assertEquals(calAtMidnight.getTime(), date.getDate());
	}

	@Test
	public void shouldReturnCorrectDateWhenConstructedWithOnFactoryMethodTakingDateEnumComponents()
			throws Exception {
		final FiniteLocalDate date = LocalDate.on(2001, Date.Month.OCTOBER, 25);
		assertEquals(2001, date.getYear());
		assertEquals(10, date.getMonthOfYear());
		assertEquals(Date.Month.OCTOBER, date.month());
		assertEquals(25, date.getDayOfMonth());
		final Calendar calAtMidnight = createCalendarForDateAtMidnight(2001, Calendar.OCTOBER, 25);
		assertEquals(calAtMidnight.getTime(), date.getDate());
	}

	@Test
	public void shouldBeCurrentDateAtMidnightWhenCreatedWithTodayFactoryMethod() throws Exception {
		Calendar systemCal;
		FiniteLocalDate localDate;
		do {
			systemCal = Calendar.getInstance();
			localDate = LocalDate.today();
		} while (systemCal.get(Calendar.DAY_OF_WEEK) != Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
		setCalendarToMidnight(systemCal);
		assertEquals(systemCal.get(Calendar.DAY_OF_MONTH), localDate.getDayOfMonth());
		assertEquals(systemCal.get(Calendar.MONTH) + 1, localDate.getMonthOfYear());
		assertEquals(systemCal.get(Calendar.YEAR), localDate.getYear());
	}

	@Test
	public void shouldBeSpecifiedDaysAfterCurrentDateWhenCreatedWithDaysAfterTodayFactoryMethod() throws Exception {
		assertEquals(LocalDate.today().plusDays(0), LocalDate.daysAfterToday(0));
		assertEquals(LocalDate.today().plusDays(10), LocalDate.daysAfterToday(10));
		assertEquals(LocalDate.today().plusDays(-1), LocalDate.daysAfterToday(-1));
		assertEquals(LocalDate.today().plusDays(365), LocalDate.daysAfterToday(365));
	}

	@Test
	public void shouldBeSpecifiedDaysBeforeCurrentDateWhenCreatedWithDaysBeforeTodayFactoryMethod()
			throws Exception {
		assertEquals(LocalDate.today().minusDays(0), LocalDate.daysBeforeToday(0));
		assertEquals(LocalDate.today().minusDays(10), LocalDate.daysBeforeToday(10));
		assertEquals(LocalDate.today().minusDays(-1), LocalDate.daysBeforeToday(-1));
		assertEquals(LocalDate.today().minusDays(365), LocalDate.daysBeforeToday(365));
	}

	@Test
	public void shouldBeSpecifiedMonthsAfterCurrentDateWhenCreatedWithMonthsAfterTodayFactoryMethod()
			throws Exception {
		assertEquals(LocalDate.today().plusMonths(0), LocalDate.monthsAfterToday(0));
		assertEquals(LocalDate.today().plusMonths(10), LocalDate.monthsAfterToday(10));
		assertEquals(LocalDate.today().plusMonths(-1), LocalDate.monthsAfterToday(-1));
		assertEquals(LocalDate.today().plusMonths(12), LocalDate.monthsAfterToday(12));
	}

	private Calendar createCalendarForDateAtMidnight(int year, int month, int day) {
		final Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.AM_PM, Calendar.AM);
		setCalendarToMidnight(cal);
		return cal;
	}

	private void setCalendarToMidnight(final Calendar cal) {
		setCalendarTime(cal, 0, 0, 0);
	}

	private void setCalendarTime(final Calendar cal, final int hour, final int minute, final int second) {
		cal.set(Calendar.AM_PM, Calendar.AM);
		cal.set(Calendar.HOUR, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.MILLISECOND, 0);
	}
}
