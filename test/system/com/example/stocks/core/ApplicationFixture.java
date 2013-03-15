package com.example.stocks.core;

import com.example.stocks.infrastructure.server.Application;
import com.example.stocks.infrastructure.server.HttpApplicationServerBuilder;
import com.example.stocks.infrastructure.server.PortfolioBuilder;
import com.example.stocks.infrastructure.yahoo.Yahoo;
import com.example.stocks.infrastructure.yahoo.YahooMarketData;
import com.example.stocks.infrastructure.yahoo.YqlWebService;
import com.example.stocks.util.Date;

import static com.example.stocks.core.ExampleStocks.Amazon;
import static com.example.stocks.core.ExampleStocks.Apple;
import static com.example.stocks.infrastructure.http.HttpClientFactory.defaultHttpClient;
import static com.example.stocks.infrastructure.server.ApplicationBuilder.defaultApplication;
import static com.example.stocks.infrastructure.server.HttpApplicationServerBuilder.defaultHttpServer;
import static com.example.stocks.infrastructure.server.PortfolioBuilder.defaultPortfolio;

public class ApplicationFixture {

    public static Application applicationWithRealYahoo() {
        Book book = new StubBook().add(Amazon).add(Apple);
        Yahoo yahoo = new YqlWebService(defaultHttpClient());
        MarketData marketData = new YahooMarketData(yahoo, new FrozenClock(new Date(2012, 8, 23)));
        PortfolioBuilder portfolio = defaultPortfolio().with(book).with(marketData);
        HttpApplicationServerBuilder httpServer = defaultHttpServer().with(portfolio);
        return defaultApplication().with(httpServer).build();
    }

    public static Application applicationWithFakeYahoo() {
        Book book = new StubBook().add(Amazon).add(Apple);
        Yahoo yahoo = new YqlWebService(defaultHttpClient(), "http://localhost:" + FakeYahoo.port);
        MarketData marketData = new YahooMarketData(yahoo, new FrozenClock(new Date(2012, 8, 22)));
        PortfolioBuilder portfolio = defaultPortfolio().with(book).with(marketData);
        HttpApplicationServerBuilder httpServer = defaultHttpServer().with(portfolio);
        return defaultApplication().with(httpServer).build();
    }

}
