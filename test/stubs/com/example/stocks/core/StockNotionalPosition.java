package com.example.stocks.core;

public class StockNotionalPosition implements Position {

    private Symbol symbol;

    public StockNotionalPosition(Symbol symbol) {
        this.symbol = symbol;
    }

    @Override
    public Money value(MarketData marketData) {
        return marketData.getPrice(symbol);
    }
}
