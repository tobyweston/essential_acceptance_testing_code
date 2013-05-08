package com.example.stocks.core;

import com.example.stocks.driver.pages.Browser;
import com.example.stocks.infrastructure.HttpServer;
import com.example.stocks.infrastructure.UterllyidleExceptionRule;
import com.example.stocks.infrastructure.client.UiServer;
import com.example.stocks.infrastructure.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExternalResource;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import java.net.MalformedURLException;
import java.util.concurrent.TimeoutException;

import static com.example.stocks.infrastructure.UrlMatchingStrategies.urlStartingWith;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.hamcrest.Matchers.is;

@RunWith(Enclosed.class)
public class PortfolioSystemTest {

    public static class PortfolioSystemTestWithFakeYahoo {

        @Rule public final TestRule serverException = new UterllyidleExceptionRule();
        @Rule public final TestRule external = new ExternalResource() {
            @Override
            protected void after() {
                stopServers();
            }
        };

        private final HttpServer ui = new UiServer();
        private final Server application = ApplicationFixture.applicationWithFakeYahoo();
        private final FakeYahoo fakeYahoo = new FakeYahoo();
        private final Browser browser = new Browser();

        @Before
        public void startServers() {
            ui.start();
            application.start();
            fakeYahoo.start();
        }

        @Test
        public void shouldRetrieveValuation() throws TimeoutException, InterruptedException {
            String response = "{\"query\":{\"results\":{\"quote\":{\"Close\":\"200.10\"}}}}";
            fakeYahoo.stub(urlStartingWith("/v1/public/yql"), aResponse().withBody(response));
            browser.valuationPage().requestValuationForShares(100).assertThatPortfolioValue(is("400.20"));
        }

        private void stopServers() {
            ui.stop();
            application.stop();
            fakeYahoo.stop();
        }

        @After
        public void quitTheBrowser() {
            browser.quit();
        }

    }

    public static class PortfolioSystemTestWithRealYahoo {

        private final HttpServer ui = new UiServer();
        private final HttpServer application = ApplicationFixture.applicationWithRealYahoo();
        private final Browser browser = new Browser();

        @Before
        public void startServers() {
            ui.start();
            application.start();
        }

        @Test
        public void shouldRetrieveValuation() throws MalformedURLException, InterruptedException {
            browser.valuationPage().requestValuationForShares(100).assertThatPortfolioValue(is("903.83"));
        }

        @After
        public void stopServer() {
            ui.stop();
            application.stop();
            browser.quit();
        }

    }

}
