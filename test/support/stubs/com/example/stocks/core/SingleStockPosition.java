package com.example.stocks.core;

public class SingleStockPosition implements Position {

    private final Symbol stock;

    public SingleStockPosition(Symbol stock) {
        this.stock = stock;
    }

    @Override
    public Money value(MarketData marketData) {
        return marketData.getPrice(stock);
    }
}
