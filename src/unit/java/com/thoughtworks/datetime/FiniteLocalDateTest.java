package com.thoughtworks.datetime;

import static junit.framework.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.Test;

public class FiniteLocalDateTest {

	@After
	public void resetDateTime() throws Exception {
		LocalDateTime.resetSystemDateTime();
	}

	@Test
	public void shouldReturnJavaDateAtMidnight() throws Exception {
		final Calendar cal = createCalendarForDateTime(2001, Calendar.OCTOBER, 25, 12, 25, 20);
		final LocalDate date = LocalDate.onDate(cal.getTime());
		final Calendar calAtMidnight = createCalendarForDateAtMidnight(2001, Calendar.OCTOBER, 25);
		assertEquals(calAtMidnight.getTime(), date.getDate());
	}

	@Test
	public void shouldReturnFiniteLocalDateWhenNotInfinite() throws Exception {
		assertEquals(LocalDate.today(), LocalDate.today().toFiniteLocalDate());
	}

	@Test
	public void shouldReturnNextFutureDate() {
		assertEquals(LocalDate.daysAfterToday(1), LocalDate.today().toFutureDate());
		assertEquals(LocalDate.daysAfterToday(1), LocalDate.daysBeforeToday(10).toFutureDate());
		assertEquals(LocalDate.daysAfterToday(1), LocalDate.daysBeforeToday(365).toFutureDate());
		assertEquals(LocalDate.daysAfterToday(1), LocalDate.daysAfterToday(1).toFutureDate());
		assertEquals(LocalDate.daysAfterToday(365), LocalDate.daysAfterToday(365).toFutureDate());
	}

	@Test
	public void shouldReturnCalendarAtMidnight() throws Exception {
		final Calendar cal = createCalendarForDateTime(2001, Calendar.OCTOBER, 25, 12, 25, 20);
		final LocalDate date = LocalDate.onDate(cal.getTime());
		final Calendar calAtMidnight = createCalendarForDateAtMidnight(2001, Calendar.OCTOBER, 25);
		assertEquals(calAtMidnight, date.toCalendar());
	}

	@Test
	public void shouldReturnDayOfWeekForSunday() throws Exception {
		final Calendar cal = createCalendarForDateAtMidnight(2006, Calendar.OCTOBER, 8);
		final LocalDate date = LocalDate.onDate(cal.getTime());
		assertEquals(7, date.getDayOfWeek());
		assertEquals(LocalDate.Day.SUNDAY, date.day());
	}

	@Test
	public void shouldReturnDayOfWeekForMonday() throws Exception {
		final Calendar cal = createCalendarForDateAtMidnight(2006, Calendar.OCTOBER, 9);
		final LocalDate date = LocalDate.onDate(cal.getTime());
		assertEquals(1, date.getDayOfWeek());
		assertEquals(LocalDate.Day.MONDAY, date.day());
	}

	@Test
	public void shouldReturnDayOfWeekForTuesday() throws Exception {
		final Calendar cal = createCalendarForDateAtMidnight(2006, Calendar.OCTOBER, 10);
		final LocalDate date = LocalDate.onDate(cal.getTime());
		assertEquals(2, date.getDayOfWeek());
		assertEquals(LocalDate.Day.TUESDAY, date.day());
	}

	@Test
	public void shouldReturnDayOfWeekForWednesday() throws Exception {
		final Calendar cal = createCalendarForDateAtMidnight(2006, Calendar.OCTOBER, 11);
		final LocalDate date = LocalDate.onDate(cal.getTime());
		assertEquals(3, date.getDayOfWeek());
		assertEquals(LocalDate.Day.WEDNESDAY, date.day());
	}

	@Test
	public void shouldReturnDayOfWeekForThursday() throws Exception {
		final Calendar cal = createCalendarForDateAtMidnight(2006, Calendar.OCTOBER, 12);
		final LocalDate date = LocalDate.onDate(cal.getTime());
		assertEquals(4, date.getDayOfWeek());
		assertEquals(LocalDate.Day.THURSDAY, date.day());
	}

