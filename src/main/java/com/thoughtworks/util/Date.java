package com.thoughtworks.util;

import java.util.Calendar;

public interface Date {
    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    };

    public enum Month {
        JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
    };

    int getDayOfWeek();

    int getDayOfMonth();

	int getMonthOfYear();

	Day day();

	Month month();

	int getYear();

	java.util.Date getDate();

	Calendar toCalendar();

	boolean isAfter(Date date);

	boolean isBefore(Date date);

	boolean isOnOrAfter(Date date);

	boolean isOnOrBefore(Date date);

	boolean isAfterNow();

	boolean isBeforeNow();
	
	boolean isBetween(Date start, Date end);

	boolean isInfinite();

	String format(String pattern);
}
