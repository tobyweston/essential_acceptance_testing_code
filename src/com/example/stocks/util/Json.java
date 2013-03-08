package com.example.stocks.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Json {

    private final JSONObject root;

    public Json(String json) {
        try {
            JSONParser parser = new JSONParser();
            this.root = (JSONObject) parser.parse(json);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private Json(JSONObject root) {
        this.root = root;
    }

    public Json getObject(String property) {
        return new Json((JSONObject) root.get(property));
    }

    public String getString(String property) {
        return (String) root.get(property);
    }
}
