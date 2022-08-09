package com.example.superiortimesystem.datetime

import java.util.Calendar;
import java.text.DecimalFormat;

class DateTime {
    val cal: Calendar = Calendar.getInstance();
    private val secondsInDay: Int = (60*60)*24; //no, i'm not too lazy to do math
    fun decimalTime(): String {
        val secondsPassed: Int = cal.get(Calendar.HOUR_OF_DAY)*3600 + cal.get(Calendar.MINUTE)*60 + cal.get(Calendar.SECOND);
        val percentOfDay: Double = (secondsPassed.toDouble())/(secondsInDay.toDouble());
        return (DecimalFormat("#.##").format((percentOfDay*10))).toString();
    }
    fun ifcDate(): String {
        return cal.get(Calendar.DAY_OF_YEAR).toString() + " " + cal.get(Calendar.YEAR);
    }
}