package com.example.stocks.infrastructure.yahoo;

import bad.robot.http.HttpClient;
import bad.robot.http.HttpResponse;
import com.example.stocks.infrastructure.Defect;
import com.example.stocks.infrastructure.http.HttpResponseException;

import java.io.IOException;
import java.net.URL;

import static java.net.URLEncoder.encode;
import static java.text.MessageFormat.format;

public class YqlWebService implements Yahoo {

    private String url = "http://query.yahooapis.com";
    private String queryTemplate = "{0}/v1/public/yql?q={1}&format=json&env=store://datatables.org/alltableswithkeys";

    private final HttpClient http;

    public YqlWebService(HttpClient http) {
        this.http = http;
    }

    public YqlWebService(HttpClient http, String url) {
        this.http = http;
        this.url = url;
    }

    @Override
    public String executeQuery(String yql) {
        HttpResponse response = http.get(urlFor(yql));
        if (!response.ok())
            throw new HttpResponseException(response);
        return response.getContent().asString();
    }

    private URL urlFor(String yql) {
        try {
            return new URL(format(queryTemplate, url, encode(yql, "UTF-8")));
        } catch (IOException e) {
            throw new Defect(e);
        }
    }
}
