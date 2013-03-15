package com.example.stocks.infrastructure.yahoo;

import com.example.stocks.core.Money;
import org.junit.Test;

import static com.googlecode.totallylazy.matchers.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class YahooStockResponseJsonTest {

    private final String noResults = "{\"query\":{\"results\":null,\"count\":0,\"created\":\"2013-03-15T15:01:28Z\",\"lang\":\"en-US\"}}";
    private final String validJson = "{\"query\":{\"results\":{\"quote\":{\"Close\":\"200.1\"}}}}";
    private final String errorJson = "{\"error\":{\"lang\":\"en-US\",\"description\":\"this should not be passed down here\"}}";

    @Test
    public void noResultsInResponse() {
        YahooStockResponseJson response = new YahooStockResponseJson(noResults);
        assertThat(response.error(), is(false));
        assertThat(response.hasResults(), is(false));
        assertThat(response.getClosingPrice(), is(new Money(0)));
    }

    @Test
    public void validResponse() {
        YahooStockResponseJson response = new YahooStockResponseJson(validJson);
        assertThat(response.error(), is(false));
        assertThat(response.hasResults(), is(true));
        assertThat(response.getClosingPrice(), is(new Money("200.1")));
    }

    @Test
    public void errorResponse() {
        YahooStockResponseJson response = new YahooStockResponseJson(errorJson);
        assertThat(response.error(), is(true));
        assertThat(response.hasResults(), is(false));
        assertThat(response.getClosingPrice(), is(new Money(0)));
    }

}
