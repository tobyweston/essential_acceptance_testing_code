package com.example.stocks.infrastructure.yahoo;

import com.example.stocks.core.Price;
import com.example.stocks.core.StockQuote;
import com.example.stocks.util.Json;

public class YahooStockQuote implements StockQuote {

    private final Json quote;

    public YahooStockQuote(String json) {
        this.quote = new Json(json).getObject("query").getObject("results").getObject("quote");
    }

    @Override
    public Price getClosingPrice() {
        return new Price(quote.getString("Close"));
    }
}
