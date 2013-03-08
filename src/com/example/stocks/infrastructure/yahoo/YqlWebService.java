package com.example.stocks.infrastructure.yahoo;

import com.example.stocks.infrastructure.http.HttpClient;

import java.io.IOException;
import java.net.URL;

import static java.net.URLEncoder.encode;
import static java.text.MessageFormat.format;

public class YqlWebService implements Yahoo {

    private static String urlTemplate = "http://query.yahooapis.com/v1/public/yql?q={0}&format=json&env=store://datatables.org/alltableswithkeys";

    private HttpClient client;

    public YqlWebService(HttpClient client) {
        this.client = client;
    }

    @Override
    public String executeQuery(String yql) {
        return client.get(urlFor(yql));
    }

    private URL urlFor(String yql) {
        try {
            return new URL(format(urlTemplate, encode(yql)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
