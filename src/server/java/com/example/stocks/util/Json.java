package com.example.stocks.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Json {

    private final JSONObject root;

    public Json(String json) {
        try {
            this.root = (JSONObject) new JSONParser().parse(json);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private Json(JSONObject root) {
        this.root = root;
    }

    public Boolean isEmpty() {
        return root == null || root.isEmpty();
    }

    public Json getObject(String property) {
        return new Json((JSONObject) root.get(property));
    }

    public Long getLong(String property) {
        return (Long) root.get(property);
    }

    public String getString(String property) {
        return (String) root.get(property);
    }
}
