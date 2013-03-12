package com.example.stocks.util;

import org.junit.Test;

import java.io.StringReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GreedyReaderTest {

    @Test
    public void returnsFullContentsOfReader() throws Exception {
        String contents = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
        StringReader input = new StringReader(contents);
        assertThat(new GreedyReader(input).readAll(), is(contents));
    }

}
