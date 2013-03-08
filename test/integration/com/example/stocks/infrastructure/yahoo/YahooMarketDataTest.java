package com.example.stocks.infrastructure.yahoo;

import com.example.stocks.core.Price;
import com.example.stocks.core.Stock;
import com.example.stocks.util.Date;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class YahooMarketDataTest {

    @Test
    public void retrievesStockPriceFromYahoo() throws Exception {
        Stock stock = new Stock("AAPL");
        Price expectedPrice = new Price("430.47");
        YahooMarketData marketDate = new YahooMarketData();
        assertThat(marketDate.priceFor(stock, new Date(2013, 3, 1)), is(expectedPrice));
    }

}
