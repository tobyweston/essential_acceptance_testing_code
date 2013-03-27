package com.example.stocks.driver.pages;

import com.google.code.tempusfugit.temporal.ProbeFor;
import org.hamcrest.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Probes {

    public static ProbeFor<String> portfolioValuation(final WebDriver driver) {
        return new ProbeFor<String>() {
            @Override
            public String call() throws RuntimeException {
                return driver.findElement(By.id("requestValuationResult")).getText();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("portfolio value from UI");
            }
        };
    }
}
