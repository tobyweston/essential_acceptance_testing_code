package com.example.stocks.core;

import com.example.stocks.infrastructure.SystemConfiguration;
import com.example.stocks.infrastructure.http.HttpClient;
import com.example.stocks.infrastructure.http.HttpClientFactory;
import com.example.stocks.infrastructure.http.HttpServer;
import com.example.stocks.infrastructure.rest.HttpApplicationServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PortfolioSystemTest {

    private final HttpServer server = new HttpApplicationServer();

    @Before
    public void startServer() {
        server.start();
    }

    @Test
    public void valuation() throws MalformedURLException {
        HttpClient http = new HttpClientFactory(new SystemConfiguration()).createClient();
        String response = http.get(new URL("http://localhost:8000/portfolio/0001"));
        assertThat(response, is("99.99"));
    }

    @After
    public void stopServer() {
        server.stop();
    }

}
