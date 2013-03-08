package com.example.stocks.infrastructure;


import com.example.stocks.util.Date;

public interface Clock {
    Date now();
}
