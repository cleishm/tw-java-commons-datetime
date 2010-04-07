package com.thoughtworks.datetime;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.Test;

import com.thoughtworks.datetime.Date.Month;

public class LocalDateTimeTest {

	@After
	public void resetDateTime() throws Exception {
		LocalDateTime.resetSystemDateTime();
	}

	@Test
	public void shouldReturnDateTimeComponents() throws Exception {
		final Calendar cal = createCalendarForDateTime(2001, Calendar.OCTOBER, 25, 15, 12, 43);
		final LocalDateTime dateTime = LocalDateTime.onDate(cal.getTime());
		assertEquals(dateTime.getYear(), 2001);
		assertEquals(dateTime.getMonthOfYear(), 10);
		assertEquals(dateTime.month(), Month.OCTOBER);
		assertEquals(dateTime.getDayOfMonth(), 25);
		assertEquals(dateTime.hourOfDay(), 15);
		assertEquals(dateTime.minuteOfHour(), 12);
		assertEquals(dateTime.secondOfMinute(), 43);
	}

	@Test
	public void shouldAddDays() throws Exception {
		final Calendar cal = createCalendarForDateTime(2001, Calendar.OCTOBER, 25, 15, 12, 43);
		final LocalDateTime dateTime = LocalDateTime.onDate(cal.getTime());

		final LocalDateTime actual = dateTime.plusDays(3);
		final LocalDateTime expected = LocalDateTime.at(2001, Month.OCTOBER, 28, 15, 12, 43);

		assertEquals(actual, expected);
	}

	@Test
	public void shouldMinusDays() throws Exception {
		final Calendar cal = createCalendarForDateTime(2001, Calendar.OCTOBER, 25, 15, 12, 43);
		final LocalDateTime dateTime = LocalDateTime.onDate(cal.getTime());

		final LocalDateTime actual = dateTime.minusDays(3);
		final LocalDateTime expected = LocalDateTime.at(2001, Month.OCTOBER, 22, 15, 12, 43);

		assertEquals(actual, expected);
	}

	@Test
	public void shouldAddSecondsToTheDateTime() throws Exception {
		final Calendar cal = createCalendarForDateTime(2007, Calendar.JANUARY, 31, 23, 59, 45);
		final LocalDateTime dateTime = LocalDateTime.onDate(cal.getTime());
		final LocalDateTime actualDateTime = dateTime.plusSeconds(20);

		final LocalDateTime expectedDateTime = LocalDateTime.at(2007, Month.FEBRUARY, 01, 00, 00, 05);

		assertEquals(actualDateTime, expectedDateTime);
	}

	@Test
	public void shouldSubtractsSecondsToTheDateTime() throws Exception {
		final Calendar cal = createCalendarForDateTime(2007, Calendar.FEBRUARY, 01, 00, 00, 15);
		final LocalDateTime dateTime = LocalDateTime.onDate(cal.getTime());
		final LocalDateTime actualDateTime = dateTime.minusSeconds(20);

		final LocalDateTime expectedDateTime = LocalDateTime.at(2007, Month.JANUARY, 31, 23, 59, 55);

		assertEquals(actualDateTime, expectedDateTime);
	}

	@Test
	public void shouldReturnDayOfWeekForSunday() throws Exception {
		final Calendar cal = createCalendarForDateAtMidnight(2006, Calendar.OCTOBER, 8);
		final LocalDateTime dateTime = LocalDateTime.onDate(cal.getTime());
		assertEquals(dateTime.getDayOfWeek(), 7);
		assertEquals(dateTime.day(), LocalDateTime.Day.SUNDAY);
	}

	@Test
	public void shouldReturnDayOfWeekForMonday() throws Exception {
		final Calendar cal = createCalendarForDateAtMidnight(2006, Calendar.OCTOBER, 9);
		final LocalDateTime dateTime = LocalDateTime.onDate(cal.getTime());
		assertEquals(dateTime.getDayOfWeek(), 1);
		assertEquals(dateTime.day(), LocalDateTime.Day.MONDAY);
	}

