package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * ProductRunner - Executes product/shopping cart scenarios.
 * Usage: mvn test -Dtest=ProductRunner
 */
@CucumberOptions(
    features = "Scenarios/agregarCarrito",
    glue = {"com.automation.stepdefinitions"},
    plugin = {
        "pretty",
        "html:target/reports/product-report.html",
        "json:target/reports/product-report.json",
        "junit:target/reports/product-junit.xml"
    },
    tags = "@AgregarCarrito",
    monochrome = true
)
public class ProductRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
