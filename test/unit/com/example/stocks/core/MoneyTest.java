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
    public void supportsAdditionOfNull() {
        assertThat(new Money(6).add(null), is(new Money(6)));
    }

    @Test
    public void supportsScalarMultiplication() {
        assertThat(new Money(7).multiply(9), is(new Money(63)));
    }

    @Test
    public void supportsNullMultiplicand() {
        assertThat(new Money(7).multiply(null), is(new Money(7)));
    }

}
