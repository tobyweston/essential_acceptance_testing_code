package com.example.stocks.infrastructure.yahoo;

import com.example.stocks.core.Stock;
import com.example.stocks.core.Symbol;
import com.example.stocks.util.Date;

import static java.text.MessageFormat.format;

public class YahooStockRequest {

    private static String queryTemplate = "select * from yahoo.finance.historicaldata where symbol = \"{0}\" and startDate = \"{1}\" and endDate = \"{1}\"";

    private Symbol symbol;
    private Date date;

    public YahooStockRequest(Symbol symbol, Date date) {
        this.symbol = symbol;
        this.date = date;
    }

    public Stock sendTo(Yahoo yahoo) {
        YahooDateFormatter formatter = new YahooDateFormatter();
        String query = format(queryTemplate, symbol.toSymbol(), date.format(formatter));
        return new YahooStockResponseJson(yahoo.executeQuery(query));
    }
}