	@Test
	public void shouldReturnFirstWeekOfYearFor3January() throws Exception {
		final Calendar cal = createCalendarForDateAtMidnight(2006, Calendar.JANUARY, 3);
		final LocalDate date = LocalDate.onDate(cal.getTime());
		assertEquals(1, date.getWeekOfYear());
	}

	@Test
	public void shouldReturnLastWeekOfYearFor31December() throws Exception {
		final Calendar cal = createCalendarForDateAtMidnight(2006, Calendar.DECEMBER, 31);
		final LocalDate date = LocalDate.onDate(cal.getTime());
		assertEquals(52, date.getWeekOfYear());
	}

	@Test
	public void shouldReturnDayOfWeekForFriday() throws Exception {
		final Calendar cal = createCalendarForDateAtMidnight(2006, Calendar.OCTOBER, 13);
		final LocalDate date = LocalDate.onDate(cal.getTime());
		assertEquals(5, date.getDayOfWeek());
		assertEquals(LocalDate.Day.FRIDAY, date.day());
	}

	@Test
	public void shouldReturnDayOfWeekForSaturday() throws Exception {
		final Calendar cal = createCalendarForDateAtMidnight(2006, Calendar.OCTOBER, 14);
		final LocalDate date = LocalDate.onDate(cal.getTime());
		assertEquals(6, date.getDayOfWeek());
		assertEquals(LocalDate.Day.SATURDAY, date.day());
	}

	@Test
	public void shouldReturnCorrectDateWhenAddingDays() throws Exception {
		assertEquals(LocalDate.on(2001, 10, 25), LocalDate.on(2001, 10, 24).plusDays(1));
		assertEquals(LocalDate.on(2001, 10, 26), LocalDate.on(2001, 10, 24).plusDays(2));
		assertEquals(LocalDate.on(2002, 10, 24), LocalDate.on(2001, 10, 24).plusDays(365));
	}

	@Test
	public void shouldIncreaseMonthWhenAddingDaysPastMonthsEnd() throws Exception {
		assertEquals(LocalDate.on(2001, 2, 1), LocalDate.on(2001, 1, 20).plusDays(12));
		assertEquals(LocalDate.on(2001, 3, 4), LocalDate.on(2001, 2, 20).plusDays(12));
		assertEquals(LocalDate.on(2001, 4, 1), LocalDate.on(2001, 3, 20).plusDays(12));
		assertEquals(LocalDate.on(2001, 5, 2), LocalDate.on(2001, 4, 20).plusDays(12));
		assertEquals(LocalDate.on(2001, 6, 1), LocalDate.on(2001, 5, 20).plusDays(12));
		assertEquals(LocalDate.on(2001, 7, 2), LocalDate.on(2001, 6, 20).plusDays(12));
		assertEquals(LocalDate.on(2001, 8, 1), LocalDate.on(2001, 7, 20).plusDays(12));
		assertEquals(LocalDate.on(2001, 9, 1), LocalDate.on(2001, 8, 20).plusDays(12));
		assertEquals(LocalDate.on(2001, 10, 2), LocalDate.on(2001, 9, 20).plusDays(12));
		assertEquals(LocalDate.on(2001, 11, 1), LocalDate.on(2001, 10, 20).plusDays(12));
		assertEquals(LocalDate.on(2001, 12, 2), LocalDate.on(2001, 11, 20).plusDays(12));
		assertEquals(LocalDate.on(2002, 1, 1), LocalDate.on(2001, 12, 20).plusDays(12));
	}

	@Test
	public void shouldAccountForLeapYearsWhenAddingDaysOverFebruary() throws Exception {
		assertEquals(LocalDate.on(1996, 3, 1), LocalDate.on(1996, 2, 25).plusDays(5));
		assertEquals(LocalDate.on(1998, 3, 2), LocalDate.on(1998, 2, 25).plusDays(5));
		assertEquals(LocalDate.on(2000, 3, 1), LocalDate.on(2000, 2, 25).plusDays(5));
		assertEquals(LocalDate.on(1900, 3, 2), LocalDate.on(1900, 2, 25).plusDays(5));
		assertEquals(LocalDate.on(1996, 2, 29), LocalDate.on(1996, 2, 25).plusDays(4));
		assertEquals(LocalDate.on(1997, 3, 1), LocalDate.on(1997, 2, 25).plusDays(4));
	}

