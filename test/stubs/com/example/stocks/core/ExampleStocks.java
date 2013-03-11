package com.example.stocks.core;

public enum ExampleStocks implements Symbol {

    Apple("AAPL"),
    Google("GOOG"),
    Amazon("AMZN");

    private final String symbol;

    ExampleStocks(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toSymbol() {
        return symbol;
    }
}
