package com.example.stocks.infrastructure.yahoo;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class YahooDateFormatterTest {

    @Test
    public void formatsDateWithLeadingZeros() throws Exception {
        YahooDateFormatter formatter = new YahooDateFormatter();
        assertThat(formatter.format(2013, 3, 1), is("2013-03-01"));
    }
}
