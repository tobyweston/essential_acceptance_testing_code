package com.example.stocks.driver.pages;

import com.example.stocks.driver.Selenium;
import org.openqa.selenium.WebDriver;

public class ManagementPage implements NavigablePage {

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
    public SummaryPage navigateToSummaryPage() {
        new Navigation(driver).navigateToSummaryPage();
        return new SummaryPage(driver);
    }

    @Override
    public ManagementPage navigateToManagementPage() {
        return this;
    }
}
