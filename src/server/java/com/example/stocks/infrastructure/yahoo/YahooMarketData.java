package com.example.stocks.infrastructure.yahoo;

import com.example.stocks.core.MarketData;
import com.example.stocks.core.Money;
import com.example.stocks.core.Symbol;
import com.example.stocks.infrastructure.Clock;

public class YahooMarketData implements MarketData {

    private final Yahoo yahoo;
    private final Clock clock;

    public YahooMarketData(Yahoo yahoo, Clock clock) {
        this.yahoo = yahoo;
        this.clock = clock;
    }

    @Override
    public Money getPrice(Symbol symbol) {
        return new YahooStockRequest(symbol, clock.now()).sendTo(yahoo).getClosingPrice();
    }
}
