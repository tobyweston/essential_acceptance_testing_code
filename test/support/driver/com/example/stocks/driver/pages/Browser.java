package com.example.stocks.driver.pages;

import com.example.stocks.driver.Selenium;
import org.openqa.selenium.WebDriver;

public class Browser implements NavigablePage {

    private final WebDriver driver = Selenium.createWebDriverForPlatform();

    private NavigablePage current;

    @Override
    public SummaryPage navigateToSummaryPage() {
        if (current == null)
            current = gotoBaseUrl();
        current = current.navigateToSummaryPage();
        return (SummaryPage) current;
    }

    @Override
    public ManagementPage navigateToManagementPage() {
        if (current == null)
            current = gotoBaseUrl();
        current = current.navigateToManagementPage();
        return (ManagementPage) current;
    }

    private SummaryPage gotoBaseUrl() {
        driver.get("http://localhost:7000/index.html");
        return new SummaryPage(driver);
    }

    public void quit() {
        Selenium.stop(driver);
    }
}
