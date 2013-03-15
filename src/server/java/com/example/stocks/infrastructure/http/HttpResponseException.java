package com.example.stocks.infrastructure.http;

import bad.robot.http.HttpResponse;

import static java.lang.String.format;

public class HttpResponseException extends RuntimeException {
    public HttpResponseException(HttpResponse response) {
        super(format("%d (%s)\n%s", response.getStatusCode(), response.getStatusMessage(), response.getContent().asString()));
    }
}
