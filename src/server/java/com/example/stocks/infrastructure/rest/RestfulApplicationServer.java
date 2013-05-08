package com.example.stocks.infrastructure.rest;

import com.example.stocks.core.Valuation;
import com.example.stocks.infrastructure.Defect;
import com.example.stocks.infrastructure.HttpServer;
import com.example.stocks.infrastructure.server.PortfolioBuilder;
import com.googlecode.utterlyidle.Application;
import com.googlecode.utterlyidle.Resources;
import com.googlecode.utterlyidle.ServerConfiguration;
import com.googlecode.utterlyidle.httpserver.RestServer;
import com.googlecode.utterlyidle.modules.ApplicationScopedModule;
import com.googlecode.utterlyidle.modules.ResourcesModule;
import com.googlecode.yadic.Container;

import java.io.IOException;

import static com.googlecode.utterlyidle.ApplicationBuilder.application;
import static com.googlecode.utterlyidle.ServerConfiguration.defaultConfiguration;
import static com.googlecode.utterlyidle.annotations.AnnotatedBindings.annotatedClass;

public class RestfulApplicationServer implements HttpServer {

    public static final Integer port = 8000;

    private RestServer server;
    private PortfolioBuilder portfolio;

    public RestfulApplicationServer(PortfolioBuilder portfolio) {
        this.portfolio = portfolio;
    }

    @Override
    public void start() {
        try {
            Application application = application()
                    .add(new ValuationFeature(portfolio))
                    .add(annotatedClass(Version.class))
                    .build();
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

    private static class ValuationFeature implements ResourcesModule, ApplicationScopedModule {

        private final PortfolioBuilder portfolio;

        private ValuationFeature(PortfolioBuilder portfolio) {
            this.portfolio = portfolio;
        }

        @Override
        public Resources addResources(Resources resources) throws Exception {
            return resources.add(annotatedClass(PortfolioResource.class));
        }

        @Override
        public Container addPerApplicationObjects(Container container) throws Exception {
            return container.addInstance(Valuation.class, portfolio.build());
        }
    }
}
