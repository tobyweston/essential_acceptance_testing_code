package com.example.stocks.core;

import org.junit.Before;
import org.junit.Test;

import static com.example.stocks.core.ExampleStocks.Amazon;
import static com.example.stocks.core.ExampleStocks.Apple;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class VersionTest {

    private StubBook book = new StubBook();
    private StubMarketData marketData = new StubMarketData();
    private Portfolio portfolio = new Portfolio(book, marketData);

    @Before
    public void initialiseStocks() {
        add(Apple,"427.06");
        add(Amazon, "272.38");
    }

    @Test
    public void calculatesSumOfPositionsRecordedInBook() throws Exception {
        assertThat(portfolio.value(), is(new Money("699.44")));
    }

    private void add(Symbol symbol, String price) {
        book.add(symbol);
        marketData.add(symbol, new Money(price));
    }
}
