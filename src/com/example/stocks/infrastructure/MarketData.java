package com.example.stocks.infrastructure;

import com.example.stocks.core.StockQuote;
import com.example.stocks.core.Symbol;
import com.example.stocks.util.Date;

public interface MarketData {
    public StockQuote getQuote(Symbol symbol, Date date);
}
