package com.example.stocks.infrastructure.yahoo;

import com.example.stocks.infrastructure.SystemConfiguration;
import com.example.stocks.infrastructure.http.HttpClient;
import com.example.stocks.infrastructure.http.HttpClientFactory;
import com.example.stocks.util.Json;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

public class YqlWebServiceIntegrationTest {

    public static final String query = "select * from yahoo.finance.historicaldata where symbol = \"YHOO\" and startDate = \"2009-09-07\" and endDate = \"2009-09-11\"";

    @Test
    public void yahooServiceIsUp() throws Exception {
        HttpClient client = new HttpClientFactory(new SystemConfiguration()).createClient();
        Json response = new Json(new YqlWebService(client).executeQuery(query));
        assertThat(response.getObject("query").getLong("count"), is(greaterThan(0l)));
    }
}
