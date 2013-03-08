package com.example.stocks.infrastructure;

import com.example.stocks.core.StockQuote;
import com.example.stocks.core.Symbol;
import com.example.stocks.infrastructure.yahoo.Yahoo;
import com.example.stocks.infrastructure.yahoo.YahooStockRequest;
import com.example.stocks.util.Date;

public class YahooMarketData implements MarketData {

    private final Yahoo yahoo;

    public YahooMarketData(Yahoo yahoo) {
        this.yahoo = yahoo;
    }

    @Override
    public StockQuote getQuote(Symbol symbol, Date date) {
        return new YahooStockRequest(symbol, date).sendTo(yahoo);
    }
}