	@Test
	public void shouldIncreaseYearWhenAddingDaysPastYearsEnd() throws Exception {
		assertEquals(LocalDate.on(2002, 1, 9), LocalDate.on(2001, 12, 20).plusDays(20));
	}

	@Test
	public void shouldReturnCorrectDateWhenAddingFixedDuration() {
		assertEquals(LocalDate.on(2001, 10, 25), LocalDate.on(2001, 10, 24).plusDuration(Duration.ofDays(1)));
		assertEquals(LocalDate.on(2001, 10, 26), LocalDate.on(2001, 10, 24).plusDuration(Duration.ofDays(2)));
		assertEquals(LocalDate.on(2002, 10, 24), LocalDate.on(2001, 10, 24).plusDuration(Duration.ofDays(365)));
	}

	@Test
	public void shouldReturnInfinteLocalDateWhenAddingInfiniteDuration() {
		assertEquals(LocalDate.infinite(), LocalDate.today().plusDuration(Duration.infinite()));
	}

	@Test
	public void shouldReturnCorrectDateWhenSubtractingDays() throws Exception {
		assertEquals(LocalDate.on(2001, 10, 24), LocalDate.on(2001, 10, 25).minusDays(1));
		assertEquals(LocalDate.on(2001, 10, 24), LocalDate.on(2001, 10, 26).minusDays(2));
		assertEquals(LocalDate.on(2001, 10, 24), LocalDate.on(2002, 10, 24).minusDays(365));
	}

	@Test
	public void shouldIncreaseMonthWhenSubtractingDaysPastMonthsEnd() throws Exception {
		assertEquals(LocalDate.on(2001, 1, 20), LocalDate.on(2001, 2, 1).minusDays(12));
		assertEquals(LocalDate.on(2001, 2, 17), LocalDate.on(2001, 3, 1).minusDays(12));
		assertEquals(LocalDate.on(2001, 3, 20), LocalDate.on(2001, 4, 1).minusDays(12));
		assertEquals(LocalDate.on(2001, 4, 19), LocalDate.on(2001, 5, 1).minusDays(12));
		assertEquals(LocalDate.on(2001, 5, 20), LocalDate.on(2001, 6, 1).minusDays(12));
		assertEquals(LocalDate.on(2001, 6, 19), LocalDate.on(2001, 7, 1).minusDays(12));
		assertEquals(LocalDate.on(2001, 7, 20), LocalDate.on(2001, 8, 1).minusDays(12));
		assertEquals(LocalDate.on(2001, 8, 20), LocalDate.on(2001, 9, 1).minusDays(12));
		assertEquals(LocalDate.on(2001, 9, 19), LocalDate.on(2001, 10, 1).minusDays(12));
		assertEquals(LocalDate.on(2001, 10, 20), LocalDate.on(2001, 11, 1).minusDays(12));
		assertEquals(LocalDate.on(2001, 11, 19), LocalDate.on(2001, 12, 1).minusDays(12));
		assertEquals(LocalDate.on(2001, 12, 20), LocalDate.on(2002, 1, 1).minusDays(12));
	}

	@Test
	public void shouldAccountForLeapYearsWhenSubtractingDaysInFebruary() throws Exception {
		assertEquals(LocalDate.on(1996, 2, 19), LocalDate.on(1996, 3, 1).minusDays(11));
		assertEquals(LocalDate.on(1998, 2, 18), LocalDate.on(1998, 3, 1).minusDays(11));
		assertEquals(LocalDate.on(2000, 2, 19), LocalDate.on(2000, 3, 1).minusDays(11));
		assertEquals(LocalDate.on(1900, 2, 18), LocalDate.on(1900, 3, 1).minusDays(11));
		assertEquals(LocalDate.on(1996, 2, 29), LocalDate.on(1996, 3, 4).minusDays(4));
		assertEquals(LocalDate.on(1997, 2, 28), LocalDate.on(1997, 3, 4).minusDays(4));
	}

