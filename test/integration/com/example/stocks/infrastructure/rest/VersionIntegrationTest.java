package com.example.stocks.infrastructure.rest;

import com.example.stocks.infrastructure.SystemConfiguration;
import com.example.stocks.infrastructure.http.HttpClient;
import com.example.stocks.infrastructure.http.HttpClientFactory;
import com.example.stocks.infrastructure.http.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static com.googlecode.totallylazy.matchers.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class VersionIntegrationTest {

    private final HttpServer server = new HttpApplicationServer();

    @Before
    public void startServer() {
        server.start();
    }

    @Test
    public void version() throws MalformedURLException {
        HttpClient http = new HttpClientFactory(new SystemConfiguration()).createClient();
        assertThat(http.get(new URL("http://localhost:8000/version")), is("1.0"));
    }

    @After
    public void stopServer() {
        server.stop();
    }
}
