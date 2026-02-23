package com.automation.script.InicioSession.Feature;

import com.automation.script.componentes.DriverManager;
import com.automation.script.config.ConfigManager;
import org.junit.Assert;

/**
 * InicioSesion - Lógica de negocio del módulo de Inicio de Sesión.
 * Orquesta las acciones de la página y aplica las validaciones del negocio.
 * Actúa como capa intermedia entre los Step Definitions y el Page Object.
 */
public class InicioSesion {

    private InicioSesionPage page;
    private ConfigManager config;

    // ──────────────────────────────────────────────
    //  Constructor
    // ──────────────────────────────────────────────

    public InicioSesion() {
        this.page   = new InicioSesionPage();
        this.config = ConfigManager.getInstance();
    }

    // ──────────────────────────────────────────────
    //  Flujo: Navegar al sistema
    // ──────────────────────────────────────────────

    public void abrirPaginaLogin() {
        DriverManager.navegarAlAmbiente();
        System.out.println("🌐 Página de login abierta.");
    }

    // ──────────────────────────────────────────────
    //  Flujo: Login completo (usa credenciales del config)
    // ──────────────────────────────────────────────

    public void realizarLoginConCredencialesDelAmbiente() {
        String usuario     = config.getUsuario();
        String contrasena  = config.getContrasena();
        realizarLogin(usuario, contrasena);
    }

    public void realizarLogin(String email, String contrasena) {
        page.ingresarEmail(email);
        page.ingresarContrasena(contrasena);
        page.clickBotonIngresar();
        System.out.println("🔑 Login ejecutado para: " + email);
    }

    // ──────────────────────────────────────────────
    //  Flujo: Solo ingresar campos (sin submit)
    // ──────────────────────────────────────────────

    public void ingresarEmail(String email) {
        page.ingresarEmail(email);
    }

    public void ingresarContrasena(String contrasena) {
        page.ingresarContrasena(contrasena);
    }

    public void clickBotonIngresar() {
        page.clickBotonIngresar();
    }

    // ──────────────────────────────────────────────
    //  Validaciones de Negocio
    // ──────────────────────────────────────────────

    public void validarLoginExitoso() {
        Assert.assertTrue(
            "❌ El dashboard no es visible tras el login.",
            page.dashboardEsVisible() || page.menuPrincipalEsVisible()
        );
        System.out.println("✅ Login exitoso - Dashboard visible.");
    }

    public void validarMensajeBienvenida(String textoParcial) {
        String textoReal = page.obtenerTextoBienvenida();
        Assert.assertTrue(
            "❌ El mensaje de bienvenida no contiene: " + textoParcial + " | Texto real: " + textoReal,
            textoReal.toLowerCase().contains(textoParcial.toLowerCase())
        );
        System.out.println("✅ Bienvenida correcta: " + textoReal);
    }

    public void validarMensajeError(String mensajeEsperado) {
        String mensajeReal = page.obtenerMensajeError();
        Assert.assertTrue(
            "❌ El mensaje de error no contiene: " + mensajeEsperado + " | Mensaje real: " + mensajeReal,
            mensajeReal.toLowerCase().contains(mensajeEsperado.toLowerCase())
        );
        System.out.println("✅ Mensaje de error correcto: " + mensajeReal);
    }

    public void validarCamposRequeridos() {
        Assert.assertTrue(
            "❌ No se mostraron mensajes de validación en campos requeridos.",
            page.mensajeValidacionEsVisible()
        );
        System.out.println("✅ Validaciones de campos requeridos visibles.");
    }
}
