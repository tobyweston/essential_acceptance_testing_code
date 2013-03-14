package com.example.stocks.core;

import com.example.stocks.infrastructure.rest.HttpApplicationServer;
import com.example.stocks.infrastructure.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.net.MalformedURLException;
import java.net.URL;

import static com.example.stocks.infrastructure.http.HttpClientFactory.defaultHttpClient;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(Enclosed.class)
public class PortfolioSystemTest {

    public static class PortfolioSystemTestWithFakeYahoo {

        private final Server application = ApplicationFixture.applicationWithFakeYahoo();
//        private final Server fakeYahoo = new FakeYahoo();

        @Before
        public void startServers() {
            application.start();
//            fakeYahoo.start();
        }

        @Test
        public void valuation() throws MalformedURLException {
            assertThat(defaultHttpClient().get(new URL("http://localhost:" + HttpApplicationServer.port + "/portfolio/0001")), is("720"));
        }

        @After
        public void stopServer() {
            application.stop();
//            fakeYahoo.stop();
        }

    }

    public static class PortfolioSystemTestWithRealYahoo {

        private final Server application = ApplicationFixture.applicationWithRealYahoo();

        @Before
        public void startServers() {
            application.start();
        }

        @Test
        public void valuation() throws MalformedURLException {
            assertThat(defaultHttpClient().get(new URL("http://localhost:" + HttpApplicationServer.port + "/portfolio/0001")), is("720"));
        }

        @After
        public void stopServer() {
            application.stop();
        }

    }

}
