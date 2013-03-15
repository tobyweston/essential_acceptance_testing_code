package com.example.stocks.infrastructure.rest;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class URLsTest {

    @Test
    public void shouldStripPackageAndClassName() {
        String url = URLs.defaultPackageUrl(getClass()).toString();
        assertThat(url, allOf(startsWith("file:/"), endsWith("test/essential_acceptance_testing_code/")));
        assertThat(url, not(containsString("com/example/stocks/infrastructure/rest")));
        assertThat(url, not(containsString("URLsTest.class")));
    }

}
