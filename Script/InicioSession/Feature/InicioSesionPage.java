package com.automation.script.InicioSession.Feature;

import com.automation.script.componentes.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * InicioSesionPage - Page Object de la página de Login.
 * Contiene todos los elementos mapeados de la pantalla de inicio de sesión.
 * Hereda métodos base de BasePage.
 */
public class InicioSesionPage extends BasePage {

    // ──────────────────────────────────────────────
    //  Localizadores / Elementos de la página
    // ──────────────────────────────────────────────

    // ------ Formulario de Login ------
    @FindBy(id = "email")
    private WebElement inputEmail;

    @FindBy(id = "password")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[@type='submit' and contains(text(),'Ingresar')]")
    private WebElement btnIngresar;

    @FindBy(xpath = "//button[contains(text(),'Ingresar')]")
    private WebElement btnIngresarAlternativo;

    // ------ Mensajes ------
    @FindBy(css = ".error-message, .alert-danger, [class*='error']")
    private WebElement lblMensajeError;

    @FindBy(css = "[class*='validation'], [class*='required']")
    private WebElement lblValidacionCampos;

    // ------ Dashboard (post login) ------
    @FindBy(css = ".dashboard, [class*='dashboard'], [id*='dashboard']")
    private WebElement seccionDashboard;

    @FindBy(css = ".welcome-message, [class*='welcome'], h1.greeting")
    private WebElement lblBienvenida;

    @FindBy(css = "nav, .sidebar, .menu-principal")
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
