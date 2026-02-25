package com.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * LoginRunner - Executes only @login tagged scenarios.
 * Usage: mvn test -Dtest=LoginRunner
 *        ejecutar-pruebas.bat login
 */
@CucumberOptions(
    features = "Scenarios/inicioSession",
    glue = {"com.automation.features"},
    plugin = {
        "pretty",
        "html:target/reports/login-report.html",
        "json:target/reports/login-report.json",
        "junit:target/reports/login-junit.xml"
    },
    tags = "@login",
    monochrome = true,
    dryRun = false,
    publish = false
)
public class LoginRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
