package com.example.stocks.portfolio;

import com.example.stocks.driver.pages.LandingPage;
import com.example.stocks.infrastructure.HttpServer;
import com.example.stocks.infrastructure.client.WebUi;
import com.github.tomakehurst.wiremock.client.VerificationException;
import org.concordion.api.ExpectedToPass;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.net.MalformedURLException;

import static com.example.stocks.infrastructure.UrlMatchingStrategies.urlEndingWith;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

@RunWith(ConcordionRunner.class)
@ExpectedToPass
public class PortfolioUiTransportTest {

    private final HttpServer client = new WebUi();
    private final FakeHttpServer application = new FakeHttpServer(8000);
    private final LandingPage ui = new LandingPage();
    private final String expectedUrl = "/portfolio/0001";

    @Before
    public void start() {
        client.start();
        application.start();
    }

    public String requestPortfolioValue() throws MalformedURLException {
        application.stub(urlEndingWith(expectedUrl), aResponse().withBody("1000"));
        ui.navigateToLandingPage().requestValuationForShares(100);
        return expectedUrl;
    }

    public String verifyHttpGet() {
        try {
            application.verify(urlEndingWith(expectedUrl));
            return "request for the portfolio value is made";
        } catch (VerificationException e) {
            System.err.println(e.getMessage());
            return "request for the portfolio value was not made";
        }
    }

    @After
    public void stop() {
        client.stop();
        application.stop();
        ui.quit();
    }

}
