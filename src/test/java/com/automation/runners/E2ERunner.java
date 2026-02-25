package com.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * E2ERunner - Executes End-to-End workflows that combine login + product operations.
 * Demonstrates reusable LoginBusiness across multiple scenarios.
 * Usage: mvn test -Dtest=E2ERunner
 *        ejecutar-pruebas.bat e2e
 */
@CucumberOptions(
    features = {"Scenarios/inicioSession", "Scenarios/agregarCarrito", "Scenarios/pasarelaPagos"},
    glue = {"com.automation.features"},
    plugin = {
        "pretty",
        "html:target/reports/e2e-report.html",
        "json:target/reports/e2e-report.json",
        "junit:target/reports/e2e-junit.xml"
    },
    tags = "@login or @AgregarCarrito or @PasarelaPagos",
    monochrome = true,
    dryRun = false,
    publish = false
)
public class E2ERunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
