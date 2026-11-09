package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Runs every feature through TestNG. Filter with -Dcucumber.filter.tags="@smoke".
 * Scenarios run one after another: they share the customer registered by the first one.
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions", "hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber.json",
                "junit:target/cucumber.xml"
        },
        monochrome = true
)
public class TestNGCucumberRunner extends AbstractTestNGCucumberTests {
}
