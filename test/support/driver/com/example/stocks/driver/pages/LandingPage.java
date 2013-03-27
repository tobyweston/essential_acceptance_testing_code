package com.example.stocks.driver.pages;

import com.example.stocks.driver.Selenium;
import com.google.code.tempusfugit.concurrency.Callable;
import com.google.code.tempusfugit.condition.SelfDescribingMatcherCondition;
import com.google.code.tempusfugit.temporal.SelfDescribingCondition;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeoutException;

import static com.example.stocks.driver.pages.NonEmptyProbe.nonEmpty;
import static com.example.stocks.driver.pages.Probes.portfolioValuation;
import static com.google.code.tempusfugit.temporal.Duration.seconds;
import static com.google.code.tempusfugit.temporal.Timeout.timeout;
import static com.google.code.tempusfugit.temporal.WaitFor.waitFor;
import static com.google.code.tempusfugit.temporal.WaitFor.waitOrTimeout;

public class LandingPage {

    private final WebDriver driver = Selenium.createWebDriverForPlatform();

    public LandingPage navigateToLandingPage() {
        driver.get("http://localhost:7000/index.html");
        Selenium.verifyPageTitle(driver, "Value your Portfolio");
        return this;
    }

    public LandingPage requestValuationForShares(Integer numberOfShares) {
        driver.findElement(By.id("inputNumberOfShares")).sendKeys(numberOfShares.toString());
        driver.findElement(By.id("requestValuation")).click();
        return this;
    }

    public LandingPage assertThatPortfolioValue(Matcher<String> matcher) throws InterruptedException {
        waitFor(assertion(portfolioValuation(driver), matcher), timeout(seconds(5)));
        return this;
    }

    public static <T> SelfDescribingCondition assertion(Callable<T, RuntimeException> actual, Matcher<T> matcher) {
        return new SelfDescribingMatcherCondition(actual, matcher);
    }

    public String getPortfolioValue() throws InterruptedException {
        SelfDescribingCondition condition = nonEmpty(portfolioValuation(driver));
        try {
            waitOrTimeout(condition, timeout(seconds(1)));
        } catch (TimeoutException e) {
            return new StringDescription().appendDescriptionOf(condition).appendText(" was never found").toString();
        }
        return portfolioValuation(driver).call();
    }

    public String getErrorMessage() {
        return driver.findElement(By.id("requestValuationError")).getText();
    }

    public void quit() {
        Selenium.stop(driver);
    }

}
