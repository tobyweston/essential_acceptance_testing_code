package com.example.stocks.core;

import static java.lang.String.format;

public enum ExampleStocks implements Symbol {

    Apple("AAPL"),
    Google("GOOG"),
    Amazon("AMZN");

    private final String symbol;

    ExampleStocks(String symbol) {
        this.symbol = symbol;
    }

    public static Symbol fromSymbol(String symbol) {
        for (ExampleStocks stock : values())
            if (stock.toSymbol().equals(symbol))
                return stock;
        throw new RuntimeException(format("no example stock with symbol %s found", symbol));
    }

    @Override
    public String toSymbol() {
        return symbol;
    }
}
