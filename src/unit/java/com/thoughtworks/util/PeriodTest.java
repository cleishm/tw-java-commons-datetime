package com.thoughtworks.util;

import static junit.framework.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class PeriodTest {

	@Test
	public void shouldHaveSameStartDateAndDurationAsProvidedInConstructor() throws Exception {
		final FiniteLocalDate startDate = LocalDate.today();
		final Duration duration = Duration.ofDays(10);
		final Period period = Period.of(startDate, duration);
		assertEquals(startDate, period.getStartDate());
		assertEquals(duration, period.getDuration());
	}

	@Test
	public void shouldHaveCorrectEndDateWhenConstructedWithFixedStartDateAndFixedDuration() throws Exception {
		final Period period = Period.of(LocalDate.today(), Duration.ofDays(10));
		assertEquals(LocalDate.daysAfterToday(9), period.getEndDate());
	}

	@Test
	public void shouldHaveInfiniteEndDateWhenConstructedWithFixedStartDateAndIndefiniteDuration() throws Exception {
		final Period period = Period.of(LocalDate.today(), Duration.infinite());
		assertEquals(LocalDate.infinite(), period.getEndDate());
	}

	@Test
	public void shouldHaveInfiniteEndDateWhenConstructedWithInfiniteStartDateAndFixedDuration() throws Exception {
		final Period period = Period.of(LocalDate.infinite(), Duration.ofDays(10));
		assertEquals(LocalDate.infinite(), period.getEndDate());
	}

	@Test
	public void shouldHaveInfiniteEndDateWhenConstructedWithInfiniteStartDateAndInfiniteDuration() throws Exception {
		final Period period = Period.of(LocalDate.infinite(), Duration.infinite());
		assertEquals(LocalDate.infinite(), period.getEndDate());
	}

	@Test
	public void shouldHaveSameStartDateAndEndDateAsProvidedInConstructor() throws Exception {
		final FiniteLocalDate startDate = LocalDate.today();
		final FiniteLocalDate endDate = LocalDate.daysAfterToday(10);
		final Period period = Period.of(startDate, endDate);
		assertEquals(startDate, period.getStartDate());
		assertEquals(endDate, period.getEndDate());
	}

	@Test
	public void shouldHaveFixedDurationWhenConstructedWithFixedStartDateAndFixedEndDate() throws Exception {
		final Period period = Period.of(LocalDate.today(), LocalDate.daysAfterToday(10));
		assertEquals(Duration.ofDays(11), period.getDuration());
	}

	@Test
	public void shouldHaveInfiniteDurationWhenConstructedWithFixedStartDateAndInfiniteEndDate() throws Exception {
		final Period period = Period.of(LocalDate.today(), LocalDate.infinite());
		assertEquals(Duration.infinite(), period.getDuration());
	}

	@Test
	public void shouldThrowIllegalArgumentExceptionWhenConstructedWithStartDateAfterEndDate() throws Exception {
		try {
			Period.of(LocalDate.today(), LocalDate.daysBeforeToday(1));
			fail("expected exception");
		} catch (final IllegalArgumentException e) {
			assertTrue(e.getMessage().contains("earlier"));
		}
	}

	@Test
	public void shouldReturnCorrectResultWhenCheckingIfPeriodsOverlap() throws Exception {
		assertTrue(Period.of(LocalDate.today(), LocalDate.today()).isOverlapping(
				Period.of(LocalDate.today(), LocalDate.today())));
		assertTrue(Period.of(LocalDate.today(), LocalDate.daysAfterToday(10)).isOverlapping(
				Period.of(LocalDate.daysBeforeToday(10), LocalDate.today())));
		assertTrue(Period.of(LocalDate.daysBeforeToday(5), LocalDate.daysAfterToday(5)).isOverlapping(
				Period.of(LocalDate.daysBeforeToday(2), LocalDate.today())));
		assertFalse(Period.of(LocalDate.daysBeforeToday(5), LocalDate.daysBeforeToday(1)).isOverlapping(
				Period.of(LocalDate.today(), LocalDate.today())));
		assertFalse(Period.of(LocalDate.today(), LocalDate.daysAfterToday(1)).isOverlapping(
				Period.of(LocalDate.daysAfterToday(2), LocalDate.daysAfterToday(10))));
	}

	@Test
	public void shouldBeEqualToSelf() throws Exception {
		final Period period = Period.of(LocalDate.today(), LocalDate.today());
		assertTrue(period.equals(period));
	}

	@Test
	public void shouldNotBeEqualToDifferentType() throws Exception {
		assertFalse(Period.of(LocalDate.today(), LocalDate.today()).equals(new Object()));
	}

	@Test
	public void shouldEqualPeriodOfSameRange() throws Exception {
		assertTrue(Period.of(LocalDate.today(), LocalDate.today()).equals(
				Period.of(LocalDate.today(), LocalDate.today())));
		assertTrue(Period.of(LocalDate.today(), Duration.ofDays(10)).equals(
				Period.of(LocalDate.today(), LocalDate.daysAfterToday(9))));
		assertTrue(Period.of(LocalDate.today(), Duration.infinite()).equals(
				Period.of(LocalDate.today(), LocalDate.infinite())));
	}

	@Test
	public void shouldNotEqualPeriodOfDifferentRange() throws Exception {
		assertFalse(Period.of(LocalDate.today(), LocalDate.today()).equals(
				Period.of(LocalDate.daysBeforeToday(1), LocalDate.today())));
		assertFalse(Period.of(LocalDate.today(), LocalDate.today()).equals(
				Period.of(LocalDate.today(), LocalDate.daysAfterToday(1))));
		assertFalse(Period.of(LocalDate.today(), Duration.infinite()).equals(
				Period.of(LocalDate.today(), LocalDate.today())));
	}

	@Test
	public void shouldNotBeEqualToPeriodsWithInfiniteDatesAndDifferentDurations() throws Exception {
		assertFalse(Period.of(LocalDate.infinite(), Duration.ofDays(10)).equals(
				Period.of(LocalDate.infinite(), Duration.ofDays(11))));
	}

	@Test
	public void shouldReturnTrueIfPeriodContainsSetOfDates() throws Exception {
		FiniteLocalDate today = LocalDate.today();
		Period period = Period.of(today, today.plusDays(5));
		Set<FiniteLocalDate> dates = new HashSet<FiniteLocalDate>();
		dates.add(today);
		dates.add(today.plusDays(1));
		dates.add(today.plusDays(2));
		dates.add(today.plusDays(3));
		dates.add(today.plusDays(4));
		dates.add(today.plusDays(5));
		assertTrue(period.containsAll(dates));
	}

	@Test
	public void shouldReturnFalseIfPeriodContainsDateBeforeStartDate() throws Exception {
		FiniteLocalDate today = LocalDate.today();
		Period period = Period.of(today, today.plusDays(5));
		Set<LocalDate> dates = new HashSet<LocalDate>();
		dates.add(today.minusDays(1));
		dates.add(today.plusDays(1));
		dates.add(today.plusDays(2));
		dates.add(today.plusDays(3));
		dates.add(today.plusDays(4));
		dates.add(today.plusDays(5));
		assertFalse(period.containsAll(dates));
	}

	@Test
	public void shouldReturnFalseIfPeriodContainsDateAfterEndDate() throws Exception {
		FiniteLocalDate today = LocalDate.today();
		Period period = Period.of(today, today.plusDays(5));
		Set<FiniteLocalDate> dates = new HashSet<FiniteLocalDate>();
		dates.add(today.plusDays(1));
		dates.add(today.plusDays(2));
		dates.add(today.plusDays(3));
		dates.add(today.plusDays(4));
		dates.add(today.plusDays(6));
		assertFalse(period.containsAll(dates));
	}

	@Test
	public void shouldHaveSameHashCodeIfEqual() throws Exception {
		assertEquals(Period.of(LocalDate.today(), LocalDate.today()).hashCode(), Period.of(LocalDate.today(),
				LocalDate.today()).hashCode());
		assertEquals(Period.of(LocalDate.today(), Duration.ofDays(10)).hashCode(), Period.of(LocalDate.today(),
				LocalDate.daysAfterToday(9)).hashCode());
		assertEquals(Period.of(LocalDate.today(), Duration.infinite()).hashCode(), Period.of(LocalDate.today(),
				LocalDate.infinite()).hashCode());
	}
}
