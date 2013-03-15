package com.example.stocks.infrastructure;

import java.util.Properties;

public class SystemConfiguration implements Configuration {

    private final Properties properties = System.getProperties();

    @Override
    public boolean useHttpProxy() {
        return getHttpProxyHost() != null && getHttpProxyPort() != null;
    }

    @Override
    public String getHttpProxyHost() {
        return properties.getProperty("http.proxy.host");
    }

    @Override
    public Integer getHttpProxyPort() {
        return Integer.parseInt(properties.getProperty("http.proxy.port"));
    }
}
