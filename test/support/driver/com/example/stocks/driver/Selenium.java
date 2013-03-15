package com.example.stocks.driver;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.util.HashMap;

import static java.lang.String.format;
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
        if (!driver.getTitle().equals(pageTitle))
            throw new RuntimeException(format("current page was expected to be '%s' but was '%s'", pageTitle, driver.getTitle()));
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

}
