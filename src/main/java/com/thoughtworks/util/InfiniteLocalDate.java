package com.thoughtworks.util;

import java.util.Calendar;

public final class InfiniteLocalDate extends LocalDate
{
    protected static final InfiniteLocalDate INSTANCE = new InfiniteLocalDate();

    private InfiniteLocalDate()
    {
    }

    public java.util.Date getDate()
    {
        throw new IllegalStateException("date is infinite");
    }

    @Override
    public FiniteLocalDate toFiniteLocalDate()
    {
        throw new IllegalStateException("date is infinite");
    }

    public Calendar toCalendar()
    {
        throw new IllegalStateException("date is infinite");
    }

    public boolean isInfinite()
    {
        return true;
    }

    public int getWeekOfYear()
    {
        throw new IllegalStateException("date is infinite");
    }

    public int getDayOfWeek()
    {
        throw new IllegalStateException("date is infinite");
    }

    public int getDayOfMonth()
    {
        throw new IllegalStateException("date is infinite");
    }

    public int getMonthOfYear()
    {
        throw new IllegalStateException("date is infinite");
    }

    public Day day()
    {
        throw new IllegalStateException("date is infinite");
    }

    public Month month()
    {
        throw new IllegalStateException("date is infinite");
    }

    public int getYear()
    {
        throw new IllegalStateException("date is infinite");
    }

    @Override
    public LocalDate minusDays(final int days)
    {
        return this;
    }

    @Override
    public LocalDate plusMonths(final int months)
    {
        return this;
    }

    @Override
    public LocalDate minusMonths(final int months)
    {
        return this;
    }

    @Override
    public LocalDate plusDays(final int days)
    {
        return this;
    }

    @Override
    public LocalDate plusDuration(final Duration duration)
    {
        assert duration != null;
        return this;
    }

    public boolean isFirstDayOfMonth()
    {
        throw new IllegalStateException("date is infinite");
    }

    public boolean isLastDayOfMonth()
    {
        throw new IllegalStateException("date is infinite");
    }

    public boolean isWeekend()
    {
        throw new IllegalStateException("date is infinite");
    }

    public boolean isAfter(final Date date)
    {
        return !date.isInfinite();
    }

    public boolean isBefore(final Date date)
    {
        return false;
    }

    public boolean isOnOrAfter(final Date date)
    {
        return !isBefore(date);
    }

    public boolean isOnOrBefore(final Date date)
    {
        return !isAfter(date);
    }

    public boolean isAfterNow()
    {
        return true;
    }

    public boolean isBeforeNow()
    {
        return false;
    }
    
	public boolean isBetween(final Date start, final Date end) {
		return false;
	}

    public int compareTo(final LocalDate other)
    {
        return other.isInfinite() ? 0 : 1;
    }

    @Override
    public Duration durationUntil(final Date date)
    {
        if (isAfter(date))
        {
            throw new IllegalArgumentException("cannot get duration to earlier date");
        }
        return Duration.infinite();
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (obj == this || obj instanceof InfiniteLocalDate)
        {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return 31;
    }

    public String format(final String pattern)
    {
        return "N/A";
    }

    @Override
    public String toString()
    {
        return "infinite";
    }

}
