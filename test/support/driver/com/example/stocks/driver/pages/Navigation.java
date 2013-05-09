package com.example.stocks.driver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

class Navigation implements NavigablePage {

    private final WebDriver driver;

    Navigation(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public NavigablePage navigateToSummary() {
        driver.findElement(By.id("navigate-to-summary")).click();
        return this;
    }

    @Override
    public NavigablePage navigateToManagement() {
        driver.findElement(By.id("navigate-to-management")).click();
        return this;
    }

}
