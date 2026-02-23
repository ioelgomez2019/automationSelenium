package com.automation.script.AgregarCarrito.Components;

import com.automation.script.componentes.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * CarritoComponent - Componentes UI del módulo AgregarCarrito.
 * Mapea elementos comunes reutilizados en el módulo del carrito.
 */
public class CarritoComponent {

    // ──────────────────────────────────────────────
    //  Elementos del Carrito
    // ──────────────────────────────────────────────

    @FindBy(css = ".cart-icon, .shopping-cart, [class*='cart-icon']")
    public WebElement iconoCarrito;

    @FindBy(css = ".cart-count, .badge-cart, [class*='cart-count']")
    public WebElement contadorCarrito;

    @FindBy(css = ".cart-empty, [class*='empty-cart']")
    public WebElement mensajeCarritoVacio;

    @FindBy(css = ".cart-total, [class*='cart-total']")
    public WebElement totalCarrito;

    @FindBy(css = ".btn-checkout, [class*='checkout']")
    public WebElement botonProcederCompra;

    // ──────────────────────────────────────────────
    //  Constructor
    // ──────────────────────────────────────────────

    public CarritoComponent() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }
}
