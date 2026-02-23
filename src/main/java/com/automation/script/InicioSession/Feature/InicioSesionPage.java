package com.automation.script.InicioSession.Feature;

import com.automation.script.componentes.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * InicioSesionPage - Page Object de la página de Login.
 * Contiene todos los elementos mapeados de la pantalla de inicio de sesión (Saucedemo).
 * Hereda métodos base de BasePage.
 */
public class InicioSesionPage extends BasePage {

    // ──────────────────────────────────────────────
    //  Localizadores / Elementos de la página
    // ──────────────────────────────────────────────

    // ------ Formulario de Login - SAUCEDEMO ------
    @FindBy(id = "user-name")
    private WebElement inputEmail;

    @FindBy(id = "password")
    private WebElement inputPassword;

    @FindBy(id = "login-button")
    private WebElement btnIngresar;

    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement btnIngresarAlternativo;

    // ------ Mensajes - SAUCEDEMO ------
    @FindBy(css = "h3[data-test='error']")
    private WebElement lblMensajeError;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement lblValidacionCampos;

    // ------ Dashboard (post login) - SAUCEDEMO ------
    @FindBy(css = ".inventory_list")
    private WebElement seccionDashboard;

    @FindBy(css = ".app_logo")
    private WebElement lblBienvenida;

    @FindBy(css = ".bm-burger-button, #react-burger-menu-btn")
    private WebElement menuPrincipal;

    // ──────────────────────────────────────────────
    //  Métodos de interacción con los elementos
    // ──────────────────────────────────────────────

    public void ingresarEmail(String email) {
        escribir(inputEmail, email);
        System.out.println("📧 Email ingresado: " + email);
    }

    public void ingresarContrasena(String contrasena) {
        escribir(inputPassword, contrasena);
        System.out.println("🔒 Contraseña ingresada.");
    }

    public void clickBotonIngresar() {
        try {
            click(btnIngresar);
        } catch (Exception e) {
            click(btnIngresarAlternativo);
        }
        System.out.println("🖱️ Clic en botón Ingresar");
    }

    public String obtenerMensajeError() {
        return obtenerTexto(lblMensajeError);
    }

    public String obtenerTextoBienvenida() {
        return obtenerTexto(lblBienvenida);
    }

    public boolean dashboardEsVisible() {
        return estaVisible(seccionDashboard);
    }

    public boolean menuPrincipalEsVisible() {
        return estaVisible(menuPrincipal);
    }

    public boolean mensajeValidacionEsVisible() {
        return estaVisible(lblValidacionCampos);
    }
}
