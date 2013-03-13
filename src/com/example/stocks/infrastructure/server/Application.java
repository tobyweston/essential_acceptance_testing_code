package com.example.stocks.infrastructure.server;

import com.example.stocks.infrastructure.rest.HttpApplicationServer;

public class Application implements Server {

    private final HttpApplicationServer server;

    public static Application productionConfiguration() {
        return new ApplicationBuilder().build();
    }

    private Application(HttpApplicationServer server) {
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

    public static class ApplicationBuilder {

        private HttpApplicationServer server = new HttpApplicationServer();

        public Application build() {
            return new Application(server);
        }
    }
}