	@Test
	public void shouldDecreaseYearWhenSubtractingDaysPastYearsEnd() throws Exception {
		assertEquals(LocalDate.on(2001, 12, 20), LocalDate.on(2002, 1, 9).minusDays(20));
	}

	@Test
	public void shouldReturnCorrectDateWhenAddingMonths() throws Exception {
		assertEquals(LocalDate.on(2002, 5, 24), LocalDate.on(2002, 4, 24).plusMonths(1));
		assertEquals(LocalDate.on(2002, 6, 10), LocalDate.on(2002, 4, 10).plusMonths(2));
		assertEquals(LocalDate.on(2004, 4, 15), LocalDate.on(2004, 2, 15).plusMonths(2));
		assertEquals(LocalDate.on(2003, 1, 2), LocalDate.on(2002, 6, 2).plusMonths(7));
		assertEquals(LocalDate.on(2004, 4, 18), LocalDate.on(2003, 4, 18).plusMonths(12));
	}

	@Test
	public void shouldReturnCorrectDateWhenSubtractingMonths() throws Exception {
		assertEquals(LocalDate.on(2002, 4, 24), LocalDate.on(2002, 5, 24).minusMonths(1));
		assertEquals(LocalDate.on(2002, 4, 10), LocalDate.on(2002, 6, 10).minusMonths(2));
		assertEquals(LocalDate.on(2004, 2, 15), LocalDate.on(2004, 4, 15).minusMonths(2));
		assertEquals(LocalDate.on(2002, 6, 2), LocalDate.on(2003, 1, 2).minusMonths(7));
		assertEquals(LocalDate.on(2003, 4, 18), LocalDate.on(2004, 4, 18).minusMonths(12));
	}

	@Test
	public void shouldReturnTrueWhenIsAWeekend() {
		assertEquals(false, LocalDate.on(2006, 11, 13).isWeekend());
		assertEquals(false, LocalDate.on(2006, 11, 14).isWeekend());
		assertEquals(false, LocalDate.on(2006, 11, 15).isWeekend());
		assertEquals(false, LocalDate.on(2006, 11, 16).isWeekend());
		assertEquals(false, LocalDate.on(2006, 11, 17).isWeekend());
		assertEquals(true, LocalDate.on(2006, 11, 18).isWeekend());
		assertEquals(true, LocalDate.on(2006, 11, 19).isWeekend());
	}

	@Test
	public void shouldReturnTrueForLastDayOfMonth() throws Exception {
		assertTrue(FiniteLocalDate.on(2007, 1, 31).isLastDayOfMonth());
		assertTrue(FiniteLocalDate.on(2007, 2, 28).isLastDayOfMonth());
		assertTrue(FiniteLocalDate.on(2004, 2, 29).isLastDayOfMonth());
		assertTrue(FiniteLocalDate.on(2007, 3, 31).isLastDayOfMonth());
		assertTrue(FiniteLocalDate.on(2007, 4, 30).isLastDayOfMonth());
		assertTrue(FiniteLocalDate.on(2007, 5, 31).isLastDayOfMonth());
		assertTrue(FiniteLocalDate.on(2007, 6, 30).isLastDayOfMonth());
		assertTrue(FiniteLocalDate.on(2007, 7, 31).isLastDayOfMonth());
		assertTrue(FiniteLocalDate.on(2007, 8, 31).isLastDayOfMonth());
		assertTrue(FiniteLocalDate.on(2007, 9, 30).isLastDayOfMonth());
		assertTrue(FiniteLocalDate.on(2007, 10, 31).isLastDayOfMonth());
		assertTrue(FiniteLocalDate.on(2007, 11, 30).isLastDayOfMonth());
		assertTrue(FiniteLocalDate.on(2007, 12, 31).isLastDayOfMonth());
		assertFalse(FiniteLocalDate.on(2004, 2, 28).isLastDayOfMonth());
		assertFalse(FiniteLocalDate.on(2007, 12, 30).isLastDayOfMonth());
		assertFalse(FiniteLocalDate.on(2007, 1, 1).isLastDayOfMonth());
	}

