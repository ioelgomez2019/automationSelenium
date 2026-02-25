package com.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * ProductRunner - Executes product/shopping cart scenarios.
 * Usage: mvn test -Dtest=ProductRunner
 *        ejecutar-pruebas.bat product
 */
@CucumberOptions(
    features = {"Scenarios/agregarCarrito", "Scenarios/pasarelaPagos"},
    glue = {"com.automation.features"},
    plugin = {
        "pretty",
        "html:target/reports/product-report.html",
        "json:target/reports/product-report.json",
        "junit:target/reports/product-junit.xml"
    },
    tags = "@AgregarCarrito or @PasarelaPagos",
    monochrome = true,
    dryRun = false,
    publish = false
)
public class ProductRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
