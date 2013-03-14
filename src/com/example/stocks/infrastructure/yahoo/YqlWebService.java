package com.example.stocks.infrastructure.yahoo;

import com.example.stocks.infrastructure.http.HttpClient;

import java.io.IOException;
import java.net.URL;

import static java.net.URLEncoder.encode;
import static java.text.MessageFormat.format;

public class YqlWebService implements Yahoo {

    private String url = "http://query.yahooapis.com";
    private String queryTemplate = url + "/v1/public/yql?q={0}&format=json&env=store://datatables.org/alltableswithkeys";

    private final HttpClient client;

    public YqlWebService(HttpClient client) {
        this.client = client;
    }

    public YqlWebService(HttpClient client, String url) {
        this.client = client;
        this.url = url;
    }

    @Override
    public String executeQuery(String yql) {
        return client.get(urlFor(yql));
    }

    private URL urlFor(String yql) {
        try {
            return new URL(format(queryTemplate, encode(yql, "UTF-8")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
