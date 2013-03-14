package com.example.stocks.core;

import com.example.stocks.infrastructure.server.Server;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.UrlMatchingStrategy;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class FakeYahoo implements Server {

    public static final Integer port = 8001;

    private final WireMockServer server = new WireMockServer(wireMockConfig().port(port));

    @Override
    public void start() {
        configureFor("localhost", port);
        server.start();
    }

    public void stub(UrlMatchingStrategy urlMatchingStrategy, ResponseDefinitionBuilder response) {
        stubFor(get(urlMatchingStrategy).willReturn(response));
    }

    @Override
    public void stop() {
        server.stop();
    }

}
