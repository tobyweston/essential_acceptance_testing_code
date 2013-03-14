package com.example.stocks.util;

import com.example.stocks.infrastructure.Clock;

public class RealClock implements Clock {
    @Override
    public Date now() {
        return new Date();
    }
}
