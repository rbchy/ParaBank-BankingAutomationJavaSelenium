package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;
import utils.TestContext;

public class Hooks {

    @Before
    public void startBrowser() {
        TestContext.resetScenario();
        DriverFactory.getDriver();
    }

    @After
    public void stopBrowser(Scenario scenario) {
        if (!DriverFactory.hasDriver()) {
            return;
        }
        WebDriver driver = DriverFactory.getDriver();
        try {
            if (scenario.isFailed()) {
                byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(png, "image/png", scenario.getName());
                // Printed to the log so a CI failure can be read without downloading artifacts
                String body = driver.findElement(By.tagName("body")).getText();
                System.out.println("---- FAILED: " + scenario.getName() + " @ " + driver.getCurrentUrl());
                System.out.println(body.length() > 1500 ? body.substring(0, 1500) : body);
                Object calls = ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                        "return performance.getEntriesByType('resource').map(e => e.name)"
                        + ".filter(n => n.includes('services')).join('\\n')");
                System.out.println("REST calls:\n" + calls);
                System.out.println("----");
            }
        } catch (Exception e) {
            System.out.println("Could not capture failure details: " + e.getMessage());
        } finally {
            DriverFactory.quitDriver();
        }
    }
}
