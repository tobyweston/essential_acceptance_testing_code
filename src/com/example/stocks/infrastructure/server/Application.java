package com.example.stocks.infrastructure.server;

import com.example.stocks.infrastructure.rest.HttpApplicationServer;

public class Application implements Server {

    private final HttpApplicationServer server;

    public static Application productionConfiguration() {
        return ApplicationBuilder.defaultApplication().build();
    }

    Application(HttpApplicationServer server) {
        this.server = server;
    }

    @Override
    public void start() {
        server.start();
    }

    @Override
    public void stop() {
        server.stop();
    }

    public static void main(String[] args) {
        productionConfiguration().start();
    }

}
