package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * TestRunner - Punto de entrada para la ejecución de pruebas BDD.
 *
 * Cómo ejecutar desde IntelliJ IDEA:
 *   1. Clic derecho sobre esta clase → "Run 'TestRunner'"
 *   2. O desde terminal: mvn test
 *
 * Filtrar por tags:
 *   mvn test -Dcucumber.filter.tags="@LoginExitoso"
 *   mvn test -Dcucumber.filter.tags="@InicioSession"
 *   mvn test -Dcucumber.filter.tags="@AgregarCarrito"
 */
@RunWith(Cucumber.class)
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
        "rerun:target/reports/rerun.txt"                // Lista de escenarios fallidos (para rerun)
    },

    // ── Mostrar snippets de steps sin implementar ──
    snippets = io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE,

    // ── Mostrar pasos en consola ──
    monochrome = false,

    // ── Tags para filtrar ejecución ──
    // Descomentar según lo que se quiera correr:
    // tags = "@InicioSession",
    // tags = "@AgregarCarrito",
    // tags = "@LoginExitoso or @AgregarProductoExitoso",
    // tags = "not @WIP",

    // ── Publicar reporte en Cucumber Cloud (opcional) ──
    publish = false
)
public class TestRunner {
    // Esta clase no necesita cuerpo.
    // Cucumber se encarga de ejecutar todos los escenarios configurados arriba.
}
