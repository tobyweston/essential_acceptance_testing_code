package com.example.stocks.infrastructure.rest;

import com.example.stocks.core.Valuation;
import com.googlecode.utterlyidle.Response;
import com.googlecode.utterlyidle.annotations.GET;
import com.googlecode.utterlyidle.annotations.Path;
import com.googlecode.utterlyidle.annotations.PathParam;

import static com.googlecode.utterlyidle.ResponseBuilder.response;
import static com.googlecode.utterlyidle.Status.OK;

public class PortfolioResource {

    private final Valuation valuation;

    public PortfolioResource(Valuation valuation) {
        this.valuation = valuation;
    }

    @GET
    @Path("/portfolio/{id}")
    public Response value(@PathParam("id") String id) {
        return response(OK).entity(valuation.value().toString()).header("Access-Control-Allow-Origin", "*").build();
    }
}