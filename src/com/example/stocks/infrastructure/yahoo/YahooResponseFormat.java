package com.example.stocks.infrastructure.yahoo;

public enum YahooResponseFormat {

    JSON("json");

    private final String queryParameter;

    YahooResponseFormat(String queryParameter) {
        this.queryParameter = queryParameter;
    }

    public String asQueryParameter() {
        return queryParameter;
    }
}
