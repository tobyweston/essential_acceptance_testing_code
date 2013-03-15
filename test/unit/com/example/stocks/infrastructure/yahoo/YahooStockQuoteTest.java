package com.example.stocks.infrastructure.yahoo;

import org.junit.Test;

public class YahooStockQuoteTest {

    private final String noResultsQuery = "{\"query\":{\"results\":null,\"count\":0,\"created\":\"2013-03-15T15:01:28Z\",\"lang\":\"en-US\"}}";

    @Test
    public void canConstructObjectWithNoResults() {
        YahooStockQuote quote = new YahooStockQuote(noResultsQuery);
    }

}
