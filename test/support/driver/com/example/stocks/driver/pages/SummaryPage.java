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

import static com.example.stocks.driver.pages.SummaryPage.Probes.errorMessage;
import static com.example.stocks.driver.pages.SummaryPage.Probes.portfolioValuation;
import static com.google.code.tempusfugit.temporal.Duration.seconds;
import static com.google.code.tempusfugit.temporal.Timeout.timeout;
import static com.google.code.tempusfugit.temporal.WaitFor.waitFor;
import static com.google.code.tempusfugit.temporal.WaitFor.waitOrTimeout;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

public class SummaryPage implements NavigablePage {

    private final WebDriver driver;

    public SummaryPage(WebDriver driver) {
        Selenium.verifyPageTitle(driver, "Portfolio Summary");
        this.driver = driver;
    }

    public SummaryPage refreshValuation() {
        driver.findElement(By.id("refresh-valuation")).click();
        return this;
    }

    public SummaryPage assertThatPortfolioValue(Matcher<String> matcher) throws InterruptedException {
        waitFor(assertion(portfolioValuation(driver), matcher), timeout(seconds(5)));
        return this;
    }

    public String getPortfolioValue() throws InterruptedException {
        return waitForValueFrom(portfolioValuation(driver));
    }

    public String getErrorMessage() throws InterruptedException {
        return waitForValueFrom(errorMessage(driver));
    }

    @Override
    public SummaryPage navigateToSummaryPage() {
        return this;
    }

    @Override
    public ManagementPage navigateToManagementPage() {
        new Navigation(driver).navigateToManagementPage();
        return new ManagementPage(driver);
    }

    private static <T> SelfDescribingCondition assertion(Callable<T, RuntimeException> actual, Matcher<T> matcher) {
        return new SelfDescribingMatcherCondition(actual, matcher);
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
                    return driver.findElement(By.id("grand-total")).getText();
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
                    return driver.findElement(By.id("alert-error-message")).getText();
                }

                @Override
                public void describeTo(Description description) {
                    description.appendText("error message from UI");
                }
            };
        }
    }
}
