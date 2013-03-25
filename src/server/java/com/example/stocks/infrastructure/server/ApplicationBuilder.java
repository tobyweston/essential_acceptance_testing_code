package com.example.stocks.infrastructure.server;

import com.example.stocks.infrastructure.Builder;
import com.example.stocks.infrastructure.HttpServer;

public class ApplicationBuilder {

    private Builder<HttpServer> server = HttpApplicationServerBuilder.defaultHttpServer();

    private ApplicationBuilder() {
    }

    public static ApplicationBuilder defaultApplication() {
        return new ApplicationBuilder();
    }

    public ApplicationBuilder with(Builder<HttpServer> server) {
        this.server = server;
        return this;
    }

    public Application build() {
        return new Application(server.build());
    }
}
