package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Config;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/** Shared explicit-wait helpers. No Thread.sleep anywhere in the framework. */
public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Config.timeoutSeconds()));
        // ParaBank redraws parts of the page by AJAX; a stale element just means "try again"
        this.wait.ignoring(StaleElementReferenceException.class);
    }

    protected void open(String path) {
        driver.get(Config.baseUrl() + path);
    }

    protected WebElement visible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String value) {
        WebElement el = visible(locator);
        el.clear();
        if (value != null) {
            el.sendKeys(value);
        }
    }

    protected String text(By locator) {
        return visible(locator).getText().trim();
    }

    /** Waits until a select has at least one option (ParaBank fills account lists by AJAX). */
    protected Select loadedSelect(By locator) {
        wait.until(d -> !new Select(d.findElement(locator)).getOptions().isEmpty());
        return new Select(driver.findElement(locator));
    }

    protected List<String> optionValues(By locator) {
        return loadedSelect(locator).getOptions().stream()
                .map(o -> o.getAttribute("value")).collect(Collectors.toList());
    }

    /** Waits until the given text is visible anywhere in the page body. */
    public boolean pageShows(String expected) {
        try {
            wait.until(d -> d.findElement(By.tagName("body")).getText().contains(expected));
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public String bodyText() {
        return driver.findElement(By.tagName("body")).getText();
    }

    /** Waits for a text field that ParaBank fills by AJAX to get its value. */
    protected void waitForValue(By locator) {
        wait.until(d -> {
            String v = d.findElement(locator).getAttribute("value");
            return v != null && !v.isBlank();
        });
    }

    protected Object js(String script, Object... args) {
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }
}
