package com.example.stocks.portfolio;

import com.example.stocks.CopyResourcesToOutputFolder;
import com.example.stocks.core.*;
import org.concordion.api.ExpectedToPass;
import org.concordion.api.extension.ConcordionExtension;
import org.concordion.api.extension.Extension;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import static com.example.stocks.core.ExampleStocks.fromSymbol;

@RunWith(ConcordionRunner.class)
@ExpectedToPass
public class PortfolioValuationCalculationTest {

    @Extension public ConcordionExtension extension = new CopyResourcesToOutputFolder(this.getClass());

    private final StubMarketData marketData = new StubMarketData();
    private final StubBook book = new StubBook();
    private final Valuation valuation = new Portfolio(book, marketData);

    public void setBook(String symbol, Integer quantity) {
        book.add(new NumberOfStocks(fromSymbol(symbol), quantity));
    }

    public void setMarketDataPrice(String symbol, String price) {
        marketData.add(fromSymbol(symbol), new Money(price));
    }

    public String getTotalPortfolioValue() {
        return valuation.value().toString();
    }

}
