package com.example.stocks.infrastructure.http;

import com.example.stocks.util.GreedyReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Proxy;
import java.net.URL;

public class DirectHttpClient implements HttpClient {

    DirectHttpClient() {
    }

    @Override
    public String get(URL url) {
        try {
            return new GreedyReader(new InputStreamReader(url.openConnection(getProxy()).getInputStream())).readAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected Proxy getProxy() {
        return Proxy.NO_PROXY;
    }
}
