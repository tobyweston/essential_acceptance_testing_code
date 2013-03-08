package com.example.stocks.util;

public class Date {

    private final Integer year;
    private final Integer month;
    private final Integer day;

    public Date(Integer year, Integer month, Integer day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String format(DateFormatter formatter) {
        return formatter.format(year, month, day);
    }
}
