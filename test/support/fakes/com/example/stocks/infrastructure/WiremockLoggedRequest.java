package com.example.stocks.infrastructure;

import com.github.tomakehurst.wiremock.verification.LoggedRequest;

import java.util.List;

public class WiremockLoggedRequest {

    private final List<LoggedRequest> requests;

    public WiremockLoggedRequest(List<LoggedRequest> requests) {
        this.requests = requests;
    }

    public String toString() {
        LoggedRequest request = requests.get(0);
        StringBuilder builder = new StringBuilder()
                .append(request.getMethod())
                .append(" ")
                .append(request.getUrl())
                .append("\n")
                .append(new WiremockHttpHeaders(request.getHeaders()));
        return builder.toString();
    }
}
