package com.example.stocks.infrastructure.yahoo;

import com.example.stocks.util.Date;
import com.example.stocks.util.Json;

import static com.example.stocks.infrastructure.yahoo.YahooResponseFormat.*;
import static java.text.MessageFormat.format;

public class YahooStockPriceRequest implements YahooRequest {

    private static String queryTemplate = "select * from yahoo.finance.historicaldata where symbol = \"{0}\" and startDate = \"{1}\" and endDate = \"{1}\"";

    private String stockSymbol;
    private Date date;

    public YahooStockPriceRequest(String stockSymbol, Date date) {
        this.stockSymbol = stockSymbol;
        this.date = date;
    }

    @Override
    public YahooStockQuote executeWith(Yahoo yahoo) {
        YahooDateFormatter formatter = new YahooDateFormatter();
        String query = format(queryTemplate, stockSymbol, date.format(formatter));
        return new YahooStockQuote(new Json(yahoo.executeQuery(query, JSON)));
    }
}
