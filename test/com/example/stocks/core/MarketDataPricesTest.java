package com.example.stocks.core;

import com.example.stocks.infrastructure.Clock;
import com.example.stocks.infrastructure.MarketData;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MarketDataPricesTest {

    @Rule public final JUnitRuleMockery context = new JUnitRuleMockery();

    @Mock private Clock clock;
    @Mock private MarketData marketData;

    @Test
    public void retrievesPriceFromMarketDataSource() throws Exception {
        final Date now = new Date();
        final Stock stock = new Stock("AAPL");
        final Price price = new Price(0);

        MarketDataPrices prices = new MarketDataPrices(clock, marketData);

        context.checking(new Expectations() {{
            oneOf(clock).now(); will(returnValue(now));
            oneOf(marketData).priceFor(stock, now); will(returnValue(price));
        }});

        assertThat(prices.getLatest(stock), is(price));
    }
}
