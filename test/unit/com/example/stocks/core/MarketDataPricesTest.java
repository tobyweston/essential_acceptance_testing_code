package com.example.stocks.core;

import com.example.stocks.infrastructure.Clock;
import com.example.stocks.infrastructure.MarketData;
import com.example.stocks.util.Date;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MarketDataPricesTest {

    @Rule public final JUnitRuleMockery context = new JUnitRuleMockery();

    @Mock private Clock clock;
    @Mock private MarketData marketData;

    @Test
    public void retrievesPriceFromMarketDataSource() throws Exception {
        final Date now = new Date(2013, 3, 1);
        final Symbol symbol = new Symbol("AAPL");
        final Stock stock = new Stock(symbol);
        final StockQuote quote = new StubStockQuote();


        MarketDataPrices prices = new MarketDataPrices(clock, marketData);

        context.checking(new Expectations() {{
            oneOf(clock).now(); will(returnValue(now));
            oneOf(marketData).getQuote(symbol, now); will(returnValue(quote));
        }});

        assertThat(prices.getLatest(stock), is(quote.getClosingPrice()));
    }

    private static class StubStockQuote implements StockQuote {
        @Override
        public Price getClosingPrice() {
            return new Price(0);
        }
    }
}
