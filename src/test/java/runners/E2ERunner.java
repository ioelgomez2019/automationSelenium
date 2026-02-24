package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * E2ERunner - Executes End-to-End workflows that combine login + product operations.
 * Demonstrates reusable LoginBusiness across multiple scenarios.
 * Usage: mvn test -Dtest=E2ERunner
 */
@CucumberOptions(
    features = {"Scenarios/inicioSession", "Scenarios/agregarCarrito"},
    glue = {"com.automation.stepdefinitions"},
    plugin = {
        "pretty",
        "html:target/reports/e2e-report.html",
        "json:target/reports/e2e-report.json",
        "junit:target/reports/e2e-junit.xml"
    },
    tags = "@login or @AgregarCarrito",
    monochrome = true
)
public class E2ERunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
