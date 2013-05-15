package com.example.stocks.infrastructure;

import com.github.tomakehurst.wiremock.http.HttpHeaders;

class WiremockHttpHeaders {

    private final HttpHeaders headers;

    public WiremockHttpHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String header : headers.keys())
            builder.append(headers.getHeader(header));
        return builder.toString();
    }
}
