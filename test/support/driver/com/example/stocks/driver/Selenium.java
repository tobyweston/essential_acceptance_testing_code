package com.example.stocks.driver;

import com.google.code.tempusfugit.temporal.ProbeFor;
import org.hamcrest.Description;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.util.HashMap;

import static com.google.code.tempusfugit.condition.Conditions.assertion;
import static com.google.code.tempusfugit.temporal.Duration.millis;
import static com.google.code.tempusfugit.temporal.Timeout.timeout;
import static com.google.code.tempusfugit.temporal.WaitFor.waitFor;
import static org.hamcrest.Matchers.is;
import static org.openqa.selenium.Platform.*;
import static org.openqa.selenium.remote.CapabilityType.SUPPORTS_JAVASCRIPT;

public class Selenium {

    public static WebDriver createWebDriverForPlatform() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(PROXY, createProxy());
        capabilities.setCapability(SUPPORTS_JAVASCRIPT, true);
        if (WINDOWS.is(getCurrent()))
            return new InternetExplorerDriver(capabilities);
        if (MAC.is(getCurrent()))
            return new SafariDriver(capabilities);
        return new HtmlUnitDriver(capabilities);
    }

    public static void verifyPageTitle(WebDriver driver, String pageTitle) {
        try {
            waitFor(assertion(Probes.pageTitle(driver), is(pageTitle)), timeout(millis(250)));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void stop(WebDriver driver) {
        try {
            driver.close();
        } catch (Exception ignored) {
            ignored.printStackTrace(System.err);
        } finally {
            try {
                driver.quit();
            } catch (Exception ignored) {
                ignored.printStackTrace(System.err);
            }
        }
    }

    public static String initialPageTitleFor(WebDriver driver) {
        if (driver instanceof ChromeDriver)
            return "WebDriver";
        return "";
    }

    public static void dumpPageSourceOrScreenShot(WebDriver driver) {
        System.out.println(driver.getPageSource());
    }

    private static Proxy createProxy() {
        return new Proxy(new HashMap<String, Object>() {{
            put("httpProxy", "localhost:8888");
        }});
    }

    private static class Probes {
        private static ProbeFor<String> pageTitle(final WebDriver driver) {
            return new ProbeFor<String>() {
                @Override
                public String call() throws RuntimeException {
                    return driver.getTitle();
                }

                @Override
                public void describeTo(Description description) {
                    description.appendText("page title");
                }
            };
        }
    }

}
