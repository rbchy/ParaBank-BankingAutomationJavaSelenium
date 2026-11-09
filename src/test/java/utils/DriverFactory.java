package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * The one place that creates the browser. One Chrome per scenario;
 * Selenium Manager finds the matching driver, so no extra library is needed.
 * Run with -Dheadless=true (what CI does) for a browser without a window.
 */
public final class DriverFactory {

    private static WebDriver driver;

    private DriverFactory() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications", "--no-sandbox",
                    "--disable-dev-shm-usage", "--window-size=1440,1000");
            if (Config.headless()) {
                options.addArguments("--headless=new");
            }
            driver = new ChromeDriver(options);
        }
        return driver;
    }

    public static boolean hasDriver() {
        return driver != null;
    }

    public static void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                driver = null;
            }
        }
    }
}