	@Test
	public void shouldReturnTrueForFirstDayOfMonth() throws Exception {
		assertTrue(FiniteLocalDate.on(2007, 1, 1).isFirstDayOfMonth());
		assertTrue(FiniteLocalDate.on(2007, 2, 1).isFirstDayOfMonth());
		assertTrue(FiniteLocalDate.on(2004, 2, 1).isFirstDayOfMonth());
		assertTrue(FiniteLocalDate.on(2007, 3, 1).isFirstDayOfMonth());
		assertTrue(FiniteLocalDate.on(2007, 4, 1).isFirstDayOfMonth());
		assertTrue(FiniteLocalDate.on(2007, 5, 1).isFirstDayOfMonth());
		assertTrue(FiniteLocalDate.on(2007, 6, 1).isFirstDayOfMonth());
		assertTrue(FiniteLocalDate.on(2007, 7, 1).isFirstDayOfMonth());
		assertTrue(FiniteLocalDate.on(2007, 8, 1).isFirstDayOfMonth());
		assertTrue(FiniteLocalDate.on(2007, 9, 1).isFirstDayOfMonth());
		assertTrue(FiniteLocalDate.on(2007, 10, 1).isFirstDayOfMonth());
		assertTrue(FiniteLocalDate.on(2007, 11, 1).isFirstDayOfMonth());
		assertTrue(FiniteLocalDate.on(2007, 12, 1).isFirstDayOfMonth());
		assertFalse(FiniteLocalDate.on(2004, 2, 28).isFirstDayOfMonth());
		assertFalse(FiniteLocalDate.on(2004, 2, 29).isFirstDayOfMonth());
		assertFalse(FiniteLocalDate.on(2007, 12, 30).isFirstDayOfMonth());
		assertFalse(FiniteLocalDate.on(2007, 1, 31).isFirstDayOfMonth());
	}

	@Test
	public void shouldReturnCorrectResultForIsAfterComparison() throws Exception {
		assertFalse(LocalDate.on(1935, 1, 8).isAfter(LocalDate.on(1935, 1, 8)));
		assertTrue(LocalDate.on(1935, 1, 9).isAfter(LocalDate.on(1935, 1, 8)));
		assertFalse(LocalDate.on(1935, 1, 8).isAfter(LocalDate.on(1935, 1, 9)));
		assertTrue(LocalDate.on(1935, 2, 8).isAfter(LocalDate.on(1935, 1, 8)));
		assertFalse(LocalDate.on(1935, 1, 8).isAfter(LocalDate.on(1935, 2, 8)));
		assertTrue(LocalDate.on(1936, 1, 8).isAfter(LocalDate.on(1935, 1, 8)));
		assertFalse(LocalDate.on(1935, 1, 8).isAfter(LocalDate.on(1936, 1, 8)));
		assertFalse(LocalDate.today().isAfter(LocalDate.infinite()));
	}

	@Test
	public void shouldReturnCorrectResultForIsBeforeComparison() throws Exception {
		assertFalse(LocalDate.on(1935, 1, 8).isBefore(LocalDate.on(1935, 1, 8)));
		assertTrue(LocalDate.on(1935, 1, 8).isBefore(LocalDate.on(1935, 1, 9)));
		assertFalse(LocalDate.on(1935, 1, 9).isBefore(LocalDate.on(1935, 1, 8)));
		assertTrue(LocalDate.on(1935, 1, 8).isBefore(LocalDate.on(1935, 2, 8)));
		assertFalse(LocalDate.on(1935, 2, 8).isBefore(LocalDate.on(1935, 1, 8)));
		assertTrue(LocalDate.on(1935, 1, 8).isBefore(LocalDate.on(1936, 1, 8)));
		assertFalse(LocalDate.on(1936, 1, 8).isBefore(LocalDate.on(1935, 1, 8)));
		assertTrue(LocalDate.today().isBefore(LocalDate.infinite()));
	}

