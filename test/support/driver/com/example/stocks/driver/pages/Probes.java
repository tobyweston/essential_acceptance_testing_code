package com.example.stocks.driver.pages;

import com.google.code.tempusfugit.temporal.ProbeFor;
import org.hamcrest.Description;

public class Probes {

    public static ProbeFor<String> portfolioValuationFrom(final LandingPage ui) {
        return new ProbeFor<String>() {
            @Override
            public String call() throws RuntimeException {
                return ui.getPortfolioValue();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("portfolio value from UI (timed out waiting)");
            }
        };
    }
}
