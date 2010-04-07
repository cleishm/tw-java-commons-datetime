package com.thoughtworks.util;

import static junit.framework.Assert.*;

import org.junit.Test;

public class InfiniteLocalDateTest {

	@Test
	public void shouldThrowIllegalStateExceptionWhenGetDateCalled() throws Exception {
		try {
			LocalDate.infinite().getDate();
			fail("expected IllegalStateException");
		} catch (final IllegalStateException e) {
			assertTrue(e.getMessage().contains("infinite"));
		}
	}

	@Test
	public void shouldThrowIllegalStateExceptionWhenToFiniteLocalDateCalled() throws Exception {
		try {
			LocalDate.infinite().toFiniteLocalDate();
			fail("expected IllegalStateException");
		} catch (final IllegalStateException e) {
			assertTrue(e.getMessage().contains("infinite"));
		}
	}

	@Test
	public void shouldThrowIllegalStateExceptionWhenToCalendarCalledOnInfiniteLocalDate() throws Exception {
		try {
			LocalDate.infinite().toCalendar();
			fail("expected IllegalStateException");
		} catch (final IllegalStateException e) {
			assertTrue(e.getMessage().contains("infinite"));
		}
	}

	@Test
	public void shouldReturnTrueFromIsInfinite() throws Exception {
		assertTrue(LocalDate.infinite().isInfinite());
	}

	@Test
	public void shouldThrowIllegalStateExceptionForGetWeekOfYear() throws Exception {
		try {
			LocalDate.infinite().getWeekOfYear();
			fail("expected IllegalStateException");
		} catch (final IllegalStateException e) {
			assertTrue(e.getMessage().contains("infinite"));
		}
	}

	@Test
	public void shouldThrowIllegalStateExceptionForGetDayOfWeek() throws Exception {
		try {
			LocalDate.infinite().getDayOfWeek();
			fail("expected IllegalStateException");
		} catch (final IllegalStateException e) {
			assertTrue(e.getMessage().contains("infinite"));
		}
	}

	@Test
	public void shouldThrowIllegalStateExceptionForGetDayOfMonth() throws Exception {
		try {
			LocalDate.infinite().getDayOfMonth();
			fail("expected IllegalStateException");
		} catch (final IllegalStateException e) {
			assertTrue(e.getMessage().contains("infinite"));
		}
	}

	@Test
	public void shouldThrowIllegalStateExceptionForGetMonthOfYear() throws Exception {
		try {
			LocalDate.infinite().getMonthOfYear();
			fail("expected IllegalStateException");
		} catch (final IllegalStateException e) {
			assertTrue(e.getMessage().contains("infinite"));
		}
	}

	@Test
	public void shouldThrowIllegalStateExceptionForDay() throws Exception {
		try {
			LocalDate.infinite().day();
			fail("expected IllegalStateException");
		} catch (final IllegalStateException e) {
			assertTrue(e.getMessage().contains("infinite"));
		}
	}

	@Test
	public void shouldThrowIllegalStateExceptionForMonth() throws Exception {
		try {
			LocalDate.infinite().month();
			fail("expected IllegalStateException");
		} catch (final IllegalStateException e) {
			assertTrue(e.getMessage().contains("infinite"));
		}
	}

	@Test
	public void shouldThrowIllegalStateExceptionForGetYear() throws Exception {
		try {
			LocalDate.infinite().getYear();
			fail("expected IllegalStateException");
		} catch (final IllegalStateException e) {
			assertTrue(e.getMessage().contains("infinite"));
		}
	}

	@Test
	public void shouldThrowIllegalStateExceptionForIsWeekend() throws Exception {
		try {
			LocalDate.infinite().isWeekend();
			fail("expected IllegalStateException");
		} catch (final IllegalStateException e) {
			assertTrue(e.getMessage().contains("infinite"));
		}
	}

	@Test
	public void shouldReturnInfiniteDateForPlusDays() throws Exception {
		assertEquals(LocalDate.infinite(), LocalDate.infinite().plusDays(23));
	}

	@Test
	public void shouldReturnInfiniteDateForMinusDays() throws Exception {
		assertEquals(LocalDate.infinite(), LocalDate.infinite().minusDays(23));
	}

	@Test
	public void shouldReturnInfiniteDateForPlusMonths() throws Exception {
		assertEquals(LocalDate.infinite(), LocalDate.infinite().plusMonths(3));
	}

	@Test
	public void shouldReturnInfiniteDateForMinusMonths() throws Exception {
		assertEquals(LocalDate.infinite(), LocalDate.infinite().minusMonths(23));
	}

