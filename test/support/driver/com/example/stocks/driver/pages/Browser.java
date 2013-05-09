package com.example.stocks.driver.pages;

import com.example.stocks.driver.Selenium;
import org.openqa.selenium.WebDriver;

public class Browser {

    private final WebDriver driver = Selenium.createWebDriverForPlatform();

    private Navigable current;

    public SummaryPage summaryPage() {
        if (current == null)
            current = gotoBaseUrl();
        current = (Navigable) current.navigateToSummary();
        return (SummaryPage) current;
    }

    public ManagementPage managementPage() {
        if (current == null)
            current = gotoBaseUrl();
        current = (Navigable) current.navigateToManagement();
        return (ManagementPage) current;
    }

    public SummaryPage gotoBaseUrl() {
        driver.get("http://localhost:7000/index.html");
        return new SummaryPage(driver);
    }

    public void quit() {
        Selenium.stop(driver);
    }

}
