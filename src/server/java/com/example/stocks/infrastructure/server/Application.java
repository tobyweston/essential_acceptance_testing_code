package com.example.stocks.infrastructure.server;

import bad.robot.http.HttpClient;
import com.example.stocks.core.Book;
import com.example.stocks.core.NumberOfStocks;
import com.example.stocks.core.Position;
import com.example.stocks.infrastructure.HttpServer;
import com.example.stocks.infrastructure.yahoo.YahooMarketData;
import com.example.stocks.infrastructure.yahoo.YqlWebService;
import com.example.stocks.util.RealClock;

import java.util.ArrayList;
import java.util.Iterator;

import static com.example.stocks.infrastructure.http.HttpClientFactory.defaultHttpClient;
import static com.example.stocks.infrastructure.server.ApplicationBuilder.defaultApplication;
import static com.example.stocks.infrastructure.server.HttpApplicationServerBuilder.defaultHttpServer;
import static com.example.stocks.infrastructure.server.PortfolioBuilder.defaultPortfolio;

public class Application implements HttpServer {

    private final HttpServer server;

    public static Application productionConfiguration() {
        HttpClient http = defaultHttpClient();
        PortfolioBuilder portfolio = defaultPortfolio().with(new BookOfOneAmazonShare()).with(new YahooMarketData(new YqlWebService(http), new RealClock()));
        HttpApplicationServerBuilder httpServer = defaultHttpServer().with(portfolio);
        return defaultApplication().with(httpServer).build();
    }

    Application(HttpServer server) {
        this.server = server;
    }

    @Override
    public void start() {
        server.start();
    }

    public void stop() {
        server.stop();
    }

    public static void main(String... args) {
        productionConfiguration().start();
    }

    private static class Symbol implements com.example.stocks.core.Symbol {
        private final String value;

        private Symbol(String value) {
            this.value = value;
        }

        @Override
        public String toSymbol() {
            return value;
        }
    }

    private static class BookOfOneAmazonShare implements Book {
        @Override
        public Iterator<Position> iterator() {
            return new ArrayList<Position>() {{
                add(new NumberOfStocks(new Symbol("AMZN"), 1));
            }}.iterator();
        }
    }
}
