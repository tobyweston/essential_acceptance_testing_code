package com.example.stocks.core;

import com.example.stocks.infrastructure.server.Server;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.UrlMatchingStrategy;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class FakeYahoo implements Server {

    public static final Integer port = 8001;

    private final WireMockServer server = new WireMockServer(new WireMockConfiguration().port(port));

    @Override
    public void start() {
        server.start();
    }

    public void stub(String url, String response) {
        stubFor(get(urlStartsWith("/v1/public/yql")).willReturn(aResponse().withBody("370")));
    }

    @Override
    public void stop() {
        server.stop();
    }

    public static UrlMatchingStrategy urlStartsWith(String url) {
        UrlMatchingStrategy urlStrategy = new UrlMatchingStrategy();
        urlStrategy.setUrlPattern(url + ".*");
        return urlStrategy;
    }

}
