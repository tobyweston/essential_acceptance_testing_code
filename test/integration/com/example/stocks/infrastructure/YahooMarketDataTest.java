package com.example.stocks.infrastructure;

import com.example.stocks.core.Price;
import com.example.stocks.core.Symbol;
import com.example.stocks.infrastructure.http.HttpClient;
import com.example.stocks.infrastructure.http.HttpClientFactory;
import com.example.stocks.infrastructure.yahoo.Yahoo;
import com.example.stocks.infrastructure.yahoo.YqlWebService;
import com.example.stocks.util.Date;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class YahooMarketDataTest {

    /*
     * If this test is failing, first check http://www.datatables.org/healthchecker/?q=yahoo to see if the feed is ok.
     */

    @Test
    public void retrievesStockPriceFromYahoo() throws Exception {
        Symbol symbol = new Symbol("AAPL");
        Date date = new Date(2013, 3, 1);
        Price expectedPrice = new Price("430.47");

        HttpClient client = new HttpClientFactory(new SystemConfiguration()).createClient();
        Yahoo yahoo = new YqlWebService(client);
        YahooMarketData marketDate = new YahooMarketData(yahoo);
        assertThat(marketDate.getQuote(symbol, date).getClosingPrice(), is(expectedPrice));
    }

}
