package com.example.stocks.core;

import com.example.stocks.driver.pages.LandingPage;
import com.example.stocks.infrastructure.UterllyidleExceptionRule;
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

        private final Server application = ApplicationFixture.applicationWithFakeYahoo();
        private final FakeYahoo fakeYahoo = new FakeYahoo();
        private final LandingPage ui = new LandingPage();

        @Before
        public void startServers() {
            application.start();
            fakeYahoo.start();
        }

        @Test
        public void shouldRetrieveValuation() throws TimeoutException, InterruptedException {
            String response = "{\"query\":{\"results\":{\"quote\":{\"Close\":\"200.10\"}}}}";
            fakeYahoo.stub(urlStartingWith("/v1/public/yql"), aResponse().withBody(response));
            ui.navigateToLandingPage().requestValuationForShares(100).assertThatPortfolioValue(is("400.20"));
        }

        private void stopServers() {
            application.stop();
            fakeYahoo.stop();
        }

        @After
        public void quitTheBrowser() {
            ui.quit();
        }

    }

    public static class PortfolioSystemTestWithRealYahoo {

        private final Server application = ApplicationFixture.applicationWithRealYahoo();
        private final LandingPage ui = new LandingPage();

        @Before
        public void startServers() {
            application.start();
        }

        @Test
        public void shouldRetrieveValuation() throws MalformedURLException, InterruptedException {
            ui.navigateToLandingPage().requestValuationForShares(100).assertThatPortfolioValue(is("903.83"));
        }

        @After
        public void stopServer() {
            application.stop();
            ui.quit();
        }

    }

}
