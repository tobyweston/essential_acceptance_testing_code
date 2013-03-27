package com.example.stocks.portfolio;

import com.example.stocks.core.MarketData;
import com.example.stocks.core.Money;
import com.example.stocks.core.Portfolio;
import com.example.stocks.core.StubBook;
import org.concordion.api.ExpectedToPass;
import org.concordion.integration.junit4.ConcordionRunner;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.runner.RunWith;

import static com.example.stocks.core.ExampleStocks.Amazon;
import static com.example.stocks.core.ExampleStocks.fromSymbol;

@RunWith(ConcordionRunner.class)
@ExpectedToPass
public class MarketDataTest {

    private final Mockery context = new JUnit4Mockery();

    private final MarketData marketData = context.mock(MarketData.class);
    private final Portfolio portfolio = new Portfolio(new StubBook().add(Amazon), marketData);


    public String verifySymbolCheckedWas(final String symbol) {
        context.checking(new Expectations() {{
            oneOf(marketData).getPrice(fromSymbol(symbol));
            will(returnValue(new Money(100)));
        }});
        portfolio.value();
        return symbol;
    }
}
