package com.example.stocks.infrastructure.rest;

import com.example.stocks.infrastructure.http.HttpServer;
import com.googlecode.utterlyidle.Application;
import com.googlecode.utterlyidle.ApplicationBuilder;
import com.googlecode.utterlyidle.ServerConfiguration;
import com.googlecode.utterlyidle.httpserver.RestServer;

import java.io.IOException;

public class HttpApplicationServer implements HttpServer {

    private RestServer server;

    @Override
    public void start() {
        Application application = ApplicationBuilder.application().addAnnotated(Version.class).build();
        ServerConfiguration configuration = ServerConfiguration.defaultConfiguration().port(8000);
        try {
            server = new RestServer(application, configuration);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stop() {
        try {
            server.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new HttpApplicationServer().start();
    }
}
