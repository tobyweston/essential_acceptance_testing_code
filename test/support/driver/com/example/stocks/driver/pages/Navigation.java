package com.example.stocks.driver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

class Navigation implements Page, Navigable {

    private final WebDriver driver;

    Navigation(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public Page navigateToSummary() {
        driver.findElement(By.id("navigate-to-summary")).click();
        return this;
    }

    @Override
    public Page navigateToManagement() {
        driver.findElement(By.id("navigate-to-management")).click();
        return this;
    }

}
