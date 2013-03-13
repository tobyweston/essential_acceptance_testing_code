package com.example.stocks.infrastructure.rest;

import com.example.stocks.core.Valuation;
import com.googlecode.utterlyidle.annotations.GET;
import com.googlecode.utterlyidle.annotations.Path;
import com.googlecode.utterlyidle.annotations.PathParam;

public class PortfolioResource {

    private final Valuation valuation;

    public PortfolioResource(Valuation valuation) {
        this.valuation = valuation;
    }

    @GET
    @Path("/portfolio/{id}")
    public String value(@PathParam("id") String id) {
        return valuation.value().toString();
    }
}