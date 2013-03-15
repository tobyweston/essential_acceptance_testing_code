package com.example.stocks.infrastructure.yahoo;

import com.example.stocks.core.Money;
import com.example.stocks.core.Stock;
import com.example.stocks.util.Json;

public class YahooStockQuote implements Stock {

    private final Json results;

    public YahooStockQuote(String json) {
        this.results = new Json(json).getObject("query").getObject("results");
    }

    @Override
    public Money getClosingPrice() {
        if (results.isEmpty())
            return new Money(0);
        return new Money(results.getObject("quote").getString("Close"));
    }

    private boolean missing(Json quote) {
        return quote == null;
    }
}
