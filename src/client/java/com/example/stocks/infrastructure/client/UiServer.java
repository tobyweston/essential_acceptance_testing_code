package com.example.stocks.infrastructure.client;

import com.example.stocks.infrastructure.Defect;
import com.example.stocks.infrastructure.HttpServer;
import com.googlecode.utterlyidle.Application;
import com.googlecode.utterlyidle.ServerConfiguration;
import com.googlecode.utterlyidle.httpserver.RestServer;

import java.io.IOException;

import static com.example.stocks.infrastructure.rest.URLs.defaultPackageUrl;
import static com.googlecode.totallylazy.Strings.EMPTY;
import static com.googlecode.utterlyidle.ApplicationBuilder.application;
import static com.googlecode.utterlyidle.ServerConfiguration.defaultConfiguration;

public class UiServer implements HttpServer {

    public static final Integer port = 7000;

    private RestServer server;

    @Override
    public void start() {
        try {
            Application application = application().content(defaultPackageUrl(getClass()), EMPTY).build();
            ServerConfiguration configuration = defaultConfiguration().port(port);
            server = new RestServer(application, configuration);
        } catch (Exception e) {
            throw new Defect(e);
        }
    }

    @Override
    public void stop() {
        try {
            server.close();
        } catch (IOException e) {
            throw new Defect(e);
        }
    }

    public static void main(String... args) {
        new UiServer().start();
    }
}
