package com.example.stocks.portfolio;

import bad.robot.http.HttpClient;
import bad.robot.http.StringHttpResponse;
import com.example.stocks.core.StubBook;
import com.example.stocks.driver.pages.LandingPage;
import com.example.stocks.infrastructure.server.Application;
import com.example.stocks.infrastructure.server.HttpApplicationServerBuilder;
import com.example.stocks.infrastructure.server.PortfolioBuilder;
import com.example.stocks.infrastructure.yahoo.YahooMarketData;
import com.example.stocks.infrastructure.yahoo.YqlWebService;
import com.example.stocks.util.RealClock;
import org.concordion.api.ExpectedToPass;
import org.concordion.api.extension.ConcordionExtension;
import org.concordion.api.extension.Extension;
import org.concordion.integration.junit4.ConcordionRunner;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.net.MalformedURLException;
import java.net.URL;

import static bad.robot.http.EmptyHeaders.emptyHeaders;
import static com.example.stocks.core.ExampleStocks.Amazon;
import static com.example.stocks.infrastructure.server.ApplicationBuilder.defaultApplication;
import static com.example.stocks.infrastructure.server.HttpApplicationServerBuilder.defaultHttpServer;
import static com.example.stocks.infrastructure.server.PortfolioBuilder.defaultPortfolio;

@RunWith(ConcordionRunner.class)
@ExpectedToPass
public class PortfolioUiTransportTest {

    @Extension public ConcordionExtension extension = new CopyResourcesToOutputFolder(this.getClass());

    public static final String EXPECTED_ENDPOINT = "http://query.yahooapis.com/v1/public/yql?" +
                                                        "q=select+*+from+yahoo.finance.historicaldata+where+symbol+%3D+%22AMZN%22+and+startDate+%3D+%222013-03-22%22+and+endDate+%3D+%222013-03-22%22" +
                                                        "&format=json" +
                                                        "&env=store://datatables.org/alltableswithkeys";
    private final Mockery context = new JUnit4Mockery() {{
        setThreadingPolicy(new Synchroniser());
    }};

    private final HttpClient httpClient = context.mock(HttpClient.class, "Http Client");
    private final YahooMarketData marketData = new YahooMarketData(new YqlWebService(httpClient), new RealClock());
    private final StubBook book = new StubBook().add(Amazon);
    private final PortfolioBuilder portfolio = defaultPortfolio().with(book).with(marketData);
    private final HttpApplicationServerBuilder httpServer = defaultHttpServer().with(portfolio);
    private final Application application = defaultApplication().with(httpServer).build();
    private final LandingPage ui = new LandingPage();


    @Before
    public void startApplication() {
        application.start();
    }

    public String requestPortfolioValue() throws MalformedURLException {
        context.checking(new org.jmock.Expectations() {{
            oneOf(httpClient).get(new URL(EXPECTED_ENDPOINT)); will(returnValue(new StringHttpResponse(200, "OK", "", emptyHeaders())));
        }});
        ui.navigateToLandingPage().requestValuationForShares(100);
        return EXPECTED_ENDPOINT;
    }

    public String verifyHttpGet() {
        try {
            context.assertIsSatisfied();
        } catch (AssertionError e) {
            System.err.println(e.getMessage());
            return "request for the portfolio value was not made";
        }
        return "request for the portfolio value is made";
    }

    @After
    public void stopApplication() {
        application.stop();
    }

}
