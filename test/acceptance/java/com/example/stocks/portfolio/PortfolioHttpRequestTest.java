package com.example.stocks.portfolio;

import com.example.stocks.CopyResourcesToOutputFolder;
import com.example.stocks.core.Money;
import com.example.stocks.core.Valuation;
import com.example.stocks.infrastructure.rest.PortfolioResource;
import com.googlecode.utterlyidle.Response;
import org.concordion.api.ExpectedToPass;
import org.concordion.api.extension.ConcordionExtension;
import org.concordion.api.extension.Extension;
import org.concordion.integration.junit4.ConcordionRunner;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
@ExpectedToPass
public class PortfolioHttpRequestTest {

    @Extension public ConcordionExtension extension = new CopyResourcesToOutputFolder(this.getClass());

    private final Mockery context = new JUnit4Mockery();

    private final Valuation valuation = context.mock(Valuation.class);
    private final PortfolioResource portfolio = new PortfolioResource(valuation);

    public String getTotalPortfolioValue() {
        context.checking(new Expectations() {{
            oneOf(valuation).value(); will(returnValue(new Money("26185.00")));
        }});

        Response response = portfolio.value(null);
        return response.entity().toString();
    }
}
