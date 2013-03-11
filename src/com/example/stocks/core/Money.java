package com.example.stocks.core;

import java.math.BigDecimal;

public class Money {
    private final BigDecimal amount;

    public Money(String amount) {
        this(new BigDecimal(amount));
    }

    public Money(Integer amount) {
        this(new BigDecimal(amount));
    }

    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Money add(Money money) {
        return new Money(amount.add(money.amount));
    }

    public Money multiply(Integer scalar) {
        return new Money(amount.multiply(new BigDecimal(scalar)));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Money)) return false;
        return amount.equals(((Money) other).amount);
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }
}
