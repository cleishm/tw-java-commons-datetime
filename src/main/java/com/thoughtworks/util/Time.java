package com.thoughtworks.util;

public interface Time {
    int hourOfDay();

    int minuteOfHour();

    int secondOfMinute();

    boolean isAfterNow();

    boolean isBeforeNow();
}
