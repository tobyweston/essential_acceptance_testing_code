package com.example.stocks.driver.pages;

import com.example.stocks.driver.Selenium;
import org.openqa.selenium.WebDriver;

public class LandingPage {

    private final WebDriver driver = Selenium.createWebDriverForPlatform();

    public LandingPage navigateToLandingPage() {
        driver.get("http://localhost:8000/index.html");
        Selenium.verifyPageTitle(driver, "Value your Portfolio");
        return this;
    }

    public String getNumberOfShares() {
        return "";
    }

    public void requestValuationForShares(Integer numberOfShares) {
        return;
    }

    public void quit() {
        Selenium.stop(driver);
    }
}
