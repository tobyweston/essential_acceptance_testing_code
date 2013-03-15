package com.example.stocks.infrastructure;

public interface Configuration {

    public boolean useHttpProxy();
    public String getHttpProxyHost();
    public Integer getHttpProxyPort();
}
