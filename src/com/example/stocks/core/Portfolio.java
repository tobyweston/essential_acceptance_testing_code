package com.example.stocks.core;

public class Portfolio {

    private final Book book;
    private final MarketData marketData;

    public Portfolio(Book book, MarketData marketData) {
        this.book = book;
        this.marketData = marketData;
    }

    public Money value() {
        Money value = new Money(0);
        for (Position position : book) {
            value = value.add(position.value(marketData));
        }
        return value;
    }
}
