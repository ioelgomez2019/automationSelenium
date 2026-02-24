package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * TestRunner - Main runner executing all tests.
 * Usage: mvn test
 *        mvn test -Dtest=TestRunner
 *        ejecutar-pruebas.bat all
 */
@CucumberOptions(
    features = {"Scenarios"},
    glue = {"com.automation.stepdefinitions", "com.automation.hooks"},
    plugin = {
        "pretty",
        "html:target/reports/full-report.html",
        "json:target/reports/full-report.json",
        "junit:target/reports/full-junit.xml"
    },
    monochrome = true,
    dryRun = false,
    publish = false
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
