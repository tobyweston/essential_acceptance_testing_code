package com.example.stocks.util;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DateTest {

    @Rule public final JUnitRuleMockery context = new JUnitRuleMockery();

    @Mock private DateFormatter formatter;

    @Test
    public void acceptsFormatter() throws Exception {
        Date date = new Date(2013, 1, 29);

        context.checking(new Expectations(){{
            oneOf(formatter).format(2013, 1, 29); will(returnValue("result"));
        }});

        assertThat(date.format(formatter), is("result"));
    }
}
