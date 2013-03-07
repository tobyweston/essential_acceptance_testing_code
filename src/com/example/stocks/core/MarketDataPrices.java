package com.example.stocks.core;

import com.example.stocks.infrastructure.Clock;
import com.example.stocks.infrastructure.MarketData;

public class MarketDataPrices implements Prices {

    private Clock clock;
    private MarketData marketData;

    public MarketDataPrices(Clock clock, MarketData marketData) {
        this.clock = clock;
        this.marketData = marketData;
    }

    @Override
    public Price getLatest(Stock stock) {
        return marketData.priceFor(stock, clock.now());
    }
}