	@Test
	public void shouldReturnDayOfWeekForTuesday() throws Exception {
		final Calendar cal = createCalendarForDateAtMidnight(2006, Calendar.OCTOBER, 10);
		final LocalDateTime dateTime = LocalDateTime.onDate(cal.getTime());
		assertEquals(dateTime.getDayOfWeek(), 2);
		assertEquals(dateTime.day(), LocalDateTime.Day.TUESDAY);
	}

	@Test
	public void shouldReturnDayOfWeekForWednesday() throws Exception {
		final Calendar cal = createCalendarForDateAtMidnight(2006, Calendar.OCTOBER, 11);
		final LocalDateTime dateTime = LocalDateTime.onDate(cal.getTime());
		assertEquals(dateTime.getDayOfWeek(), 3);
		assertEquals(dateTime.day(), LocalDateTime.Day.WEDNESDAY);
	}

	@Test
	public void shouldReturnDayOfWeekForThursday() throws Exception {
		final Calendar cal = createCalendarForDateAtMidnight(2006, Calendar.OCTOBER, 12);
		final LocalDateTime dateTime = LocalDateTime.onDate(cal.getTime());
		assertEquals(dateTime.getDayOfWeek(), 4);
		assertEquals(dateTime.day(), LocalDateTime.Day.THURSDAY);
	}

	@Test
	public void shouldReturnDayOfWeekForFriday() throws Exception {
		final Calendar cal = createCalendarForDateAtMidnight(2006, Calendar.OCTOBER, 13);
		final LocalDateTime dateTime = LocalDateTime.onDate(cal.getTime());
		assertEquals(dateTime.getDayOfWeek(), 5);
		assertEquals(dateTime.day(), LocalDateTime.Day.FRIDAY);
	}

	@Test
	public void shouldReturnDayOfWeekForSaturday() throws Exception {
		final Calendar cal = createCalendarForDateAtMidnight(2006, Calendar.OCTOBER, 14);
		final LocalDateTime dateTime = LocalDateTime.onDate(cal.getTime());
		assertEquals(dateTime.getDayOfWeek(), 6);
		assertEquals(dateTime.day(), LocalDateTime.Day.SATURDAY);
	}

	@Test
	public void shouldReturnLocalDate() throws Exception {
		final Calendar cal = createCalendarForDateTime(2001, Calendar.OCTOBER, 25, 12, 25, 20);
		final LocalDateTime dateTime = LocalDateTime.onDate(cal.getTime());
		assertEquals(dateTime.toLocalDate(), LocalDate.on(2001, Month.OCTOBER, 25));
	}

	@Test
	public void shouldReturnJavaDate() throws Exception {
		final Calendar cal = createCalendarForDateTime(2001, Calendar.OCTOBER, 25, 12, 25, 20);
		final LocalDateTime dateTime = LocalDateTime.onDate(cal.getTime());
		assertEquals(dateTime.getDate(), cal.getTime());
	}

	@Test
	public void shouldReturnCalendar() throws Exception {
		final Calendar cal = createCalendarForDateTime(2001, Calendar.OCTOBER, 25, 12, 25, 20);
		final LocalDateTime dateTime = LocalDateTime.onDate(cal.getTime());
		assertEquals(dateTime.toCalendar(), cal);
	}

	@Test
	public void shouldReturnCorrectDateTimeWhenConstructedWithDateTimeComponents() throws Exception {
		final LocalDateTime dateTime = LocalDateTime.at(2001, 10, 25, 2, 45, 12);
		assertEquals(dateTime.getYear(), 2001);
		assertEquals(dateTime.getMonthOfYear(), 10);
		assertEquals(dateTime.month(), Month.OCTOBER);
		assertEquals(dateTime.getDayOfMonth(), 25);
		assertEquals(dateTime.hourOfDay(), 2);
		assertEquals(dateTime.minuteOfHour(), 45);
		assertEquals(dateTime.secondOfMinute(), 12);
		final Calendar cal = createCalendarForDateTime(2001, Calendar.OCTOBER, 25, 2, 45, 12);
		assertEquals(dateTime.getDate(), cal.getTime());
	}

