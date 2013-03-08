package com.example.stocks.infrastructure.yahoo;

import com.example.stocks.util.Json;

public class YahooStockQuote implements YahooResponse {

    private final Json quote;

    public YahooStockQuote(Json json) {
        this.quote = json.getObject("query").getObject("results").getObject("quote");
    }

    public String getClosingPrice() {
        return quote.getString("Close");
    }
}
