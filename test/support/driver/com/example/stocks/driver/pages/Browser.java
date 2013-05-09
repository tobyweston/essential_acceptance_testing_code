package com.example.stocks.driver.pages;

import com.example.stocks.driver.Selenium;
import org.openqa.selenium.WebDriver;

public class Browser {

    private final WebDriver driver = Selenium.createWebDriverForPlatform();

    private Page currentPage;

    public ValuationPage valuationPage() {
        if (currentPage == null)
            currentPage = gotoValuationPageUrl();
        return (ValuationPage) currentPage;
    }

    private ValuationPage gotoValuationPageUrl() {
        driver.get("http://localhost:7000/index.html");
        return new ValuationPage(driver);
    }

    public void quit() {
        Selenium.stop(driver);
    }

}
