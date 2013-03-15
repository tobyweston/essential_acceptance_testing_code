package com.example.stocks.infrastructure.server;

public class ApplicationBuilder {

    private HttpApplicationServerBuilder server = HttpApplicationServerBuilder.defaultHttpServer();

    private ApplicationBuilder() {
    }

    public static ApplicationBuilder defaultApplication() {
        return new ApplicationBuilder();
    }

    public ApplicationBuilder with(HttpApplicationServerBuilder server) {
        this.server = server;
        return this;
    }

    public Application build() {
        return new Application(server.build());
    }
}
