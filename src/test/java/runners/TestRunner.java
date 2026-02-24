package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * TestRunner - Main runner executing all tests.
 * Usage: mvn test OR mvn test -Dtest=TestRunner
 */
@CucumberOptions(
    features = {"Scenarios"},
    glue = {"com.automation.stepdefinitions"},
    plugin = {
        "pretty",
        "html:target/reports/full-report.html",
        "json:target/reports/full-report.json",
        "junit:target/reports/full-junit.xml"
    },
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
