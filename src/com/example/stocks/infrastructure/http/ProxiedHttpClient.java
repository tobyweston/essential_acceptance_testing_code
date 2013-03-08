package com.example.stocks.infrastructure.http;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class ProxiedHttpClient extends DirectHttpClient {

    private final String host;
    private final int port;

    public ProxiedHttpClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    protected Proxy getProxy() {
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port));
    }
}
