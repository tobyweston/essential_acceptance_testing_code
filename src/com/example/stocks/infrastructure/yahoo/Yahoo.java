package com.example.stocks.infrastructure.yahoo;

public interface Yahoo {
    public String executeQuery(String yql, YahooResponseFormat format);
}