	@Test
	public void shouldReturnCorrectDateTimeWhenConstructedWithDateTimeEnumComponents() throws Exception {
		final LocalDateTime dateTime = LocalDateTime.at(2001, Month.OCTOBER, 25, 2, 45, 12);
		assertEquals(dateTime.getYear(), 2001);
		assertEquals(dateTime.getMonthOfYear(), 10);
		assertEquals(dateTime.month(), Month.OCTOBER);
		assertEquals(dateTime.getDayOfMonth(), 25);
		assertEquals(dateTime.hourOfDay(), 2);
		assertEquals(dateTime.minuteOfHour(), 45);
		assertEquals(dateTime.secondOfMinute(), 12);
		final Calendar cal = createCalendarForDateTime(2001, Calendar.OCTOBER, 25, 2, 45, 12);
		assertEquals(dateTime.getDate(), cal.getTime());
	}

	@Test
	public void shouldReturnCorrectDateTimeWhenConstructedWithLocalDateAndTimes() throws Exception {
		final LocalDate date = LocalDate.on(2001, 10, 25);
		final LocalDateTime dateTime = LocalDateTime.onDateAt(date, 11, 2, 5);
		assertEquals(dateTime.getYear(), 2001);
		assertEquals(dateTime.getMonthOfYear(), 10);
		assertEquals(dateTime.month(), Month.OCTOBER);
		assertEquals(dateTime.getDayOfMonth(), 25);
		assertEquals(dateTime.hourOfDay(), 11);
		assertEquals(dateTime.minuteOfHour(), 2);
		assertEquals(dateTime.secondOfMinute(), 5);

		final Calendar cal = Calendar.getInstance();
		cal.set(2001, 9, 25, 11, 2, 5);
		cal.set(Calendar.MILLISECOND, 0);
		assertEquals(dateTime.getDate(), cal.getTime());
	}

	@Test
	public void shouldReturnSpecifiedDateBeforeTodayAtMidnightWhenConstructedWithDaysBeforeToday() throws Exception {
		LocalDateTime.setSystemDateTime(LocalDateTime.at(1985, 7, 13, 9, 30, 0));
		assertEquals(LocalDateTime.atMidnightDaysBeforeToday(5), LocalDateTime.at(1985, 7, 8, 0, 0, 0));
		assertEquals(LocalDateTime.atMidnightDaysBeforeToday(1), LocalDateTime.at(1985, 7, 12, 0, 0, 0));
		assertEquals(LocalDateTime.atMidnightDaysBeforeToday(0), LocalDateTime.at(1985, 7, 13, 0, 0, 0));
		assertEquals(LocalDateTime.atMidnightDaysBeforeToday(-1), LocalDateTime.at(1985, 7, 14, 0, 0, 0));
	}

	@Test
	public void shouldReturnSpecifiedDateAfterTodayAtMidnightWhenConstructedWithDaysAfterToday() throws Exception {
		LocalDateTime.setSystemDateTime(LocalDateTime.at(1985, 7, 13, 9, 30, 0));
		assertEquals(LocalDateTime.atMidnightDaysAfterToday(5), LocalDateTime.at(1985, 7, 18, 0, 0, 0));
		assertEquals(LocalDateTime.atMidnightDaysAfterToday(1), LocalDateTime.at(1985, 7, 14, 0, 0, 0));
		assertEquals(LocalDateTime.atMidnightDaysAfterToday(0), LocalDateTime.at(1985, 7, 13, 0, 0, 0));
		assertEquals(LocalDateTime.atMidnightDaysAfterToday(-1), LocalDateTime.at(1985, 7, 12, 0, 0, 0));
	}

	@Test
	public void shouldBeCurrentDateTimeWhenCreatedWithNowFactoryMethod() throws Exception {
		Calendar beforeTime;
		LocalDateTime dateTime;
		Calendar afterTime;
		do {
			beforeTime = Calendar.getInstance();
			dateTime = LocalDateTime.now();
			afterTime = Calendar.getInstance();
		} while (beforeTime.get(Calendar.SECOND) != afterTime.get(Calendar.SECOND));
		afterTime.set(Calendar.MILLISECOND, 0);
		assertEquals(dateTime.getDate(), afterTime.getTime());
		assertEquals(dateTime.toCalendar(), afterTime);
	}

