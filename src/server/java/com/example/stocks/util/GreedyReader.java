package com.example.stocks.util;

import java.io.*;

public class GreedyReader {

    private Reader reader;

    public GreedyReader(Reader reader) {
        this.reader = reader;
    }

    public String readAll() {
        Writer writer = new StringWriter();

        try {
            int charsRead;
            char[] buffer = new char[512];
            while((charsRead = reader.read(buffer)) > 0) {
                writer.write(buffer, 0, charsRead);
            }

            writer.flush();
            return writer.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            close(reader, writer);
        }
    }

    private void close(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            try {
                closeable.close();
            } catch (IOException e) {
                // we tried
            }
        }
    }
}
