package com.example.stocks.core;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static com.example.stocks.core.ExampleStocks.Apple;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NumberOfStocksTest {

    @Rule public final JUnitRuleMockery context = new JUnitRuleMockery();

    @Mock MarketData marketData;

    @Test
    public void valuesPositionWithMarketData() throws Exception {
        context.checking(new Expectations() {{
            oneOf(marketData).getPrice(Apple); will(returnValue(new Money(7)));
        }});

        assertThat(new NumberOfStocks(Apple, 9).value(marketData), is(new Money(63)));
    }

}
