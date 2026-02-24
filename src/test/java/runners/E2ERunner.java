package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * E2ERunner - Runner para ejecución End-to-End (Login + Agregar al Carrito).
 *
 * Ejecuta una secuencia completa de pruebas desde login hasta agregar productos.
 *
 * Ejecutar desde línea de comando:
 *   mvn test -Dtest=E2ERunner
 *   mvn test -DsuiteXmlFile=testng-e2e.xml
 */
@CucumberOptions(
    features = {
        "Scenarios/inicioSession",
        "Scenarios/agregarCarrito"
    },
    
    glue = {
        "com.automation.script.InicioSession.Feature",
        "com.automation.script.AgregarCarrito.Feature"
    },
    
    plugin = {
        "pretty",
        "html:target/reports/e2e-report.html",
        "json:target/reports/e2e-report.json",
        "junit:target/reports/e2e-junit.xml",
        "rerun:target/reports/e2e-rerun.txt",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    },
    
    snippets = CucumberOptions.SnippetType.CAMELCASE,
    monochrome = true,
    
    // Ejecutar login exitoso y agregar productos (flujo E2E completo)
    tags = "@LoginExitoso or @AgregarCarrito",
    
    publish = false
)
public class E2ERunner extends AbstractTestNGCucumberTests {
    
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
