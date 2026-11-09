package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Runs every feature through TestNG. By default @known-defect scenarios are left out;
 * -Dcucumber.filter.tags="..." replaces that filter (e.g. "@smoke" or "@known-defect").
 * Scenarios run one after another: they share the customer registered by the first one.
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions", "hooks"},
        tags = "not @known-defect",
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
