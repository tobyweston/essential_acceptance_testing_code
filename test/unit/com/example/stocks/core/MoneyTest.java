package com.example.stocks.core;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MoneyTest {

    @Test
    public void supportsAddition() {
        assertThat(new Money(6).add(new Money(4)), is(new Money(10)));
    }

    @Test
    public void supportsScalarMuliplication() {
        assertThat(new Money(7).multiply(9), is(new Money(63)));
    }
}
