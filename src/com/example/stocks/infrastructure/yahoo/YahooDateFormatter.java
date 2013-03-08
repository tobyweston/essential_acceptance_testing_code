package com.example.stocks.infrastructure.yahoo;

import com.example.stocks.util.DateFormatter;

public class YahooDateFormatter implements DateFormatter {

    @Override
    public String format(Integer year, Integer month, Integer day) {
        StringBuilder date = new StringBuilder();
        date.append(padZeros(year, 4)).append("-");
        date.append(padZeros(month, 2)).append("-");
        date.append(padZeros(day, 2));
        return date.toString();
    }

    private String padZeros(Integer value, Integer desiredLength) {
        String result = String.valueOf(value);
        while (result.length() < desiredLength) {
            result = "0" + result;
        }
        return result;
    }
}
