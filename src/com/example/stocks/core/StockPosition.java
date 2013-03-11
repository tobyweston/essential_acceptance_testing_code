package com.example.stocks.core;

public class StockPosition implements Position {

    private Symbol symbol;
    private Integer amount;

    public StockPosition(Symbol symbol, Integer amount) {
        this.symbol = symbol;
        this.amount = amount;
    }

    @Override
    public Money value(MarketData marketData) {
        return marketData.getPrice(symbol).multiply(amount);
    }
}
