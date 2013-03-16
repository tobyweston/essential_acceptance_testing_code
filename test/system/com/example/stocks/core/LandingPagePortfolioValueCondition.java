package com.example.stocks.core;

import com.example.stocks.driver.pages.LandingPage;
import com.google.code.tempusfugit.temporal.Condition;
import org.hamcrest.Matcher;

class LandingPagePortfolioValueCondition implements Condition {

    private final LandingPage ui;
    private final Matcher<String> matcher;

    public static LandingPagePortfolioValueCondition portfolioValueFrom(LandingPage ui, Matcher<String> matcher) {
        return new LandingPagePortfolioValueCondition(ui, matcher);
    }

    private LandingPagePortfolioValueCondition(LandingPage ui, Matcher<String> matcher) {
        this.ui = ui;
        this.matcher = matcher;
    }

    @Override
    public boolean isSatisfied() {
        return matcher.matches(ui.getPortfolioValue());
    }
}
