package com.automation.stepdefinitions;

import com.automation.business.LoginBusiness;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

/**
 * CommonSteps - Shared step definitions for login workflows.
 * NOTE: No @Before/@After here - those are in Hooks.java
 * This allows other classes to use composition without inheritance conflicts.
 */
public class CommonSteps {

    protected LoginBusiness loginBusiness;

    // ──────────────────────────────────────────────
    //  REUSABLE Login Steps (for all workflows)
    // ──────────────────────────────────────────────

    @Given("user opens login page")
    public void userOpensLoginPage() {
        if (loginBusiness == null) {
            loginBusiness = new LoginBusiness();
        }
        loginBusiness.openLoginPage();
    }

    @When("user enters username {string}")
    public void userEntersUsername(String username) {
        if (loginBusiness == null) {
            loginBusiness = new LoginBusiness();
        }
        loginBusiness.getLoginPage().enterUsername(username);
    }

    @When("user enters password {string}")
    public void userEntersPassword(String password) {
        if (loginBusiness == null) {
            loginBusiness = new LoginBusiness();
        }
        loginBusiness.getLoginPage().enterPassword(password);
    }

    @When("user clicks login button")
    public void userClicksLoginButton() {
        if (loginBusiness == null) {
            loginBusiness = new LoginBusiness();
        }
        loginBusiness.getLoginPage().clickLoginButton();
    }

    @When("user logs in with {string} and {string}")
    public void userLogsInWith(String username, String password) {
        if (loginBusiness == null) {
            loginBusiness = new LoginBusiness();
        }
        loginBusiness.login(username, password);
    }

    @When("user logs in as standard user")
    public void userLogsInAsStandardUser() {
        if (loginBusiness == null) {
            loginBusiness = new LoginBusiness();
        }
        loginBusiness.loginAsStandardUser();
    }

    @Then("login should be successful")
    public void loginShouldBeSuccessful() {
        if (loginBusiness == null) {
            loginBusiness = new LoginBusiness();
        }
        loginBusiness.validateLoginSuccess();
    }

    @Then("error message should contain {string}")
    public void errorMessageShouldContain(String expectedError) {
        if (loginBusiness == null) {
            loginBusiness = new LoginBusiness();
        }
        loginBusiness.validateLoginFailure(expectedError);
    }

    @Given("que el usuario abre el navegador y accede al sistema")
    public void queElUsuarioAbreElNavegador() {
        if (loginBusiness == null) {
            loginBusiness = new LoginBusiness();
        }
        loginBusiness.abrirPaginaLogin();
    }

    @When("el usuario ingresa el correo {string}")
    public void elUsuarioIngresaElCorreo(String email) {
        if (loginBusiness == null) {
            loginBusiness = new LoginBusiness();
        }
        loginBusiness.ingresarEmail(email);
    }

    @And("el usuario ingresa la contraseña {string}")
    public void elUsuarioIngresaLaContrasena(String password) {
        if (loginBusiness == null) {
            loginBusiness = new LoginBusiness();
        }
        loginBusiness.ingresarContrasena(password);
    }

    @And("el usuario hace clic en el botón \"Ingresar\"")
    public void elUsuarioHaceClicEnElBoton() {
        if (loginBusiness == null) {
            loginBusiness = new LoginBusiness();
        }
        loginBusiness.clickBotonIngresar();
    }

    @Then("el usuario debe ver el dashboard principal")
    public void elUsuarioDebeVerElDashboard() {
        if (loginBusiness == null) {
            loginBusiness = new LoginBusiness();
        }
        loginBusiness.validarLoginExitoso();
    }

    @Then("el sistema debe mostrar el mensaje de error {string}")
    public void elSistemaDebeMostrarMensajeDeError(String expectedError) {
        if (loginBusiness == null) {
            loginBusiness = new LoginBusiness();
        }
        loginBusiness.validarMensajeError(expectedError);
    }

    @When("el usuario no ingresa ningún dato")
    public void elUsuarioNoIngresaNingunDato() {
        System.out.println("⚠ No data entered - empty login form");
    }

    @And("el mensaje de bienvenida debe contener {string}")
    public void elMensajeDeBienvenidaDebeContener(String expectedText) {
        if (loginBusiness == null) {
            loginBusiness = new LoginBusiness();
        }
        String headerText = loginBusiness.getLoginPage().getText(org.openqa.selenium.By.className("app_logo"));
        org.testng.Assert.assertTrue(headerText.contains(expectedText), 
            "Expected dashboard header to contain: " + expectedText);
    }
}
