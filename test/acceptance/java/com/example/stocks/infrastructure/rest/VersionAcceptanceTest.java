package com.example.stocks.infrastructure.rest;

import bad.robot.http.HttpClient;
import bad.robot.http.HttpResponse;
import com.example.stocks.infrastructure.server.ApplicationBuilder;
import com.example.stocks.infrastructure.server.Server;
import org.concordion.api.ExpectedToPass;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.net.MalformedURLException;
import java.net.URL;

import static com.example.stocks.infrastructure.http.HttpClientFactory.defaultHttpClient;

@RunWith(ConcordionRunner.class)
@ExpectedToPass
public class VersionAcceptanceTest {

    private final Server server = ApplicationBuilder.defaultApplication().build();

    @Before
    public void startServer() {
        server.start();
    }

    public String getVersion(String url) throws MalformedURLException {
        HttpClient http = defaultHttpClient();
        HttpResponse response = http.get(new URL("http://localhost:8000" + url));
        return response.getContent().asString();
    }

    @After
    public void stopServer() {
        server.stop();
    }

}
