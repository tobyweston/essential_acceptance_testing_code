package com.example.stocks.infrastructure.server;

import com.example.stocks.infrastructure.rest.HttpApplicationServer;

public class HttpApplicationServerBuilder {

    private PortfolioBuilder portfolio = PortfolioBuilder.defaultPortfolio();

    private HttpApplicationServerBuilder() {
    }

    public static HttpApplicationServerBuilder defaultHttpServer() {
        return new HttpApplicationServerBuilder();
    }

    public HttpApplicationServerBuilder with(PortfolioBuilder portfolio) {
        this.portfolio = portfolio;
        return this;
    }

    public HttpApplicationServer build() {
        return new HttpApplicationServer(portfolio);
    }
}
