package com.example.stocks.infrastructure.yahoo;

import com.example.stocks.core.Money;
import org.junit.Test;

import static com.googlecode.totallylazy.matchers.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class YahooStockQuoteTest {

    private final String noResults = "{\"query\":{\"results\":null,\"count\":0,\"created\":\"2013-03-15T15:01:28Z\",\"lang\":\"en-US\"}}";
    private final String validJson = "{\"query\":{\"results\":{\"quote\":{\"Close\":\"200.1\"}}}}";

    @Test
    public void getPriceFromValidJson() {
        assertThat(new YahooStockQuote(validJson).getClosingPrice(), is(new Money("200.10")));
    }

    @Test
    public void getPriceFromJsonWithNoResults() {
        assertThat(new YahooStockQuote(noResults).getClosingPrice(), is(new Money(0)));
    }


}
