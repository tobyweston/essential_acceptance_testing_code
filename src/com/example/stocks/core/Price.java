package com.example.stocks.core;

import java.math.BigDecimal;

public class Price {
    private final BigDecimal price;

    public Price(String price) {
        this(new BigDecimal(price));
    }

    public Price(Integer price) {
        this(new BigDecimal(price));
    }

    private Price(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Price)) return false;
        return price.equals(((Price) other).price);
    }

    @Override
    public int hashCode() {
        return price.hashCode();
    }
}
