package com.example.stocks.infrastructure.yahoo;

import com.example.stocks.core.Money;
import com.example.stocks.core.Stock;
import com.example.stocks.util.Json;

public class YahooStockQuote implements Stock {

    private final Json quote;

    public YahooStockQuote(String json) {
        this.quote = new Json(json).getObject("query").getObject("results").getObject("quote");
    }

    @Override
    public Money getClosingPrice() {
        return new Money(quote.getString("Close"));
    }
}
