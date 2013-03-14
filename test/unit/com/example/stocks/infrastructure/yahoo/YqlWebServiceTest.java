package com.example.stocks.infrastructure.yahoo;

import com.example.stocks.infrastructure.http.HttpClient;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class YqlWebServiceTest {

    @Rule public final JUnitRuleMockery context = new JUnitRuleMockery();

    private final HttpClient http = context.mock(HttpClient.class);
    private final YqlWebService yqlWebService = new YqlWebService(http);


    @Test
    public void executeQueryAgainstYahooUrl() throws MalformedURLException {
        final URL url = new URL("http://query.yahooapis.com/v1/public/yql?q=foo&format=json&env=store://datatables.org/alltableswithkeys");
        context.checking(new Expectations() {{
            oneOf(http).get(url);
        }});
        yqlWebService.executeQuery("foo");
    }

    @Test
    public void executeQueryUrlEncodingQuery() throws MalformedURLException {
        final URL url = new URL("http://query.yahooapis.com/v1/public/yql?q=encoded+example&format=json&env=store://datatables.org/alltableswithkeys");
        context.checking(new Expectations() {{
            oneOf(http).get(url);
        }});
        yqlWebService.executeQuery("encoded example");
    }

    @Test
    public void executeQueryAgainstInjectedUrl() throws MalformedURLException {
        final YqlWebService yqlWebService = new YqlWebService(http, "http://example.com");
        final URL url = new URL("http://example.com/v1/public/yql?q=bar&format=json&env=store://datatables.org/alltableswithkeys");
        context.checking(new Expectations() {{
            oneOf(http).get(url);
        }});
        yqlWebService.executeQuery("bar");
    }

    @Test
    public void executeQueryReturnsResponse() {
        context.checking(new Expectations() {{
            oneOf(http).get(with(any(URL.class))); will(returnValue("OK"));
        }});
        assertThat(yqlWebService.executeQuery("bar"), is("OK"));
    }

}
