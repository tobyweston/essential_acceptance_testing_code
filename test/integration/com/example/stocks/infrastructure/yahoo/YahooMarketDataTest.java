package com.example.stocks.infrastructure.yahoo;

import com.example.stocks.core.Price;
import com.example.stocks.core.Stock;
import com.example.stocks.infrastructure.http.HttpClient;
import com.example.stocks.infrastructure.http.HttpClientFactory;
import com.example.stocks.util.Date;
import com.example.stocks.infrastructure.SystemConfiguration;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class YahooMarketDataTest {

    @Test
    public void retrievesStockPriceFromYahoo() throws Exception {
        Stock stock = new Stock("AAPL");
        Date date = new Date(2013, 3, 1);
        Price expectedPrice = new Price("430.47");

        HttpClient client = new HttpClientFactory(new SystemConfiguration()).createClient();
        Yahoo yahoo = new YqlWebService(client);
        YahooMarketData marketDate = new YahooMarketData(yahoo);
        assertThat(marketDate.priceFor(stock, date), is(expectedPrice));
    }

}
