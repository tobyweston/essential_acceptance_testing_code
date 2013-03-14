package com.example.stocks.core;

import com.example.stocks.infrastructure.SystemConfiguration;
import com.example.stocks.infrastructure.http.HttpClient;
import com.example.stocks.infrastructure.http.HttpClientFactory;
import com.example.stocks.infrastructure.rest.HttpApplicationServer;
import com.example.stocks.infrastructure.server.HttpApplicationServerBuilder;
import com.example.stocks.infrastructure.server.PortfolioBuilder;
import com.example.stocks.infrastructure.server.Server;
import com.example.stocks.infrastructure.yahoo.Yahoo;
import com.example.stocks.infrastructure.yahoo.YahooMarketData;
import com.example.stocks.infrastructure.yahoo.YqlWebService;
import com.example.stocks.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static com.example.stocks.core.ExampleStocks.Amazon;
import static com.example.stocks.core.ExampleStocks.Apple;
import static com.example.stocks.infrastructure.rest.HttpApplicationServer.port;
import static com.example.stocks.infrastructure.server.ApplicationBuilder.defaultApplication;
import static com.example.stocks.infrastructure.server.HttpApplicationServerBuilder.defaultHttpServer;
import static com.example.stocks.infrastructure.server.PortfolioBuilder.defaultPortfolio;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PortfolioSystemTest {

    private final Book book = new StubBook().add(Amazon).add(Apple);
    private final Yahoo yahoo = new YqlWebService(new HttpClientFactory(new SystemConfiguration()).createClient(), "http://localhost:" + FakeYahoo.port);
    private final MarketData marketData = new YahooMarketData(yahoo, new FrozenClock(new Date(2013, 8, 22)));
    private final PortfolioBuilder portfolio = defaultPortfolio().with(book).with(marketData);
    private final HttpApplicationServerBuilder httpServer = defaultHttpServer().with(portfolio);
    private final Server application = defaultApplication().with(httpServer).build();
    private final Server fakeYahoo = new FakeYahoo();

    @Before
    public void startServers() {
        application.start();
        fakeYahoo.start();
    }

    @Test
    public void valuation() throws MalformedURLException {
        HttpClient http = new HttpClientFactory(new SystemConfiguration()).createClient();
        String response = http.get(new URL("http://localhost:" + HttpApplicationServer.port + "/portfolio/0001"));
        assertThat(response, is("720"));
    }

    @After
    public void stopServer() {
        application.stop();
        fakeYahoo.stop();
    }

}
