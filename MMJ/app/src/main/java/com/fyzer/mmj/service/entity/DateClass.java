package com.fyzer.mmj.service.entity;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by XANXUS on 2017/11/20.
 */

public class DateClass {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private Date today;

    public DateClass() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
        today = calendar.getTime();
    }

    public String getYear() {
        return ""+year;
    }

    public String getMonth() {
        String month = "";
        switch (this.month+1){
            case 1:
                month = "January";
                break;
            case 2:
                month = "February";
                break;
            case 3:
                month = "March";
                break;
            case 4:
                month = "April";
                break;
            case 5:
                month = "May";
                break;
            case 6:
                month = "June";
                break;
            case 7:
                month = "July";
                break;
            case 8:
                month = "August";
                break;
            case 9:
                month = "September";
                break;
            case 10:
                month = "October";
                break;
            case 11:
                month = "November";
                break;
            case 12:
                month = "December";
                break;
        }
        return month;
    }

    public String getDay() {
        return ""+day;
    }

    public String getHour() {
        return ""+hour;
    }

    public String getMinute() {
        return ""+minute;
    }

    public String getSecond() {
        return ""+second;
    }

    public Date getToday() {
        return today;
    }
}