	@Test
	public void shouldReturnDateWith0MillisecondWhenConstructedWithDate() {
		final Calendar currentTime = Calendar.getInstance();
		currentTime.set(Calendar.MILLISECOND, 55);
		final LocalDateTime dateTime = LocalDateTime.onDate(currentTime.getTime());
		currentTime.set(Calendar.MILLISECOND, 0);
		assertEquals(dateTime.getDate(), currentTime.getTime());
		assertEquals(dateTime.toCalendar(), currentTime);
	}

	@Test
	public void shouldBeSpecifiedTimeBeforeCurrentTimeWhenCreatedWithHoursBeforeNowFactoryMethod() throws Exception {
		assertEquals(LocalDateTime.hoursBeforeNow(0), LocalDateTime.now().minusHours(0));
		assertEquals(LocalDateTime.hoursBeforeNow(10), LocalDateTime.now().minusHours(10));
		assertEquals(LocalDateTime.hoursBeforeNow(-1), LocalDateTime.now().minusHours(-1));
		assertEquals(LocalDateTime.hoursBeforeNow(365), LocalDateTime.now().minusHours(365));
	}

	@Test
	public void shouldBeSpecifiedTimeAfterCurrentTimeWhenCreatedWithHoursAfterNowFactoryMethod() throws Exception {
		assertEquals(LocalDateTime.hoursAfterNow(0), LocalDateTime.now().plusHours(0));
		assertEquals(LocalDateTime.hoursAfterNow(10), LocalDateTime.now().plusHours(10));
		assertEquals(LocalDateTime.hoursAfterNow(-1), LocalDateTime.now().plusHours(-1));
		assertEquals(LocalDateTime.hoursAfterNow(365), LocalDateTime.now().plusHours(365));
	}

	@Test
	public void shouldBeEqualToEquivalentDateTime() throws Exception {
		assertEquals(LocalDateTime.at(2001, 10, 25, 14, 10, 55), LocalDateTime.at(2001, Month.OCTOBER, 25, 14, 10, 55));
	}

	@Test
	public void shouldNotBeEqualToDifferentDateTime() throws Exception {
		assertFalse("Dates should not be equal", LocalDateTime.at(2001, 10, 25, 14, 10, 55).equals(
				LocalDateTime.at(2001, 10, 25, 14, 10, 56)));
		assertFalse("Dates should not be equal", LocalDateTime.at(2001, 10, 25, 14, 10, 55).equals(
				LocalDateTime.at(2001, 10, 25, 14, 11, 55)));
		assertFalse("Dates should not be equal", LocalDateTime.at(2001, 10, 25, 13, 10, 55).equals(
				LocalDateTime.at(2001, 10, 25, 14, 10, 55)));
		assertFalse("Dates should not be equal", LocalDateTime.at(2001, 10, 25, 14, 10, 55).equals(
				LocalDateTime.at(2001, 10, 26, 14, 10, 55)));
		assertFalse("Dates should not be equal", LocalDateTime.at(2001, 11, 25, 14, 10, 55).equals(
				LocalDateTime.at(2001, 10, 25, 14, 10, 55)));
		assertFalse("Dates should not be equal", LocalDateTime.at(2001, Month.SEPTEMBER, 25, 14, 10, 55).equals(
				LocalDateTime.at(2001, 10, 25, 14, 10, 55)));
		assertFalse("Dates should not be equal", LocalDateTime.at(2002, 10, 25, 14, 10, 55).equals(
				LocalDateTime.at(2001, 10, 25, 14, 10, 55)));
	}

	@Test
	public void shouldNotBeEquivalentWithOtherTypeOfObject() throws Exception {
		final LocalDateTime dateTime = LocalDateTime.at(2001, 10, 25, 5, 45, 10);
		final java.util.Date javaDate = createCalendarForDateTime(2001, 10, 25, 5, 45, 10).getTime();
		assertFalse("Date should not be equivalent with other object", dateTime.equals(javaDate));
	}

	@Test
	public void shouldNotBeEquivalentWithNull() throws Exception {
		final LocalDateTime date = LocalDateTime.at(2001, 10, 25, 15, 30, 30);
		assertFalse("Date should not be equivalent with null", date.equals(null));
	}

	@Test
	public void shouldHaveSameHashCodeAsEquivalentDateTime() throws Exception {
		assertEquals(LocalDateTime.at(1985, 7, 13, 9, 30, 0).hashCode(), LocalDateTime.at(1985, 7, 13, 9, 30, 0)
				.hashCode());
	}

