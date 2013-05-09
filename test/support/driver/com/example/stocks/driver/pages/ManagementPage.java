package com.example.stocks.driver.pages;

import com.example.stocks.driver.Selenium;
import org.openqa.selenium.WebDriver;

public class ManagementPage implements Page, Navigable {

    private final WebDriver driver;

    public ManagementPage(WebDriver driver) {
        Selenium.verifyPageTitle(driver, "Manage Portfolio");
        this.driver = driver;
    }

    public ManagementPage buy() {
        return this;
    }

    public ManagementPage sell() {
        return this;
    }

    @Override
    public SummaryPage navigateToSummary() {
        new Navigation(driver).navigateToSummary();
        return new SummaryPage(driver);
    }

    @Override
    public ManagementPage navigateToManagement() {
        return this;
    }
}
