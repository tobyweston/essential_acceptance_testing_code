package com.example.stocks.core;

import com.example.stocks.infrastructure.Clock;
import com.example.stocks.util.Date;

public class FrozenClock implements Clock {

    private Date now;

    public FrozenClock(Date date) {
        this.now = date;
    }

    @Override
    public Date now() {
        return now;
    }
}
