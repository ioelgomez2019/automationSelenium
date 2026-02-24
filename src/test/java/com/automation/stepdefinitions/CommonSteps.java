package com.automation.stepdefinitions;

import com.automation.core.DriverManager;
import com.automation.core.ScreenshotUtil;
import com.automation.business.LoginBusiness;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

/**
 * CommonSteps - Shared step definitions used across all workflows.
 * Contains login and common setup/teardown operations.
 */
public class CommonSteps {

    protected LoginBusiness loginBusiness;

    @Before
    public void setup(Scenario scenario) {
        System.out.println("\n▶ Scenario: " + scenario.getName());
        DriverManager.iniciarDriver();
        loginBusiness = new LoginBusiness();
    }

    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("✗ FAILED: " + scenario.getName());
            byte[] screenshot = ScreenshotUtil.capturarPantallaBytes();
            if (screenshot != null) {
                scenario.attach(screenshot, "image/png", "Failure Screenshot");
            }
        } else {
            System.out.println("✓ PASSED: " + scenario.getName());
        }
        DriverManager.cerrarDriver();
    }

    // ──────────────────────────────────────────────
    //  REUSABLE Login Steps (for all workflows)
    // ──────────────────────────────────────────────

    @Given("user opens login page")
    public void userOpensLoginPage() {
        loginBusiness.openLoginPage();
    }

    @When("user enters username {string}")
    public void userEntersUsername(String username) {
        loginBusiness.getLoginPage().enterUsername(username);
    }

    @When("user enters password {string}")
    public void userEntersPassword(String password) {
        loginBusiness.getLoginPage().enterPassword(password);
    }

    @When("user clicks login button")
    public void userClicksLoginButton() {
        loginBusiness.getLoginPage().clickLoginButton();
    }

    @When("user logs in with {string} and {string}")
    public void userLogsInWith(String username, String password) {
        loginBusiness.login(username, password);
    }

    @When("user logs in as standard user")
    public void userLogsInAsStandardUser() {
        loginBusiness.loginAsStandardUser();
    }

    @Then("login should be successful")
    public void loginShouldBeSuccessful() {
        loginBusiness.validateLoginSuccess();
    }

    @Then("error message should contain {string}")
    public void errorMessageShouldContain(String expectedError) {
        loginBusiness.validateLoginFailure(expectedError);
    }

    @Given("que el usuario abre el navegador y accede al sistema")
    public void queElUsuarioAbreElNavegador() {
        loginBusiness.abrirPaginaLogin();
    }

    @When("el usuario ingresa el correo {string}")
    public void elUsuarioIngresaElCorreo(String email) {
        loginBusiness.ingresarEmail(email);
    }

    @And("el usuario ingresa la contraseña {string}")
    public void elUsuarioIngresaLaContrasena(String password) {
        loginBusiness.ingresarContrasena(password);
    }

    @And("el usuario hace clic en el botón \"Ingresar\"")
    public void elUsuarioHaceClicEnElBoton() {
        loginBusiness.clickBotonIngresar();
    }

    @Then("el usuario debe ver el dashboard principal")
    public void elUsuarioDebeVerElDashboard() {
        loginBusiness.validarLoginExitoso();
    }

    @Then("el sistema debe mostrar el mensaje de error {string}")
    public void elSistemaDebeMostrarMensajeDeError(String expectedError) {
        loginBusiness.validarMensajeError(expectedError);
    }
}