	@Test
	public void shouldReturnFormattedStringFromToString() throws Exception {
		assertEquals(LocalDateTime.at(1985, 7, 13, 10, 30, 55).toString(), "Sat Jul 13 1985 10:30:55");
		assertEquals(LocalDateTime.at(2010, 11, 2, 9, 53, 45).toString(), "Tue Nov 02 2010 09:53:45");
		assertEquals(LocalDateTime.at(2001, 10, 25, 23, 01, 01).toString(), "Thu Oct 25 2001 23:01:01");
		assertEquals(LocalDateTime.at(1992, 1, 15, 0, 0, 0).toString(), "Wed Jan 15 1992 00:00:00");
		assertEquals(LocalDateTime.at(2004, 2, 29, 23, 59, 59).toString(), "Sun Feb 29 2004 23:59:59");
	}

	@Test
	public void shouldFixDateTimeToSpecifiedValue() throws Exception {
		final LocalDateTime dateTime = LocalDateTime.at(1985, 7, 13, 9, 30, 0);
		LocalDateTime.setSystemDateTime(dateTime);
		assertEquals(LocalDateTime.now().getYear(), 1985);
		assertEquals(LocalDateTime.now().getMonthOfYear(), 7);
		assertEquals(LocalDateTime.now().getDayOfMonth(), 13);
		assertEquals(LocalDateTime.now().hourOfDay(), 9);
		assertEquals(LocalDateTime.now().minuteOfHour(), 30);
		assertEquals(LocalDateTime.now().secondOfMinute(), 0);
	}

	@Test
	public void shouldResetTimeToCurrent() throws Exception {
		final LocalDateTime dateTime = LocalDateTime.at(1985, 7, 13, 9, 30, 0);
		LocalDateTime.setSystemDateTime(dateTime);
		assertEquals(LocalDateTime.now(), dateTime);
		LocalDateTime.resetSystemDateTime();
		assertFalse("Date has not been reset", dateTime.equals(LocalDateTime.now()));
	}

	@Test
	public void shouldIndicateWhenSystemDateTimeIsFixed() throws Exception {
		LocalDateTime.setSystemDateTime(LocalDateTime.now());
		assertTrue(LocalDateTime.isSystemDateTimeFixed());
		LocalDateTime.resetSystemDateTime();
		assertFalse(LocalDateTime.isSystemDateTimeFixed());
	}

