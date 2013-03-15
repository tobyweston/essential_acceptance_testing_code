package com.example.stocks.core;

import com.example.stocks.driver.pages.LandingPage;
import com.example.stocks.infrastructure.UterllyidleExceptionRule;
import com.example.stocks.infrastructure.rest.HttpApplicationServer;
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
import java.net.URL;

import static bad.robot.http.matchers.Matchers.content;
import static com.example.stocks.infrastructure.UrlMatchingStrategies.urlStartingWith;
import static com.example.stocks.infrastructure.http.HttpClientFactory.defaultHttpClient;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.hamcrest.MatcherAssert.assertThat;
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
        public void shouldRetrieveValuation() {
//            ui.navigateToLandingPage().setNumberOfShares(100).requestValuation(); <-- not doing this as it couples the tests
            String response = "{\"query\":{\"results\":{\"quote\":{\"Close\":\"200.10\"}}}}";
            fakeYahoo.stub(urlStartingWith("/v1/public/yql"), aResponse().withBody(response));
            ui.navigateToLandingPage().requestValuationForShares(100);
            assertThat(ui.getPortfolioValue(), is("400.20"));
        }

        @Test
        public void valuation() throws MalformedURLException {
            String response = "{\"query\":{\"results\":{\"quote\":{\"Close\":\"200.10\"}}}}";
            fakeYahoo.stub(urlStartingWith("/v1/public/yql"), aResponse().withBody(response));
            assertThat(defaultHttpClient().get(new URL("http://localhost:" + HttpApplicationServer.port + "/portfolio/0001")), content(is("400.20")));
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

    /** there's some sort of request limit on real Yahoo, so this may fail if you run it a lot */
    public static class PortfolioSystemTestWithRealYahoo {

        private final Server application = ApplicationFixture.applicationWithRealYahoo();

        @Before
        public void startServers() {
            application.start();
        }

        @Test
        public void valuation() throws MalformedURLException {
            assertThat(defaultHttpClient().get(new URL("http://localhost:" + HttpApplicationServer.port + "/portfolio/0001")), content(is("903.83")));
        }

        @After
        public void stopServer() {
            application.stop();
        }

    }

}
