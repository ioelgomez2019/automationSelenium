package com.automation.features.login;

import com.automation.features.login.LoginBusiness;
import com.automation.core.DriverManager;
import com.automation.core.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginSteps {
    private LoginBusiness loginBusiness;

    @Before
    public void setup(Scenario scenario) {
        System.out.println("\nScenario: " + scenario.getName());
        System.out.println("Tags: " + scenario.getSourceTagNames());
        DriverManager.iniciarDriver();
    }

    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ScreenshotUtil.capturarPantallaBytes();
            if (screenshot != null) {
                scenario.attach(screenshot, "image/png", "Failure Screenshot");
            }
        }

        try {
            DriverManager.cerrarDriver();
        } catch (Exception e) {
            System.out.println("Warning closing driver: " + e.getMessage());
        }
    }

    private LoginBusiness getLoginBusiness() {
        if (loginBusiness == null) {
            loginBusiness = new LoginBusiness();
        }
        return loginBusiness;
    }

    @Given("que el usuario abre el navegador y accede al sistema")
    public void queElUsuarioAbreElNavegador() {
        getLoginBusiness().abrirPaginaLogin();
    }

    @When("el usuario ingresa el correo {string}")
    public void elUsuarioIngresaElCorreo(String email) {
        getLoginBusiness().ingresarEmail(email);
    }

    @And("el usuario ingresa la contrasena {string}")
    public void elUsuarioIngresaLaContrasena(String password) {
        getLoginBusiness().ingresarContrasena(password);
    }

    @And("el usuario hace clic en el boton ingresar")
    public void elUsuarioHaceClicEnElBotonIngresar() {
        getLoginBusiness().clickBotonIngresar();
    }

    @Then("el usuario debe ver el dashboard principal")
    public void elUsuarioDebeVerElDashboard() {
        getLoginBusiness().validarLoginExitoso();
    }

    @Then("el sistema debe mostrar el mensaje de error {string}")
    public void elSistemaDebeMostrarMensajeDeError(String expectedError) {
        getLoginBusiness().validarMensajeError(expectedError);
    }

    @And("el mensaje de bienvenida debe contener {string}")
    public void elMensajeDeBienvenidaDebeContener(String expectedText) {
        String headerText = getLoginBusiness().getLoginPage().getText(By.className("app_logo"));
        Assert.assertTrue(headerText.contains(expectedText),
            "Expected dashboard header to contain: " + expectedText);
    }
}
