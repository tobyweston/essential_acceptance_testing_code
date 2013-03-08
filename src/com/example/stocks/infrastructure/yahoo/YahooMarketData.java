package com.example.stocks.infrastructure.yahoo;

import com.example.stocks.core.Price;
import com.example.stocks.core.Stock;
import com.example.stocks.infrastructure.MarketData;
import com.example.stocks.util.Date;

public class YahooMarketData implements MarketData {

    private final Yahoo yahoo;

    public YahooMarketData(Yahoo yahoo) {
        this.yahoo = yahoo;
    }

    @Override
    public Price priceFor(Stock stock, Date date) {
        YahooStockPriceRequest request = new YahooStockPriceRequest(stock.getSymbol(), date);
        return new Price(request.executeWith(yahoo).getClosingPrice());
    }
}
