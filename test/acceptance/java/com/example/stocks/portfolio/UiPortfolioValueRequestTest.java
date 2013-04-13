package com.example.stocks.portfolio;

import com.example.stocks.CopyResourcesToOutputFolder;
import com.example.stocks.driver.pages.LandingPage;
import com.example.stocks.infrastructure.HttpServer;
import com.example.stocks.infrastructure.client.WebUi;
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
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

@RunWith(ConcordionRunner.class)
@ExpectedToPass
public class UiPortfolioValueRequestTest {

    @Extension public ConcordionExtension extension = new CopyResourcesToOutputFolder(this.getClass());

    private final HttpServer client = new WebUi();
    private final FakeHttpServer application = new FakeHttpServer(8000);
    private final LandingPage ui = new LandingPage();

    private final static String expectedUrl = "/portfolio/0001";

    @Before
    public void start() {
        client.start();
        application.start();
    }

    public String requestPortfolioValue() throws MalformedURLException {
        application.stub(urlEndingWith("/portfolio/0001"), aResponse().withHeader("Access-Control-Allow-Origin", "*").withBody("1000"));
        ui.navigateToLandingPage().requestValuationForShares(100);
        return expectedUrl;
    }

    public String verifyHttpGet() throws InterruptedException {
        try {
            application.verify(urlEndingWith(expectedUrl));
            return "request for the portfolio value is made";
        } catch (AssertionError e) {
            return "request for the portfolio value was not made";
        }
    }

    public boolean verifyResponseReturned() throws InterruptedException {
        ui.assertThatPortfolioValue(not(isEmptyOrNullString()));
        return true;
    }

    @After
    public void stop() {
        client.stop();
        application.stop();
        ui.quit();
    }

}
