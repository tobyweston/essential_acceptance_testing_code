package com.example.stocks.core;

public class StockUnitPosition implements Position {

    private Symbol symbol;
    private Integer numberOfUnits;

    public StockUnitPosition(Symbol symbol, Integer numberOfUnits) {
        this.symbol = symbol;
        this.numberOfUnits = numberOfUnits;
    }

    @Override
    public Money value(MarketData marketData) {
        return marketData.getPrice(symbol).multiply(numberOfUnits);
    }
}
