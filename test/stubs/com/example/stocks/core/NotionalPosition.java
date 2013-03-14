package com.example.stocks.core;

public class NotionalPosition implements Position {

    private final Symbol stock;

    public NotionalPosition(Symbol stock) {
        this.stock = stock;
    }

    @Override
    public Money value(MarketData marketData) {
        return marketData.getPrice(stock);
    }
}
