package com.example.stocks.infrastructure.server;

import com.example.stocks.core.*;

import java.util.Collections;
import java.util.Iterator;

public class PortfolioBuilder {

    private Book book = new Book() {
        @Override
        public Iterator<Position> iterator() {
            return Collections.<Position>emptyList().iterator();
        }
    };
    private MarketData marketData = new MarketData() {
        @Override
        public Money getPrice(Symbol symbol) {
            return new Money(0);
        }
    };

    PortfolioBuilder() {
    }

    public static PortfolioBuilder defaultPortfolio() {
        return new PortfolioBuilder();
    }

    public PortfolioBuilder with(Book book) {
        this.book = book;
        return this;
    }

    public PortfolioBuilder with(MarketData marketData) {
        this.marketData = marketData;
        return this;
    }

    public Portfolio build() {
        return new Portfolio(book, marketData);
    }
}
