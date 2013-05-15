package com.example.stocks.portfolio;

import com.example.stocks.CopyResourcesToOutputFolder;
import com.example.stocks.core.FakeYahoo;
import com.example.stocks.core.FrozenClock;
import com.example.stocks.core.MarketData;
import com.example.stocks.infrastructure.Clock;
import com.example.stocks.infrastructure.WiremockLoggedRequest;
import com.example.stocks.infrastructure.yahoo.Yahoo;
import com.example.stocks.infrastructure.yahoo.YahooMarketData;
import com.example.stocks.infrastructure.yahoo.YqlWebService;
import com.example.stocks.util.Date;
import com.github.tomakehurst.wiremock.verification.LoggedRequest;
import org.concordion.api.ExpectedToPass;
import org.concordion.api.extension.ConcordionExtension;
import org.concordion.api.extension.Extension;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.text.ParseException;
import java.util.List;

import static bad.robot.http.HttpClients.anApacheClient;
import static com.example.stocks.core.ExampleStocks.fromSymbol;
import static com.example.stocks.infrastructure.UrlMatchingStrategies.urlStartingWith;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

@RunWith(ConcordionRunner.class)
@ExpectedToPass
public class YahooTest {

    @Extension public ConcordionExtension extension = new CopyResourcesToOutputFolder(this.getClass());

    private Date date;
    private final FakeYahoo yahoo = new FakeYahoo();
    private final String host = "http://localhost:" + FakeYahoo.port;
    private final Yahoo client = new YqlWebService(anApacheClient(), host);
    private final Clock clock = new FrozenClock(date) {
        @Override
        public Date now() {
            return date;
        }
    };
    private final MarketData marketData = new YahooMarketData(client, clock);

    @Before
    public void start() {
        yahoo.start();
    }

    public String getPriceFromYahoo(String symbol, String date) throws ParseException {
        String response = "{\"query\":{\"results\":{\"quote\":{\"Close\":\"200.10\"}}}}";
        this.date = new Date(date);
        yahoo.stub(
                urlStartingWith("/v1/public/yql"),
                aResponse().withBody(response));
        marketData.getPrice(fromSymbol(symbol));
        List<LoggedRequest> requests = yahoo.requestsForHttpGet(urlStartingWith("/v1/public/yql"));
        return getFirstHttpMessageFrom(requests);
    }

    private static String getFirstHttpMessageFrom(List<LoggedRequest> requests) {
        if (requests.isEmpty())
            return "Not request made";
        return new WiremockLoggedRequest(requests).toString();
    }

    @After
    public void stop() {
        yahoo.stop();
    }

}
