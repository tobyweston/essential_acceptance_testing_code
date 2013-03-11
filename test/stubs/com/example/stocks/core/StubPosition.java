package com.example.stocks.core;

public class StubPosition implements Position {

    private Symbol symbol;

    public StubPosition(Symbol symbol) {
        this.symbol = symbol;
    }

    @Override
    public Money value(MarketData marketData) {
        return marketData.getPrice(symbol);
    }
}
