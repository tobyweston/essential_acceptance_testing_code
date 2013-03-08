package com.example.stocks.infrastructure;

import com.example.stocks.core.Price;
import com.example.stocks.core.Stock;
import com.example.stocks.util.Date;

public interface MarketData {
    public Price priceFor(Stock stock, Date date);
}
