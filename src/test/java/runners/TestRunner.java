package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * TestRunner - Punto de entrada para la ejecución de pruebas BDD con TestNG.
 *
 * Cómo ejecutar desde IntelliJ IDEA:
 *   1. Clic derecho sobre esta clase → "Run 'TestRunner'"
 *   2. O desde terminal: mvn test
 *
 * Filtrar por tags desde línea de comando:
 *   mvn test -Dcucumber.filter.tags="@login"
 *   mvn test -Dcucumber.filter.tags="@InicioSession"
 *   mvn test -Dcucumber.filter.tags="@AgregarCarrito"
 *   mvn test -Dcucumber.filter.tags="@login and @LoginExitoso"
 *   mvn test -Dcucumber.filter.tags="@login or @AgregarCarrito"
 *
 * Ejecución E2E (encadenada):
 *   mvn test -Dcucumber.filter.tags="@login or @AgregarCarrito"
 */
@CucumberOptions(

    // ── Ubicación de los archivos .feature ──
    features = {
        "Scenarios/inicioSession",
        "Scenarios/agregarCarrito"
    },

    // ── Ubicación de los Step Definitions ──
    glue = {
        "com.automation.script.InicioSession.Feature",
        "com.automation.script.AgregarCarrito.Feature"
    },

    // ── Formato de reportes de salida ──
    plugin = {
        "pretty",                                        // Output en consola
        "html:target/reports/cucumber-report.html",     // Reporte HTML nativo
        "json:target/reports/cucumber-report.json",     // JSON para integración CI/CD
        "junit:target/reports/cucumber-junit.xml",      // XML para Jenkins/GitHub Actions
        "rerun:target/reports/rerun.txt",               // Lista de escenarios fallidos (para rerun)
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" // ExtentReports
    },

    // ── Mostrar snippets de steps sin implementar ──
    snippets = CucumberOptions.SnippetType.CAMELCASE,

    // ── Mostrar pasos en consola ──
    monochrome = true,

    // ── Tags para filtrar ejecución ──
    // Descomentar según lo que se quiera correr o usar -Dcucumber.filter.tags desde línea de comando:
    // tags = "@login",
    // tags = "@InicioSession",
    // tags = "@AgregarCarrito",
    // tags = "@login or @AgregarCarrito",                 // Ejecución E2E
    // tags = "@LoginExitoso or @AgregarProductoExitoso",
    // tags = "not @WIP",

    // ── Publicar reporte en Cucumber Cloud (opcional) ──
    publish = false
)
public class TestRunner extends AbstractTestNGCucumberTests {
    
    /**
     * Este método permite la ejecución paralela de escenarios.
     * parallel = true en @DataProvider habilita la paralelización.
     * 
     * Para ejecución paralela, descomentar parallel = true
     */
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
