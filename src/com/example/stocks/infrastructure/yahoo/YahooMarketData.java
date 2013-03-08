package com.example.stocks.infrastructure.yahoo;

import com.example.stocks.core.Price;
import com.example.stocks.core.Stock;
import com.example.stocks.infrastructure.MarketData;
import com.example.stocks.util.Date;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;

public class YahooMarketData implements MarketData {

    private static String requestTemplate = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol%20%3D%20%22{0}%22%20and%20startDate%20%3D%20%22{1}%22%20and%20endDate%20%3D%20%22{1}%22&format=json&env=http%3A%2F%2Fdatatables.org%2Falltables.env";

    private YahooDateFormatter formatter = new YahooDateFormatter();

    @Override
    public Price priceFor(Stock stock, Date date) {
        String request = MessageFormat.format(requestTemplate, stock.getSymbol(), date.format(formatter));
        JSONObject response = parse(send(request));
        JSONObject query = (JSONObject)response.get("query");
        JSONObject results = (JSONObject)query.get("results");
        JSONObject quote = (JSONObject)results.get("quote");
        return new Price((String)quote.get("Close"));
    }

    private JSONObject parse(String json) {
        try {
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(json);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private String send(String request) {
        Reader input = null;
        Writer output = new StringWriter();

        try {
            URL url = new URL(request);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("ocs-proxy.eu.hedani.net", 8080));
            URLConnection connection = url.openConnection(proxy);
            input = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            int charsRead = 0;
            char[] buffer = new char[512];

            while((charsRead = input.read(buffer)) > 0) {
                output.write(buffer, 0, charsRead);
            }

            output.flush();
            return output.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(input != null) input.close();
            } catch (IOException e) {
                // oh well, we tried
            }
            try {
                if(output != null) output.close();
            } catch (IOException e) {
                // oh well we tried
            }
        }
    }
}
