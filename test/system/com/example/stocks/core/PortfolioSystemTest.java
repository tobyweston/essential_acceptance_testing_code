package com.example.stocks.core;

import com.example.stocks.infrastructure.SystemConfiguration;
import com.example.stocks.infrastructure.http.HttpClient;
import com.example.stocks.infrastructure.http.HttpClientFactory;
import com.example.stocks.infrastructure.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static com.example.stocks.infrastructure.server.Application.ApplicationBuilder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PortfolioSystemTest {

    private final Server application = new ApplicationBuilder().build();

    @Before
    public void startServer() {
        application.start();
    }

    @Test
    public void valuation() throws MalformedURLException {
        HttpClient http = new HttpClientFactory(new SystemConfiguration()).createClient();
        String response = http.get(new URL("http://localhost:8000/portfolio/0001"));
        assertThat(response, is("99.99"));
    }

    @After
    public void stopServer() {
        application.stop();
    }

}
