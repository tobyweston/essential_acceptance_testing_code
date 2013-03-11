package com.example.stocks.core;

import java.util.HashMap;
import java.util.Map;

public class StubMarketData implements MarketData {
    private Map<Symbol, Money> data;

    public StubMarketData() {
        this.data = new HashMap<Symbol, Money>();
    }

    public void add(Symbol symbol, Money price) {
        this.data.put(symbol, price);
    }

    @Override
    public Money getPrice(Symbol symbol) {
        return data.get(symbol);
    }
}
