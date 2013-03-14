package com.example.stocks.infrastructure;

import com.example.stocks.infrastructure.http.HttpClient;

import java.net.URL;

public class LoggingHttpClient implements HttpClient {

    private final HttpClient delegate;

    public LoggingHttpClient(HttpClient delegate) {
        this.delegate = delegate;
    }

    @Override
    public String get(URL url) {
        String response = null;
        try {
            System.out.printf("GET %s%n", url.toString());
            response = delegate.get(url);
            return response;
        } finally {
            if (response != null)
                System.out.printf("GET response %s%n", response);
        }
    }
}
