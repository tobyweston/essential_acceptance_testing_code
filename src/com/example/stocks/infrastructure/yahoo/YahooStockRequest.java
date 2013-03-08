package com.example.stocks.infrastructure.yahoo;

import com.example.stocks.core.StockQuote;
import com.example.stocks.core.Symbol;
import com.example.stocks.util.Date;

import static java.text.MessageFormat.format;

public class YahooStockRequest {

    private static String queryTemplate = "select * from yahoo.finance.historicaldata where symbol = \"{0}\" and startDate = \"{1}\" and endDate = \"{1}\"";

    private Symbol stockSymbol;
    private Date date;

    public YahooStockRequest(Symbol symbol, Date date) {
        this.stockSymbol = symbol;
        this.date = date;
    }

    public StockQuote sendTo(Yahoo yahoo) {
        YahooDateFormatter formatter = new YahooDateFormatter();
        String query = format(queryTemplate, stockSymbol, date.format(formatter));
        return new YahooStockQuote(yahoo.executeQuery(query));
    }
}
