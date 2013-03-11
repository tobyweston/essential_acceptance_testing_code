package com.example.stocks.util;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JsonTest {

    private Json json = new Json("{\"name\": {\"firstName\": \"Pat\", \"surname\": \"Clifton\"}, \"age\": 32, \"occupation\": \"Postman\"}");

    @Test
    public void canAccessStrings() throws Exception {
        assertThat(json.getString("occupation"), is("Postman"));
    }

    @Test
    public void canAccessLongs() throws Exception {
        assertThat(json.getLong("age"), is(32l));
    }

    @Test
    public void canAccessNestedObjects() throws Exception {
        assertThat(json.getObject("name").getString("surname"), is("Clifton"));
    }

}
