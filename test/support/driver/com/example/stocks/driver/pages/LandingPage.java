package com.example.stocks.driver.pages;

import com.example.stocks.driver.Selenium;
import com.google.code.tempusfugit.concurrency.Callable;
import com.google.code.tempusfugit.condition.SelfDescribingMatcherCondition;
import com.google.code.tempusfugit.temporal.ProbeFor;
import com.google.code.tempusfugit.temporal.SelfDescribingCondition;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeoutException;

import static com.example.stocks.driver.pages.LandingPage.Probes.errorMessage;
import static com.example.stocks.driver.pages.LandingPage.Probes.portfolioValuation;
import static com.google.code.tempusfugit.temporal.Duration.seconds;
import static com.google.code.tempusfugit.temporal.Timeout.timeout;
import static com.google.code.tempusfugit.temporal.WaitFor.waitFor;
import static com.google.code.tempusfugit.temporal.WaitFor.waitOrTimeout;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

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

    public String getPortfolioValue() throws InterruptedException {
        return waitForValueFrom(portfolioValuation(driver));
    }

    public String getErrorMessage() throws InterruptedException {
        return waitForValueFrom(errorMessage(driver));
    }

    private static <T> SelfDescribingCondition assertion(Callable<T, RuntimeException> actual, Matcher<T> matcher) {
        return new SelfDescribingMatcherCondition(actual, matcher);
    }

    public void quit() {
        Selenium.stop(driver);
    }

    private static String waitForValueFrom(ProbeFor<String> probe) throws InterruptedException {
        SelfDescribingCondition condition = assertion(probe, not(isEmptyOrNullString()));
        try {
            waitOrTimeout(condition, timeout(seconds(1)));
        } catch (TimeoutException e) {
            return new StringDescription().appendDescriptionOf(condition).toString();
        }
        return probe.call();
    }

    static class Probes {

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

        public static ProbeFor<String> errorMessage(final WebDriver driver) {
            return new ProbeFor<String>() {
                @Override
                public String call() throws RuntimeException {
                    return driver.findElement(By.id("requestValuationError")).getText();
                }

                @Override
                public void describeTo(Description description) {
                    description.appendText("error message from UI");
                }
            };
        }
    }
}
