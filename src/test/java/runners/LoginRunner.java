package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * LoginRunner - Runner específico para pruebas de Login/Inicio de Sesión.
 *
 * Ejecutar desde línea de comando:
 *   mvn test -Dtest=LoginRunner
 *   mvn test -DsuiteXmlFile=testng-login.xml
 */
@CucumberOptions(
    features = "Scenarios/inicioSession",
    glue = "com.automation.script.InicioSession.Feature",
    
    plugin = {
        "pretty",
        "html:target/reports/login-report.html",
        "json:target/reports/login-report.json",
        "junit:target/reports/login-junit.xml",
        "rerun:target/reports/login-rerun.txt",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    },
    
    snippets = CucumberOptions.SnippetType.CAMELCASE,
    monochrome = true,
    
    // Ejecutar solo pruebas con tag @login
    tags = "@login",
    
    publish = false
)
public class LoginRunner extends AbstractTestNGCucumberTests {
    
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
