package com.thoughtworks.util;

import static junit.framework.Assert.*;

import org.junit.Test;

public class DurationTest {
	
	@Test
	public void shouldNotBeInfiniteWithDuration() throws Exception {
		final Duration duration = Duration.ofDays(0);
		assertFalse(duration.isInfinite());
	}

	@Test
	public void shouldReturnSameDaysAsConstructedWith() throws Exception {
		Duration duration = Duration.ofDays(14);
		assertFalse(duration.isInfinite());
		assertEquals(new Integer(14), duration.getDays());
	}
	
	@Test
	public void shouldThrowExceptionWhenGettingDaysForInfiniteDuration() throws Exception {
		final Duration duration = Duration.infinite();
		assertTrue(duration.isInfinite());
		try {
			duration.getDays();
			fail("shouldve thrown RuntimeException");
		} catch (RuntimeException e) {
			assertTrue(true);
		}
	}

	@Test
	public void toStringShouldIncludeDaysOrInfinite() throws Exception {
		assertTrue(Duration.ofDays(123).toString().equals("123"));
		assertTrue(Duration.ofDays(1).toString().equals("1"));
		assertTrue(Duration.ofDays(0).toString().equals("0"));
		assertTrue(Duration.infinite().toString().equals("infinite"));
	}

	@Test
	public void shouldBeEquivalentToDurationsWithSameLength() throws Exception {
		final Duration duration = Duration.ofDays(10);
		assertEquals(duration, duration);
		assertEquals(Duration.ofDays(10), Duration.ofDays(10));
		assertEquals(Duration.ofDays(1), Duration.ofDays(1));
		assertEquals(Duration.infinite(), Duration.infinite());
	}

	@Test
	public void shouldNotBeEquivalentToDurationsWithDifferentLength() throws Exception {
		assertFalse(Duration.ofDays(10).equals(Duration.ofDays(11)));
		assertFalse(Duration.ofDays(0).equals(Duration.ofDays(1)));
		assertFalse(Duration.ofDays(10).equals(Duration.infinite()));
		assertFalse(Duration.infinite().equals(Duration.ofDays(10)));
	}

	@Test
	public void shouldNotBeEqualToObjectsOfOtherType() throws Exception {
		assertFalse(Duration.ofDays(10).equals(new Object()));
		assertFalse(Duration.ofDays(10).equals(null));
	}

	@Test
	public void shouldHaveSameHashCodeAsDurationsWithSameLength() throws Exception {
		final Duration duration = Duration.ofDays(10);
		assertEquals(duration.hashCode(), duration.hashCode());
		assertEquals(Duration.ofDays(10).hashCode(), Duration.ofDays(10).hashCode());
		assertEquals(Duration.ofDays(1).hashCode(), Duration.ofDays(1).hashCode());
		assertEquals(Duration.infinite().hashCode(), Duration.infinite().hashCode());
	}

	@Test
	public void shouldCompareAsSmallerThanDurationsWithMoreDays() throws Exception {
		assertTrue(Duration.ofDays(10).compareTo(Duration.ofDays(11)) < 0);
	}

	@Test
	public void shouldCompareAsLargerThanDurationsWithMoreDays() throws Exception {
		assertTrue(Duration.ofDays(10).compareTo(Duration.ofDays(1)) > 0);
	}

	@Test
	public void shouldCompareAsEquivalentToDurationsWithSameDays() throws Exception {
		final Duration duration = Duration.ofDays(10);
		assertTrue(duration.compareTo(duration) == 0);
		assertTrue(Duration.ofDays(20).compareTo(Duration.ofDays(20)) == 0);
	}

	@Test
	public void infiniteDurationShouldAlwaysCompareAsLargerUnlessEquivalent() throws Exception {
		assertTrue(Duration.infinite().compareTo(Duration.ofDays(10)) > 0);
		assertTrue(Duration.ofDays(10).compareTo(Duration.infinite()) < 0);
		assertTrue(Duration.infinite().compareTo(Duration.infinite()) == 0);
	}

	@Test
	public void shouldThrowIllegalArgumentExceptionWhenDaysAreNegative() throws Exception {
		try {
			Duration.ofDays(-1);
			fail("expected exception was not thrown");
		} catch (final IllegalArgumentException e) {
			assertTrue(e.getMessage().contains("negative"));
		}
	}
}
