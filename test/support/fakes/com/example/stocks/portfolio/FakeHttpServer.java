package com.example.stocks.portfolio;

import com.example.stocks.infrastructure.HttpServer;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.UrlMatchingStrategy;
import com.github.tomakehurst.wiremock.client.VerificationException;
import com.github.tomakehurst.wiremock.client.WireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class FakeHttpServer implements HttpServer {

    private final Integer port;
    private final WireMockServer server;

    public FakeHttpServer(Integer port) {
        this.port = port;
        this.server = new WireMockServer(wireMockConfig().port(this.port));
    }

    @Override
    public void start() {
        configureFor("localhost", port);
        server.start();
    }

    public void stub(UrlMatchingStrategy urlMatchingStrategy, ResponseDefinitionBuilder response) {
        stubFor(get(urlMatchingStrategy).willReturn(response));
    }

    public void verify(UrlMatchingStrategy urlMatchingStrategy) throws VerificationException {
        WireMock.verify(getRequestedFor(urlMatchingStrategy));
    }

    @Override
    public void stop() {
        server.stop();
    }
}
