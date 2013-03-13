package com.example.stocks.infrastructure.rest;

import com.example.stocks.core.Money;
import com.example.stocks.core.Valuation;
import com.googlecode.utterlyidle.annotations.GET;
import com.googlecode.utterlyidle.annotations.Path;
import com.googlecode.utterlyidle.annotations.PathParam;

public class Portfolio {

    private final Valuation valuation;

    public Portfolio() {
        this.valuation = new Valuation() {
            @Override
            public Money value() {
                return new Money(0);
            }
        };
    }

    public Portfolio(Valuation valuation) {
        this.valuation = valuation;
    }

    @GET
    @Path("/portfolio/{id}")
    public String value(@PathParam("id") String id) {
        return valuation.value().toString();
    }
}