package com.example.stocks.infrastructure.http;

import bad.robot.http.CommonHttpClient;
import bad.robot.http.HttpClient;
import bad.robot.http.listener.LoggingHttpClient;
import com.example.stocks.infrastructure.Configuration;
import com.example.stocks.infrastructure.SystemConfiguration;
import org.apache.log4j.Logger;

import static bad.robot.http.HttpClients.anApacheClient;
import static bad.robot.http.configuration.Proxy.proxy;
import static com.googlecode.totallylazy.URLs.url;
import static java.lang.String.format;

public class HttpClientFactory {

    private final Configuration configuration;

    public static HttpClient defaultHttpClient() {
        return new LoggingHttpClient(new HttpClientFactory(new SystemConfiguration()).createClient(), Logger.getLogger(HttpClient.class));
    }

    public HttpClientFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    public HttpClient createClient() {
        CommonHttpClient http = anApacheClient();
        if (configuration.useHttpProxy())
            http.with(proxy(url(format("http://%s:%d", configuration.getHttpProxyHost(), configuration.getHttpProxyPort()))));
        return http;
    }
}