	@Test
	public void shouldReturnCorrectResultForIsOnOrAfterComparison() throws Exception {
		assertTrue(LocalDate.on(1935, 1, 8).isOnOrAfter(LocalDate.on(1935, 1, 8)));
		assertTrue(LocalDate.on(1935, 1, 9).isOnOrAfter(LocalDate.on(1935, 1, 8)));
		assertFalse(LocalDate.on(1935, 1, 8).isOnOrAfter(LocalDate.on(1935, 1, 9)));
		assertTrue(LocalDate.on(1935, 2, 8).isOnOrAfter(LocalDate.on(1935, 1, 8)));
		assertFalse(LocalDate.on(1935, 1, 8).isOnOrAfter(LocalDate.on(1935, 2, 8)));
		assertTrue(LocalDate.on(1936, 1, 8).isOnOrAfter(LocalDate.on(1935, 1, 8)));
		assertFalse(LocalDate.on(1935, 1, 8).isOnOrAfter(LocalDate.on(1936, 1, 8)));
	}

	@Test
	public void shouldReturnCorrectResultForIsOnOrBeforeComparison() throws Exception {
		assertTrue(LocalDate.on(1935, 1, 8).isOnOrBefore(LocalDate.on(1935, 1, 8)));
		assertTrue(LocalDate.on(1935, 1, 8).isOnOrBefore(LocalDate.on(1935, 1, 9)));
		assertFalse(LocalDate.on(1935, 1, 9).isOnOrBefore(LocalDate.on(1935, 1, 8)));
		assertTrue(LocalDate.on(1935, 1, 8).isOnOrBefore(LocalDate.on(1935, 2, 8)));
		assertFalse(LocalDate.on(1935, 2, 8).isOnOrBefore(LocalDate.on(1935, 1, 8)));
		assertTrue(LocalDate.on(1935, 1, 8).isOnOrBefore(LocalDate.on(1936, 1, 8)));
		assertFalse(LocalDate.on(1936, 1, 8).isOnOrBefore(LocalDate.on(1935, 1, 8)));
	}

	@Test
	public void ShouldReturnTrueFromAfterNowForFutureDates() throws Exception {
		final LocalDateTime dateTime = LocalDateTime.at(1985, 7, 13, 9, 30, 0);
		LocalDateTime.setSystemDateTime(dateTime);
		assertTrue(LocalDate.on(1985, 7, 14).isAfterNow());
		assertTrue(LocalDate.on(1985, 8, 13).isAfterNow());
		assertTrue(LocalDate.on(1986, 7, 13).isAfterNow());
		assertFalse(LocalDate.on(1985, 7, 13).isAfterNow());
	}

	@Test
	public void ShouldReturnTrueFromBeforeNowForPastDates() throws Exception {
		final LocalDateTime dateTime = LocalDateTime.at(1985, 7, 13, 9, 30, 0);
		LocalDateTime.setSystemDateTime(dateTime);
		assertTrue(LocalDate.on(1985, 7, 12).isBeforeNow());
		assertTrue(LocalDate.on(1985, 6, 13).isBeforeNow());
		assertTrue(LocalDate.on(1984, 7, 13).isBeforeNow());
		assertFalse(LocalDate.on(1985, 7, 13).isBeforeNow());
	}

	@Test
	public void shouldCompareNegativeIfFirstDateIsEarlier() throws Exception {
		assertTrue(LocalDate.on(1985, 6, 12).compareTo(LocalDate.on(1985, 7, 12)) < 0);
	}

	@Test
	public void shouldComparePositiveIfFirstDateIsLater() throws Exception {
		assertTrue(LocalDate.on(1985, 7, 12).compareTo(LocalDate.on(1985, 6, 12)) > 0);
	}

	@Test
	public void shouldCompareZeroIfDatesAreEqual() throws Exception {
		assertTrue(LocalDate.on(1985, 7, 12).compareTo(LocalDate.on(1985, 7, 12)) == 0);
	}

	@Test
	public void shouldCompareNegativeWhenFixedDateComparedWithInfiniteDate() throws Exception {
		assertTrue(LocalDate.today().compareTo(LocalDate.infinite()) < 0);
	}

