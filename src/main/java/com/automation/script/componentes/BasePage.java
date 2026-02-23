package com.automation.script.componentes;

import com.automation.script.config.ConfigManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * BasePage - Clase base para todos los Page Objects del proyecto.
 * Contiene métodos genéricos reutilizables para interacción con la UI.
 */
public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    // ──────────────────────────────────────────────
    //  Constructor
    // ──────────────────────────────────────────────

    public BasePage() {
        this.driver = DriverManager.getDriver();
        int timeout = ConfigManager.getInstance().getTimeoutExplicito();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        PageFactory.initElements(driver, this);
    }

    // ──────────────────────────────────────────────
    //  Acciones básicas
    // ──────────────────────────────────────────────

    protected void click(WebElement elemento) {
        esperarClickable(elemento);
        elemento.click();
    }

    protected void escribir(WebElement elemento, String texto) {
        esperarVisible(elemento);
        elemento.clear();
        elemento.sendKeys(texto);
    }

    protected String obtenerTexto(WebElement elemento) {
        esperarVisible(elemento);
        return elemento.getText().trim();
    }

    protected String obtenerValor(WebElement elemento) {
        esperarVisible(elemento);
        return elemento.getAttribute("value");
    }

    protected boolean estaVisible(WebElement elemento) {
        try {
            return elemento.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    // ──────────────────────────────────────────────
    //  Esperas explícitas
    // ──────────────────────────────────────────────

    protected WebElement esperarVisible(WebElement elemento) {
        return wait.until(ExpectedConditions.visibilityOf(elemento));
    }

    protected WebElement esperarClickable(WebElement elemento) {
        return wait.until(ExpectedConditions.elementToBeClickable(elemento));
    }

    protected void esperarTexto(WebElement elemento, String texto) {
        wait.until(ExpectedConditions.textToBePresentInElement(elemento, texto));
    }

    protected void esperarUrl(String urlParcial) {
        wait.until(ExpectedConditions.urlContains(urlParcial));
    }

    protected void esperarElementoPorLocator(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // ──────────────────────────────────────────────
    //  Utilidades de página
    // ──────────────────────────────────────────────

    protected String obtenerTituloPagina() {
        return driver.getTitle();
    }

    protected String obtenerUrlActual() {
        return driver.getCurrentUrl();
    }

    protected void scrollHastaElemento(WebElement elemento) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elemento);
    }

    protected void clickConJS(WebElement elemento) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", elemento);
    }

    protected void esperarSegundos(int segundos) {
        try {
            Thread.sleep(segundos * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
