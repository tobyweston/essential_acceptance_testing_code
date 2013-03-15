package com.example.stocks.infrastructure.rest;

import com.googlecode.utterlyidle.annotations.GET;
import com.googlecode.utterlyidle.annotations.Path;

public class Version {

    @GET
    @Path("/version")
    public String version() {
        return "1.0";
    }
}