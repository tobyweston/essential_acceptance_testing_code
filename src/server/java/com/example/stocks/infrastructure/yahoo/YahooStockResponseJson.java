package com.example.stocks.infrastructure.yahoo;

import com.example.stocks.core.Money;
import com.example.stocks.core.Stock;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class YahooStockResponseJson implements Stock {

    private final JSONObject root;

    public YahooStockResponseJson(String json) {
        try {
            this.root = (JSONObject) new JSONParser().parse(json);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean error() {
        return root.containsKey("error");
    }

    public boolean hasResults() {
        if (getQueryObject() == null)
            return false;
        if (getResultsObject() == null)
            return false;
        return true;
    }

    public Money getClosingPrice() {
        if (error() || !hasResults())
            return new Money(0);
        return new Money((String) getQuoteObject().get("Close"));
    }

    private JSONObject getQueryObject() {
        return (JSONObject) root.get("query");
    }

    private JSONObject getResultsObject() {
        return (JSONObject) getQueryObject().get("results");
    }

    private JSONObject getQuoteObject() {
        return (JSONObject) getResultsObject().get("quote");
    }
}