	@Test
	public void shouldReturnCorrectResultForIsAfterComparison() throws Exception {
		assertTrue(LocalDate.infinite().isAfter(LocalDate.daysAfterToday(35)));
		assertTrue(LocalDate.infinite().isAfter(LocalDate.daysAfterToday(1000000)));
		assertTrue(LocalDate.infinite().isAfter(LocalDate.today()));
		assertFalse(LocalDate.infinite().isAfter(LocalDate.infinite()));
	}

	@Test
	public void shouldReturnCorrectResultForIsBeforeComparison() throws Exception {
		assertFalse(LocalDate.infinite().isBefore(LocalDate.daysBeforeToday(4)));
		assertFalse(LocalDate.infinite().isBefore(LocalDate.daysAfterToday(4)));
		assertFalse(LocalDate.infinite().isBefore(LocalDate.today()));
		assertFalse(LocalDate.infinite().isBefore(LocalDate.infinite()));
	}

	@Test
	public void shouldReturnCorrectResultForIsOnOrAfterComparison() throws Exception {
		assertTrue(LocalDate.infinite().isOnOrAfter(LocalDate.daysAfterToday(1)));
		assertTrue(LocalDate.infinite().isOnOrAfter(LocalDate.daysAfterToday(1123123)));
		assertTrue(LocalDate.infinite().isOnOrAfter(LocalDate.today()));
		assertTrue(LocalDate.infinite().isOnOrAfter(LocalDate.daysBeforeToday(1231232)));
		assertTrue(LocalDate.infinite().isOnOrAfter(LocalDate.infinite()));
	}

	@Test
	public void shouldReturnCorrectResultForIsOnOrBeforeComparison() throws Exception {
		assertFalse(LocalDate.infinite().isOnOrBefore(LocalDate.daysAfterToday(1)));
		assertFalse(LocalDate.infinite().isOnOrBefore(LocalDate.daysAfterToday(1123123)));
		assertFalse(LocalDate.infinite().isOnOrBefore(LocalDate.today()));
		assertFalse(LocalDate.infinite().isOnOrBefore(LocalDate.daysBeforeToday(1231232)));
		assertTrue(LocalDate.infinite().isOnOrBefore(LocalDate.infinite()));
	}

	@Test
	public void shouldReturnTrueForIsAfterNow() {
		assertTrue(LocalDate.infinite().isAfterNow());
	}

	@Test
	public void shouldReturnFalseForIsBeforeNow() throws Exception {
		assertFalse(LocalDate.infinite().isBeforeNow());
	}

	@Test
	public void shouldCompareZeroWhenBothDatesAreInfinite() throws Exception {
		assertEquals(0, LocalDate.infinite().compareTo(LocalDate.infinite()));
	}

	@Test
	public void shouldComparePositiveValueWhenInfiniteDateComparedWithFixedDate() throws Exception {
		assertTrue(LocalDate.infinite().compareTo(LocalDate.today()) > 0);
	}

	@Test
	public void shouldThrowIllegalArgumentExceptionFromDurationUntilIfTargetDateIsBefore() throws Exception {
		try {
			LocalDate.today().durationUntil(LocalDate.daysBeforeToday(1));
			fail("expected exception");
		} catch (final IllegalArgumentException e) {
			assertTrue(e.getMessage().contains("earlier"));
		}
	}

	@Test
	public void shouldThrowIllegalStateExceptionFromIsFirstDayOfMonth() throws Exception {
		try {
			InfiniteLocalDate.INSTANCE.isFirstDayOfMonth();
			fail("expected exception");
		} catch (final IllegalStateException e) {
			assertTrue(true);
		}
	}

	@Test
	public void shouldThrowIllegalStateExceptionFromIslastDayOfMonth() throws Exception {
		try {
			InfiniteLocalDate.INSTANCE.isLastDayOfMonth();
			fail("expected exception");
		} catch (final IllegalStateException e) {
			assertTrue(true);
		}
	}

	@Test
	public void shouldReturnNAWhenFormatingInfiniteDate() throws Exception {
		assertEquals("N/A", LocalDate.infinite().format("dd/MM/yyyy"));
	}

	@Test
	public void shouldBeEqualIfBothDatesInfinite() throws Exception {
		assertEquals(LocalDate.infinite(), LocalDate.infinite());
	}

	@Test
	public void shouldNotBeEqualIfOnlyOneDateIsInfinite() throws Exception {
		assertFalse(LocalDate.infinite().equals(LocalDate.today()));
	}

	@Test
	public void shouldReturnSameHashCodeForInfiniteDates() throws Exception {
		assertEquals(LocalDate.infinite().hashCode(), LocalDate.infinite().hashCode());
	}

	@Test
	public void shouldReturnInfiniteForToString() throws Exception {
		assertEquals("infinite", LocalDate.infinite().toString());
	}
}
