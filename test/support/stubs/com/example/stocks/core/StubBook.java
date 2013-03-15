package com.example.stocks.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StubBook implements Book {

    private final List<Position> positions;

    public StubBook() {
        this.positions = new ArrayList<Position>();
    }

    public StubBook add(Symbol symbol) {
        positions.add(new SingleStockPosition(symbol));
        return this;
    }

    @Override
    public Iterator<Position> iterator() {
        return positions.iterator();
    }
}
