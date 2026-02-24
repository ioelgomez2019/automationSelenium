package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * CarritoRunner - Runner específico para pruebas del Carrito de Compras.
 *
 * Ejecutar desde línea de comando:
 *   mvn test -Dtest=CarritoRunner
 *   mvn test -DsuiteXmlFile=testng-carrito.xml
 */
@CucumberOptions(
    features = "Scenarios/agregarCarrito",
    glue = "com.automation.stepdefinitions",
    
    plugin = {
        "pretty",
        "html:target/reports/carrito-report.html",
        "json:target/reports/carrito-report.json",
        "junit:target/reports/carrito-junit.xml",
        "rerun:target/reports/carrito-rerun.txt",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    },
    
    snippets = CucumberOptions.SnippetType.CAMELCASE,
    monochrome = true,
    
    // Ejecutar solo pruebas del carrito
    tags = "@AgregarCarrito",
    
    publish = false
)
public class CarritoRunner extends AbstractTestNGCucumberTests {
    
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
