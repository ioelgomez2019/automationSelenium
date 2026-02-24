package com.automation.stepdefinitions;

import com.automation.core.DriverManager;
import com.automation.core.ScreenshotUtil;
import com.automation.business.login.LoginBusiness;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.es.*;

/**
 * LoginSteps - Step Definitions para el módulo de Inicio de Sesión.
 * Conecta los pasos Gherkin (.feature) con la lógica de negocio (LoginBusiness).
 * 
 * Arquitectura: Test Layer - Step Definitions (Login Module)
 */
public class InicioSessionStep {

    private LoginBusiness loginBusiness;

    // ──────────────────────────────────────────────
    //  Hooks - Setup y Teardown del escenario
    // ──────────────────────────────────────────────

    @Before("@InicioSession or @login")
    public void antesDelEscenario(Scenario scenario) {
        System.out.println("\n🚀 Iniciando escenario: " + scenario.getName());
        DriverManager.iniciarDriver();
        loginBusiness = new LoginBusiness();
    }

    @After("@InicioSession or @login")
    public void despuesDelEscenario(Scenario scenario) {
        // Capturar screenshot si el escenario falló
        if (scenario.isFailed()) {
            System.out.println("❌ Escenario FALLIDO: " + scenario.getName());
            byte[] screenshot = ScreenshotUtil.capturarPantallaBytes();
            if (screenshot != null) {
                scenario.attach(screenshot, "image/png", "Screenshot del fallo");
            }
        } else {
            System.out.println("✅ Escenario EXITOSO: " + scenario.getName());
        }
        DriverManager.cerrarDriver();
    }

    // ──────────────────────────────────────────────
    //  Step Definitions - Gherkin en español
    // ──────────────────────────────────────────────

    @Dado("que el usuario abre el navegador y accede al sistema")
    public void queElUsuarioAbreElNavegador() {
        loginBusiness.abrirPaginaLogin();
    }

    @Cuando("el usuario ingresa el correo {string}")
    public void elUsuarioIngresaElCorreo(String email) {
        loginBusiness.ingresarEmail(email);
    }

    @Y("el usuario ingresa la contraseña {string}")
    public void elUsuarioIngresaLaContrasena(String contrasena) {
        loginBusiness.ingresarContrasena(contrasena);
    }

    @Y("el usuario hace clic en el botón {string}")
    public void elUsuarioHaceClicEnElBoton(String nombreBoton) {
        if (nombreBoton.equalsIgnoreCase("Ingresar")) {
            loginBusiness.clickBotonIngresar();
        }
    }

    @Cuando("el usuario no ingresa ningún dato")
    public void elUsuarioNoIngresaNingunDato() {
        System.out.println("⚠️ No se ingresan datos - campos vacíos.");
        // No hacer nada, los campos quedan vacíos
    }

    @Entonces("el usuario debe ver el dashboard principal")
    public void elUsuarioDebeVerElDashboard() {
        loginBusiness.validarLoginExitoso();
    }

    @Y("el mensaje de bienvenida debe contener {string}")
    public void elMensajeDeBienvenidaDebeContener(String texto) {
        loginBusiness.validarMensajeBienvenida(texto);
    }

    @Entonces("el sistema debe mostrar el mensaje de error {string}")
    public void elSistemaDebeMostrarMensajeDeError(String mensajeEsperado) {
        loginBusiness.validarMensajeError(mensajeEsperado);
    }

    @Entonces("el sistema debe mostrar validaciones de campos requeridos")
    public void elSistemaDebeMostrarValidaciones() {
        loginBusiness.validarCamposRequeridos();
    }
}