	@Test
	public void shouldReturnCorrectResultForIsAfterComparison() throws Exception {
		assertFalse(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isAfter(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
		assertTrue(LocalDateTime.at(1935, 1, 8, 12, 25, 33).isAfter(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
		assertFalse(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isAfter(LocalDateTime.at(1935, 1, 8, 12, 25, 33)));
		assertTrue(LocalDateTime.at(1935, 1, 8, 12, 26, 32).isAfter(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
		assertFalse(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isAfter(LocalDateTime.at(1935, 1, 8, 12, 26, 32)));
		assertTrue(LocalDateTime.at(1935, 1, 8, 13, 25, 32).isAfter(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
		assertFalse(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isAfter(LocalDateTime.at(1935, 1, 8, 13, 25, 32)));
		assertTrue(LocalDateTime.at(1935, 1, 9, 12, 25, 32).isAfter(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
		assertFalse(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isAfter(LocalDateTime.at(1935, 1, 9, 12, 25, 32)));
		assertTrue(LocalDateTime.at(1935, 2, 8, 12, 25, 32).isAfter(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
		assertFalse(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isAfter(LocalDateTime.at(1935, 2, 8, 12, 25, 32)));
		assertTrue(LocalDateTime.at(1936, 1, 8, 12, 25, 32).isAfter(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
		assertFalse(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isAfter(LocalDateTime.at(1936, 1, 8, 12, 25, 32)));
	}

	@Test
	public void shouldReturnCorrectResultForIsBeforeComparison() throws Exception {
		assertFalse(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isBefore(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
		assertTrue(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isBefore(LocalDateTime.at(1935, 1, 8, 12, 25, 33)));
		assertFalse(LocalDateTime.at(1935, 1, 8, 12, 25, 33).isBefore(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
		assertTrue(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isBefore(LocalDateTime.at(1935, 1, 8, 12, 26, 32)));
		assertFalse(LocalDateTime.at(1935, 1, 8, 12, 26, 32).isBefore(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
		assertTrue(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isBefore(LocalDateTime.at(1935, 1, 8, 13, 25, 32)));
		assertFalse(LocalDateTime.at(1935, 1, 8, 13, 25, 32).isBefore(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
		assertTrue(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isBefore(LocalDateTime.at(1935, 1, 9, 12, 25, 32)));
		assertFalse(LocalDateTime.at(1935, 1, 9, 12, 25, 32).isBefore(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
		assertTrue(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isBefore(LocalDateTime.at(1935, 2, 8, 12, 25, 32)));
		assertFalse(LocalDateTime.at(1935, 2, 8, 12, 25, 32).isBefore(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
		assertTrue(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isBefore(LocalDateTime.at(1936, 1, 8, 12, 25, 32)));
		assertFalse(LocalDateTime.at(1936, 1, 8, 12, 25, 32).isBefore(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
	}

	@Test
	public void shouldReturnCorrectResultForIsOnOrAfterComparison() throws Exception {
		assertTrue(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isOnOrAfter(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
		assertTrue(LocalDateTime.at(1935, 1, 8, 12, 25, 33).isOnOrAfter(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
		assertFalse(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isOnOrAfter(LocalDateTime.at(1935, 1, 8, 12, 25, 33)));
		assertTrue(LocalDateTime.at(1935, 1, 8, 12, 26, 32).isOnOrAfter(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
		assertFalse(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isOnOrAfter(LocalDateTime.at(1935, 1, 8, 12, 26, 32)));
		assertTrue(LocalDateTime.at(1935, 1, 8, 13, 25, 32).isOnOrAfter(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
		assertFalse(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isOnOrAfter(LocalDateTime.at(1935, 1, 8, 13, 25, 32)));
		assertTrue(LocalDateTime.at(1935, 1, 9, 12, 25, 32).isOnOrAfter(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
		assertFalse(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isOnOrAfter(LocalDateTime.at(1935, 1, 9, 12, 25, 32)));
		assertTrue(LocalDateTime.at(1935, 2, 8, 12, 25, 32).isOnOrAfter(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
		assertFalse(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isOnOrAfter(LocalDateTime.at(1935, 2, 8, 12, 25, 32)));
		assertTrue(LocalDateTime.at(1936, 1, 8, 12, 25, 32).isOnOrAfter(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
		assertFalse(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isOnOrAfter(LocalDateTime.at(1936, 1, 8, 12, 25, 32)));
	}

	@Test
	public void shouldReturnCorrectResultForIsOnOrBeforeComparison() throws Exception {
		assertTrue(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isOnOrBefore(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
		assertTrue(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isOnOrBefore(LocalDateTime.at(1935, 1, 8, 12, 25, 33)));
		assertFalse(LocalDateTime.at(1935, 1, 8, 12, 25, 33).isOnOrBefore(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
		assertTrue(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isOnOrBefore(LocalDateTime.at(1935, 1, 8, 12, 26, 32)));
		assertFalse(LocalDateTime.at(1935, 1, 8, 12, 26, 32).isOnOrBefore(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
		assertTrue(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isOnOrBefore(LocalDateTime.at(1935, 1, 8, 13, 25, 32)));
		assertFalse(LocalDateTime.at(1935, 1, 8, 13, 25, 32).isOnOrBefore(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
		assertTrue(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isOnOrBefore(LocalDateTime.at(1935, 1, 9, 12, 25, 32)));
		assertFalse(LocalDateTime.at(1935, 1, 9, 12, 25, 32).isOnOrBefore(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
		assertTrue(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isOnOrBefore(LocalDateTime.at(1935, 2, 8, 12, 25, 32)));
		assertFalse(LocalDateTime.at(1935, 2, 8, 12, 25, 32).isOnOrBefore(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
		assertTrue(LocalDateTime.at(1935, 1, 8, 12, 25, 32).isOnOrBefore(LocalDateTime.at(1936, 1, 8, 12, 25, 32)));
		assertFalse(LocalDateTime.at(1936, 1, 8, 12, 25, 32).isOnOrBefore(LocalDateTime.at(1935, 1, 8, 12, 25, 32)));
	}

	@Test
	public void shouldReturnTrueForAfterNowOnlyForFutureDates() throws Exception {
		final LocalDateTime dateTime = LocalDateTime.at(1985, 7, 13, 9, 30, 0);
		LocalDateTime.setSystemDateTime(dateTime);
		assertTrue(LocalDateTime.at(1985, 7, 13, 9, 30, 1).isAfterNow());
		assertTrue(LocalDateTime.at(1985, 7, 13, 9, 31, 0).isAfterNow());
		assertFalse(LocalDateTime.at(1985, 7, 13, 9, 30, 0).isAfterNow());
	}

	@Test
	public void shouldReturnTrueForBeforeNowOnlyForPastDates() throws Exception {
		final LocalDateTime dateTime = LocalDateTime.at(1985, 7, 13, 9, 30, 0);
		LocalDateTime.setSystemDateTime(dateTime);
		assertTrue(LocalDateTime.at(1985, 7, 13, 9, 29, 59).isBeforeNow());
		assertTrue(LocalDateTime.at(1985, 7, 13, 9, 28, 0).isBeforeNow());
		assertFalse(LocalDateTime.at(1985, 7, 13, 9, 30, 0).isBeforeNow());
	}

	@Test
	public void shouldReturnDistanceBetweenTwoDates() {
		assertEquals(LocalDateTime.at(1963, 11, 22, 23, 59, 59).daysUntil(LocalDateTime.at(1963, 11, 23, 23, 59, 58)),
				0);
		assertEquals(LocalDateTime.at(1963, 11, 22, 23, 59, 59).daysUntil(LocalDateTime.at(1963, 11, 23, 23, 59, 59)),
				1);
		assertEquals(LocalDateTime.at(1963, 11, 22, 0, 0, 0).daysUntil(LocalDateTime.at(1963, 11, 27, 23, 59, 59)), 5);
		assertEquals(LocalDateTime.at(1963, 11, 22, 23, 59, 59).daysUntil(LocalDateTime.at(1963, 11, 17, 0, 0, 0)), -5);
		assertEquals(LocalDateTime.at(1963, 11, 22, 12, 0, 0).daysUntil(LocalDateTime.at(1964, 11, 22, 13, 10, 45)),
				366);
		assertEquals(LocalDateTime.at(1963, 11, 22, 0, 0, 0).daysUntil(LocalDateTime.at(1962, 11, 22, 0, 0, 0)), -365);
		assertEquals(LocalDateTime.at(1963, 11, 22, 0, 0, 0).daysUntil(LocalDateTime.at(1969, 7, 20, 0, 0, 0)), 2067);
	}

	@Test
	public void shouldReturnCorrectDateTimeWhenAddingHours() {
		final LocalDateTime currentTime = LocalDateTime.at(2005, 12, 5, 12, 45, 53);
		assertEquals(currentTime.plusHours(1), LocalDateTime.at(2005, 12, 5, 13, 45, 53));
		assertEquals(currentTime.plusHours(24), LocalDateTime.at(2005, 12, 6, 12, 45, 53));
	}

	@Test
	public void shouldReturnCorrectDateTimeWhenAddingMinutes() {
		final LocalDateTime currentTime = LocalDateTime.at(2005, 12, 5, 12, 45, 53);
		assertEquals(currentTime.plusMinutes(1), LocalDateTime.at(2005, 12, 5, 12, 46, 53));
		assertEquals(currentTime.plusMinutes(600), LocalDateTime.at(2005, 12, 5, 22, 45, 53));
	}

	@Test
	public void shouldReturnCorrectDateTimeWhenSubtractingHours() {
		final LocalDateTime currentTime = LocalDateTime.at(2005, 12, 5, 12, 45, 53);
		assertEquals(currentTime.minusHours(1), LocalDateTime.at(2005, 12, 5, 11, 45, 53));
		assertEquals(currentTime.minusHours(24), LocalDateTime.at(2005, 12, 4, 12, 45, 53));
	}

	@Test
	public void shouldReturnCorrectDateTimeWhenSubtractingMinutes() throws Exception {
		final LocalDateTime currentTime = LocalDateTime.at(2005, 12, 5, 12, 45, 53);
		assertEquals(currentTime.minusMinutes(1), LocalDateTime.at(2005, 12, 5, 12, 44, 53));
		assertEquals(currentTime.minusMinutes(600), LocalDateTime.at(2005, 12, 5, 2, 45, 53));
	}

	@Test
	public void shouldReturnCorrectDateTimeWhenGettingDaysBeforeToday() throws Exception {
		final LocalDateTime currentTime = LocalDateTime.at(2005, 12, 15, 12, 45, 53);
		LocalDateTime.setSystemDateTime(currentTime);

		final LocalDateTime fiveDaysBefore = LocalDateTime.atDaysBeforeToday(5);

		assertEquals(fiveDaysBefore.getYear(), currentTime.getYear());
		assertEquals(fiveDaysBefore.getMonthOfYear(), currentTime.getMonthOfYear());
		assertEquals(fiveDaysBefore.getDayOfMonth(), currentTime.getDayOfMonth() - 5);
		assertEquals(fiveDaysBefore.hourOfDay(), currentTime.hourOfDay());
		assertEquals(fiveDaysBefore.minuteOfHour(), currentTime.minuteOfHour());
		assertEquals(fiveDaysBefore.secondOfMinute(), currentTime.secondOfMinute());
	}

	@Test
	public void shouldReturnCorrectLocalDateTimeAcrossMonthBondary() throws Exception {
		final LocalDateTime currentTime = LocalDateTime.at(2005, 12, 2, 12, 45, 53);
		LocalDateTime.setSystemDateTime(currentTime);

		final LocalDateTime fiveDaysBefore = LocalDateTime.atDaysBeforeToday(7);

		assertEquals(fiveDaysBefore.getYear(), currentTime.getYear());
		assertEquals(fiveDaysBefore.getMonthOfYear(), 11);
		assertEquals(fiveDaysBefore.getDayOfMonth(), 25);
		assertEquals(fiveDaysBefore.hourOfDay(), currentTime.hourOfDay());
		assertEquals(fiveDaysBefore.minuteOfHour(), currentTime.minuteOfHour());
		assertEquals(fiveDaysBefore.secondOfMinute(), currentTime.secondOfMinute());
	}

	@Test
	public void testLocalDateTimeIsNotInfinite() throws Exception {
		assertFalse(LocalDateTime.now().isInfinite());
		assertFalse(LocalDateTime.at(2007, 12, 12, 11, 00, 34).isInfinite());
		assertFalse(LocalDateTime.atDaysBeforeToday(123).isInfinite());
		assertFalse(LocalDateTime.hoursBeforeNow(33).isInfinite());
	}

	@Test
	public void testParseStringShouldReturnLocalDateTime() throws Exception {
		final LocalDateTime dateTime = LocalDateTime.at(2007, 1, 1, 10, 30, 1);
		assertEquals(LocalDateTime.parseDateTime(dateTime.toString(), "EEE MMM dd yyyy HH:mm:ss"), dateTime);
		assertEquals(LocalDateTime.parseDateTime(dateTime.format("yyyyMMddHHmmss"), "yyyyMMddHHmmss"), dateTime);
	}

	@Test
	public void testParseStringShouldReturnNullWhenParsingIllegalString() throws Exception {
		assertNull(LocalDateTime.parseDateTime("hello", "yyyyMMddHHmmss"));
	}

	@Test
	public void shouldFormatDateIntoStringUsingPattern() throws Exception {
		final LocalDateTime dateTime = LocalDateTime.at(1977, 12, 22, 05, 12, 34);
		assertEquals(dateTime.format("dd/MM/yyyy"), "22/12/1977");
	}

	private Calendar createCalendarForDateAtMidnight(final int year, final int month, final int day) {
		final Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.AM_PM, Calendar.AM);
		setCalendarToMidnight(cal);
		return cal;
	}

	private Calendar createCalendarForDateTime(final int year, final int month, final int day, final int hour,
			final int minute, final int second) {
		final Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.AM_PM, Calendar.AM);
		setCalendarTime(cal, hour, minute, second);
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
