package com.example.superiortimesystem.datetime

import java.util.Calendar;

/**
 * A class to do conversions of the current date and time
 * into international fixed calendar dates and decimal
 * time time.
 */
class DateTime {
    private val cal: Calendar = Calendar.getInstance();
    private val secondsInDay: Int = (60*60)*24; //no, i'm not too lazy to do math
    private val daysInMonth = 28; // there's 28 days in a month by IFC standards
    private val months: Array<String> = arrayOf<String>(
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "Sol",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    )

    /**
     * Checks if the current year is a leap year
     *
     * Leap years are known to occur every 4 years unless the year is
     * a value divisible by 100 (eg. 1800, 1900) but is still a leap
     * year when the value is divisible by 400 (eg. 2000, 2400).
     *
     * @return `true` if the current year is a leap year, `false` otherwise
     */
    private fun isLeapYear(): Boolean {
        val year: Int = cal.get(Calendar.YEAR);
        return (when {
            year%4 != 0 -> false;
            year%400 == 0 -> true;
            year%100 != 0 -> false;
            else -> true;
        });
    }

    /**
     * Converts current time to decimal time
     *
     * Decimal time is technically just a percentage of how much of the
     * day has passed. To get decimal time, divide the number of seconds
     * that have passed in the day and divide by the number of seconds
     * that a day has to get the percentage.
     *
     *@return the decimal time representation of the current time
     */
    fun decimalTime(): String {
        val secondsPassed: Int = cal.get(Calendar.HOUR_OF_DAY)*3600 + cal.get(Calendar.MINUTE)*60 + cal.get(Calendar.SECOND);
        val percentOfDay: Double = (secondsPassed.toDouble())/(secondsInDay.toDouble());
        return ((percentOfDay*10).toString()).subSequence(0, 4).toString();
    }

    /**
     * Converts the current date to the IFC date
     *
     * Since there are 28 days in each month in a normal year
     * according to IFC, dividing the number of days by 28 will
     * get how many months have passed and the modulus of that
     * will get how many days have passed. After, a few
     * exceptions such as the last day of the month, leap years
     * and the last day of the year must be dealt with.
     *
     * @return a string in the format of "{month} {day}, {year}" of the current date in IFC format
     */
    fun ifcDate(): String {
        val dayOfYear: Int = cal.get(Calendar.DAY_OF_YEAR);
        var dayOfMonth: Int = 0;
        var monthNumber: Int = 0;
        if (isLeapYear() && dayOfYear > 170) {
            monthNumber = (dayOfYear - 1)/daysInMonth;
            dayOfMonth = (dayOfYear - 1)%daysInMonth;
        } else if (isLeapYear() && dayOfYear == 170) {
            monthNumber = 6;
            dayOfMonth = 29;
        } else {
            monthNumber = dayOfYear/daysInMonth;
            dayOfMonth = dayOfYear%daysInMonth;
        }
        if (dayOfMonth == 0) {
            monthNumber -= 1;
            dayOfMonth = daysInMonth;
        }
        if (monthNumber > 12) {
            dayOfMonth += daysInMonth;
            monthNumber -= 1;
        }
        return months[monthNumber] + " " + dayOfMonth + ", " + cal.get(Calendar.YEAR);
    }
}