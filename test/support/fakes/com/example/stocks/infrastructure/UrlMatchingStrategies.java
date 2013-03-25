package com.example.stocks.infrastructure;

import com.github.tomakehurst.wiremock.client.UrlMatchingStrategy;

public class UrlMatchingStrategies {

    public static UrlMatchingStrategy urlStartingWith(String url) {
        UrlMatchingStrategy urlStrategy = new UrlMatchingStrategy();
        urlStrategy.setUrlPattern(url + ".*");
        return urlStrategy;
    }

    public static UrlMatchingStrategy urlEndingWith(String url) {
        UrlMatchingStrategy urlStrategy = new UrlMatchingStrategy();
        urlStrategy.setUrlPattern(".*" + url);
        return urlStrategy;
    }
}
