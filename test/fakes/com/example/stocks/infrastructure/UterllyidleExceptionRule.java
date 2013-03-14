package com.example.stocks.infrastructure;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.net.MalformedURLException;
import java.net.URL;

import static com.example.stocks.infrastructure.http.HttpClientFactory.defaultHttpClient;

public class UterllyidleExceptionRule implements TestRule {

    @Override
    public Statement apply(final Statement statement, Description description) {
//    public Statement apply(final Statement statement, FrameworkMethod method, Object target) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    statement.evaluate();
                } catch (Exception failure) {
                    logExceptions();
                    throw failure;
                }
            }

            private void logExceptions() throws MalformedURLException {
                try {
                    System.out.println(defaultHttpClient().get(new URL("http://localhost:8000/utterlyidle/exceptions")));
                } catch (Throwable e) {
                    System.out.println("Warning: unable to log exceptions from utterlyidle, " + e.getMessage());
                }
            }
        };
    }

}
