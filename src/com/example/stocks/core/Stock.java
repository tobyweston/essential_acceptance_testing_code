package com.example.stocks.core;

import com.example.stocks.infrastructure.MarketData;
import com.example.stocks.util.Date;

public class Stock {

    private Symbol symbol;

    public Stock(Symbol symbol) {
        this.symbol = symbol;
    }

    public StockQuote getQuote(MarketData marketData, Date now) {
        return marketData.getQuote(symbol, now);
    }
}
