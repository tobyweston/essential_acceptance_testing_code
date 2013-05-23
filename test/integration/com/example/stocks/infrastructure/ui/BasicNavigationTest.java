package com.example.stocks.infrastructure.ui;

import com.example.stocks.driver.pages.Browser;
import com.example.stocks.infrastructure.HttpServer;
import com.example.stocks.infrastructure.client.UiServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.googlecode.totallylazy.matchers.Matchers.is;

public class BasicNavigationTest {

    private final HttpServer ui = new UiServer();
    private final Browser browser = new Browser();

    @Before
    public void start() {
        ui.start();
    }

    @Test
    public void canDoBasicNavigation() {
        browser.navigateToSummaryPage().navigateToManagementPage().navigateToSummaryPage();
    }

    @Test
    public void canNavigateFromSummaryToManagementPageAndPerformPageOperations() {
        browser.navigateToSummaryPage().navigateToManagementPage().sell();
    }

    @Test
    public void canNavigateFromManagementToSummaryPageAndPerformPageOperations() throws InterruptedException {
        browser.navigateToManagementPage().navigateToSummaryPage().getPortfolioValue();
    }

    @Test
    public void canTrustBrowserObjectToRetainContextAllowingFlexibleNavigation() throws InterruptedException {
        String defaultGrandTotalInUi = "43,101.89";
        browser.navigateToManagementPage().sell();
        browser.navigateToSummaryPage().getPortfolioValue();
        browser.navigateToManagementPage().sell();
        browser.navigateToSummaryPage().getPortfolioValue();
        browser.navigateToManagementPage().sell().navigateToSummaryPage().assertThatPortfolioValue(is(defaultGrandTotalInUi)).navigateToManagementPage().buy();
    }

    @After
    public void stop() {
        ui.stop();
        browser.quit();
    }

}
