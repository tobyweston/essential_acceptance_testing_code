package com.example.stocks.infrastructure.http;

import com.example.stocks.infrastructure.Configuration;

public class HttpClientFactory {

    private final Configuration configuration;

    public HttpClientFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    public HttpClient createClient() {
        if(configuration.useHttpProxy())
            return new ProxiedHttpClient(configuration.getHttpProxyHost(), configuration.getHttpProxyPort());
        return new DirectHttpClient();
    }
}
