package com.example.stocks.core;

public class NumberOfStocks implements Position {

    private final Symbol stock;
    private final Integer numberOfUnits;

    public NumberOfStocks(Symbol stock, Integer numberOfUnits) {
        this.stock = stock;
        this.numberOfUnits = numberOfUnits;
    }

    @Override
    public Money value(MarketData marketData) {
        return marketData.getPrice(stock).multiply(numberOfUnits);
    }
}
