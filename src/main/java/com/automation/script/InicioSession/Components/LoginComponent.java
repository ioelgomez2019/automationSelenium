package com.automation.script.InicioSession.Components;

import com.automation.script.componentes.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * LoginComponent - Componentes UI específicos del módulo InicioSession.
 * Mapea elementos comunes que se reutilizan entre páginas del módulo.
 */
public class LoginComponent {

    // ──────────────────────────────────────────────
    //  Elementos del formulario de login
    // ──────────────────────────────────────────────

    @FindBy(id = "email")
    public WebElement campoEmail;

    @FindBy(id = "password")
    public WebElement campoPassword;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement botonIngresar;

    @FindBy(css = ".error-message, .alert-danger, [class*='error']")
    public WebElement mensajeError;

    @FindBy(css = ".validation-message, .field-required, [class*='invalid']")
    public WebElement mensajeValidacion;

    @FindBy(css = ".logo, [class*='logo']")
    public WebElement logoEmpresa;

    // ──────────────────────────────────────────────
    //  Constructor - inicializa los elementos
    // ──────────────────────────────────────────────

    public LoginComponent() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }
}
