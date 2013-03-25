package com.example.stocks.infrastructure.server;

import com.example.stocks.infrastructure.Builder;
import com.example.stocks.infrastructure.HttpServer;
import com.example.stocks.infrastructure.rest.RestfulApplicationServer;

public class HttpApplicationServerBuilder implements Builder<HttpServer> {

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

    @Override
    public HttpServer build() {
        return new RestfulApplicationServer(portfolio);
    }
}
