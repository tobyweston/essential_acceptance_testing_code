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
        browser.gotoBaseUrl().navigateToManagement().navigateToSummary();
    }

    @Test
    public void canNavigateFromSummaryToManagementPageAndPerformPageOperations() {
        browser.gotoBaseUrl().navigateToManagement().sell();
    }

    @Test
    public void canNavigateFromManagementToSummaryPageAndPerformPageOperations() throws InterruptedException {
        browser.gotoBaseUrl().navigateToManagement().navigateToSummary().getPortfolioValue();
    }

    @Test
    public void canTrustBrowserObjectToRetainContextAllowingFlexibleNavigation() throws InterruptedException {
        browser.gotoBaseUrl().navigateToManagement().sell();
        browser.summaryPage().getPortfolioValue();
        browser.managementPage().sell();
        browser.summaryPage().getPortfolioValue();
        browser.managementPage().sell().navigateToSummary().assertThatPortfolioValue(is("")).navigateToManagement().buy();
    }

    @After
    public void stop() {
        ui.stop();
        browser.quit();
    }

}
