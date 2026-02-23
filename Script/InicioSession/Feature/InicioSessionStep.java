package com.automation.script.InicioSession.Feature;

import com.automation.script.componentes.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.es.*;

/**
 * InicioSessionStep - Step Definitions para el módulo de Inicio de Sesión.
 * Conecta los pasos Gherkin (.feature) con la lógica de negocio (InicioSesion.java).
 */
public class InicioSessionStep {

    private InicioSesion inicioSesion;

    // ──────────────────────────────────────────────
    //  Hooks - Setup y Teardown del escenario
    // ──────────────────────────────────────────────

    @Before("@InicioSession")
    public void antesDelEscenario(Scenario scenario) {
        System.out.println("\n🚀 Iniciando escenario: " + scenario.getName());
        DriverManager.iniciarDriver();
        inicioSesion = new InicioSesion();
    }

    @After("@InicioSession")
    public void despuesDelEscenario(Scenario scenario) {
        // Capturar screenshot si el escenario falló
        if (scenario.isFailed()) {
            System.out.println("❌ Escenario FALLIDO: " + scenario.getName());
            byte[] screenshot = com.automation.script.componentes.ScreenshotUtil.capturarPantallaBytes();
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
        inicioSesion.abrirPaginaLogin();
    }

    @Cuando("el usuario ingresa el correo {string}")
    public void elUsuarioIngresaElCorreo(String email) {
        inicioSesion.ingresarEmail(email);
    }

    @Y("el usuario ingresa la contraseña {string}")
    public void elUsuarioIngresaLaContrasena(String contrasena) {
        inicioSesion.ingresarContrasena(contrasena);
    }

    @Y("el usuario hace clic en el botón {string}")
    public void elUsuarioHaceClicEnElBoton(String nombreBoton) {
        if (nombreBoton.equalsIgnoreCase("Ingresar")) {
            inicioSesion.clickBotonIngresar();
        }
    }

    @Cuando("el usuario no ingresa ningún dato")
    public void elUsuarioNoIngresaNingunDato() {
        System.out.println("⚠️ No se ingresan datos - campos vacíos.");
        // No hacer nada, los campos quedan vacíos
    }

    @Entonces("el usuario debe ver el dashboard principal")
    public void elUsuarioDebeVerElDashboard() {
        inicioSesion.validarLoginExitoso();
    }

    @Y("el mensaje de bienvenida debe contener {string}")
    public void elMensajeDeBienvenidaDebeContener(String texto) {
        inicioSesion.validarMensajeBienvenida(texto);
    }

    @Entonces("el sistema debe mostrar el mensaje de error {string}")
    public void elSistemaDebeMostrarMensajeDeError(String mensajeEsperado) {
        inicioSesion.validarMensajeError(mensajeEsperado);
    }

    @Entonces("el sistema debe mostrar validaciones de campos requeridos")
    public void elSistemaDebeMostrarValidaciones() {
        inicioSesion.validarCamposRequeridos();
    }
}
