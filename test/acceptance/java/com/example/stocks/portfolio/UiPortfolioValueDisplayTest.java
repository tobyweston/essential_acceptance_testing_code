package com.example.stocks.portfolio;

import com.example.stocks.CopyResourcesToOutputFolder;
import com.example.stocks.driver.pages.Browser;
import com.example.stocks.infrastructure.HttpServer;
import com.example.stocks.infrastructure.client.UiServer;
import org.concordion.api.ExpectedToPass;
import org.concordion.api.extension.ConcordionExtension;
import org.concordion.api.extension.Extension;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.net.MalformedURLException;

import static com.example.stocks.infrastructure.UrlMatchingStrategies.urlEndingWith;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

@RunWith(ConcordionRunner.class)
@ExpectedToPass
public class UiPortfolioValueDisplayTest {

    @Extension public ConcordionExtension extension = new CopyResourcesToOutputFolder(this.getClass());

    private final HttpServer ui = new UiServer();
    private final FakeHttpServer application = new FakeHttpServer(8000);
    private final Browser browser = new Browser();

    @Before
    public void start() {
        ui.start();
        application.start();
    }

    public void requestPortfolioValue(String headerName, String headerValue, String body) throws MalformedURLException {
        application.stub(urlEndingWith("/portfolio/0001"), aResponse().withHeader(headerName, headerValue).withBody(body));
        browser.valuationPage().requestValuationForShares(100);
    }

    public String getPortfolioValue() throws InterruptedException {
        return browser.valuationPage().getPortfolioValue();
    }

    @After
    public void stop() {
        ui.stop();
        application.stop();
        browser.quit();
    }

}
