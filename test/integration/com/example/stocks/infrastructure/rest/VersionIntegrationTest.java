package com.example.stocks.infrastructure.rest;

import com.example.stocks.infrastructure.server.PortfolioBuilder;
import com.example.stocks.infrastructure.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static bad.robot.http.matchers.Matchers.content;
import static com.example.stocks.infrastructure.http.HttpClientFactory.defaultHttpClient;
import static com.googlecode.totallylazy.matchers.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class VersionIntegrationTest {

    private final Server server = new RestfulApplicationServer(PortfolioBuilder.defaultPortfolio());

    @Before
    public void startServer() {
        server.start();
    }

    @Test
    public void version() throws MalformedURLException {
        assertThat(defaultHttpClient().get(new URL("http://localhost:8000/version")), content(is("1.0")));
    }

    @After
    public void stopServer() {
        server.stop();
    }
}
