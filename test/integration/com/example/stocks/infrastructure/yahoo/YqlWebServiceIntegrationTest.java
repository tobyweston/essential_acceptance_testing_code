package com.example.stocks.infrastructure.yahoo;

import org.junit.Test;

import static com.example.stocks.infrastructure.http.HttpClientFactory.defaultHttpClient;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class YqlWebServiceIntegrationTest {

    private static final String query = "select * from yahoo.finance.historicaldata where symbol = \"YHOO\" and startDate = \"2009-09-07\" and endDate = \"2011-09-11\"";

    @Test
    public void checkYahooWebServiceIsUp() {
        YqlWebService yahoo = new YqlWebService(defaultHttpClient());
        assertThat(yahoo.executeQuery(query), containsString("results"));
    }
}
