package com.example.superiortimesystem.datetime

import java.util.Calendar;
import java.text.DecimalFormat;

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
     * @return true if the current year is a leap year, false otherwise
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
    fun decimalTime(): String {
        val secondsPassed: Int = cal.get(Calendar.HOUR_OF_DAY)*3600 + cal.get(Calendar.MINUTE)*60 + cal.get(Calendar.SECOND);
        val percentOfDay: Double = (secondsPassed.toDouble())/(secondsInDay.toDouble());
        return (DecimalFormat("#.##").format((percentOfDay*10))).toString();
    }
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