	@Test
	public void shouldReturnDistanceBetweenTwoDates() throws Exception {
		assertEquals(Duration.ofDays(0), LocalDate.on(1963, 11, 22).durationUntil(LocalDate.on(1963, 11, 22)));
		assertEquals(Duration.ofDays(5), LocalDate.on(1963, 11, 22).durationUntil(LocalDate.on(1963, 11, 27)));
		assertEquals(Duration.ofDays(366), LocalDate.on(1963, 11, 22).durationUntil(LocalDate.on(1964, 11, 22)));
		assertEquals(Duration.ofDays(2067), LocalDate.on(1963, 11, 22).durationUntil(LocalDate.on(1969, 7, 20)));
		assertEquals(Duration.infinite(), LocalDate.on(1963, 11, 22).durationUntil(LocalDate.infinite()));
	}

	@Test
	public void shouldBeEqualToEquivalentLocalDate() throws Exception {
		assertEquals(LocalDate.on(2001, Date.Month.OCTOBER, 25), LocalDate.on(2001, 10, 25));
	}

	@Test
	public void shouldNotEqualToDifferentLocalDate() throws Exception {
		assertFalse("Dates should not be equal", LocalDate.on(2001, 10, 25).equals(LocalDate.on(2001, 10, 26)));
		assertFalse("Dates should not be equal", LocalDate.on(2001, 11, 25).equals(LocalDate.on(2001, 10, 25)));
		assertFalse("Dates should not be equal", LocalDate.on(2001, Date.Month.SEPTEMBER, 25).equals(
				LocalDate.on(2001, 10, 25)));
		assertFalse("Dates should not be equal", LocalDate.on(2002, 10, 25).equals(LocalDate.on(2001, 10, 25)));
	}

	@Test
	public void shouldNotBeEquivalentWithOtherTypeOfObject() throws Exception {
		final LocalDate date = LocalDate.on(2001, 10, 25);
		final java.util.Date javaDate = createCalendarForDateAtMidnight(2001, 10, 25).getTime();
		assertFalse("Date should not be equivalent with other object", date.equals(javaDate));
	}

	@Test
	public void shouldNotBeEquivalentWithNull() throws Exception {
		final LocalDate date = LocalDate.on(2001, 10, 25);
		assertFalse("Date should not be equivalent with null", date.equals(null));
	}

	@Test
	public void shouldHaveSameHashCodeAsEquivalentDate() throws Exception {
		assertEquals(LocalDate.on(1985, 7, 13).hashCode(), LocalDate.on(1985, 7, 13).hashCode());
		assertEquals(LocalDate.infinite().hashCode(), LocalDate.infinite().hashCode());
	}

	@Test
	public void shouldFormatDateIntoStringUsingPattern() throws Exception {
		LocalDate date = LocalDate.on(1977, 12, 22);
		assertEquals("22/12/1977", date.format("dd/MM/yyyy"));
	}

	@Test
	public void shouldReturnFormattedStringFromToString() throws Exception {
		assertEquals("Sat Jul 13 1985", LocalDate.on(1985, 7, 13).toString());
		assertEquals("Tue Nov 02 2010", LocalDate.on(2010, 11, 2).toString());
		assertEquals("Thu Oct 25 2001", LocalDate.on(2001, 10, 25).toString());
		assertEquals("Wed Jan 15 1992", LocalDate.on(1992, 1, 15).toString());
		assertEquals("Sun Feb 29 2004", LocalDate.on(2004, 2, 29).toString());
	}

	@Test
	public void shouldParseStringDateAndReturnLocalDateRepresentation() throws Exception {
		assertEquals(FiniteLocalDate.on(1977, 12, 22), FiniteLocalDate.parseDate("22/12/1977", "dd/MM/yyyy"));
	}

	@Test
	public void shouldParseStringDateAndReturnNullWhenDateStringIsEmpty() throws Exception {
		assertNull(FiniteLocalDate.parseDate("", "dd/MM/yyyy"));
	}

	@Test
	public void shouldParseStringDateAndReturnNullWhenDateStringIsNull() throws Exception {
		assertNull(FiniteLocalDate.parseDate(null, "dd/MM/yyyy"));
	}

	@Test
	public void shouldParseStringDateAndReturnNullWhenDateStringIsInInvalidFormat() throws Exception {
		assertNull(FiniteLocalDate.parseDate("12/22/1977", "dd/MM/yyyy"));
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

	private Calendar createCalendarForDateTime(int year, int month, int day, int hour, int minute, int second) {
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
