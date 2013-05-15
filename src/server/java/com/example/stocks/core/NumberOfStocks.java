package com.example.stocks.core;

public class NumberOfStocks implements Position {

    private final Symbol stock;
    private final Integer quantity;

    public NumberOfStocks(Symbol stock, Integer quantity) {
        this.stock = stock;
        this.quantity = quantity;
    }

    @Override
    public Money value(MarketData marketData) {
        return marketData.getPrice(stock).multiply(quantity);
    }
}
