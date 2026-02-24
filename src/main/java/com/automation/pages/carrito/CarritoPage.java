package com.automation.pages.carrito;

import com.automation.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * CarritoPage - Page Object del módulo de Carrito de Compras.
 * Contiene todos los elementos mapeados de las páginas de productos y carrito.
 * 
 * Arquitectura: Page Object (Carrito Module)
 * Responsabilidad: Mapeo de elementos y acciones básicas de UI
 */
public class CarritoPage extends BasePage {

    // ──────────────────────────────────────────────
    //  Elementos - Listado de Productos
    // ──────────────────────────────────────────────

    @FindBy(css = ".product-list, .products-container, [class*='product-list']")
    private WebElement listaProductos;

    @FindBy(css = ".product-item, .product-card, [class*='product-item']")
    private List<WebElement> todosLosProductos;

    // ──────────────────────────────────────────────
    //  Elementos - Carrito
    // ──────────────────────────────────────────────

    @FindBy(css = ".cart-count, .badge-cart, [class*='cart-count']")
    private WebElement contadorCarrito;

    @FindBy(css = ".cart-items, .cart-list, [class*='cart-items']")
    private WebElement listaItemsCarrito;

    @FindBy(css = ".cart-empty, [class*='empty-cart']")
    private WebElement mensajeCarritoVacio;

    // ──────────────────────────────────────────────
    //  Métodos de interacción con Productos
    // ──────────────────────────────────────────────

    public void seleccionarProductoPorNombre(String nombreProducto) {
        By locatorProducto = By.xpath(
            "//div[contains(@class,'product')]//h3[contains(text(),'" + nombreProducto + "')]" +
            " | //div[contains(@class,'product')]//span[contains(text(),'" + nombreProducto + "')]"
        );
        esperarElementoPorLocator(locatorProducto);
        WebElement producto = driver.findElement(locatorProducto);
        click(producto);
        System.out.println("🛍️ Producto seleccionado: " + nombreProducto);
    }

    public void clickAgregarAlCarrito() {
        By locatorBtn = By.xpath(
            "//button[contains(text(),'Agregar al Carrito')] | //button[contains(@class,'add-to-cart')]"
        );
        esperarElementoPorLocator(locatorBtn);
        WebElement btnAgregar = driver.findElement(locatorBtn);
        click(btnAgregar);
        System.out.println("➕ Producto agregado al carrito.");
    }

    public String obtenerContadorCarrito() {
        return obtenerTexto(contadorCarrito);
    }

    public boolean productoEstaEnCarrito(String nombreProducto) {
        try {
            By locator = By.xpath(
                "//div[contains(@class,'cart-item')]//span[contains(text(),'" + nombreProducto + "')]" +
                " | //td[contains(text(),'" + nombreProducto + "')]"
            );
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void eliminarProductoDelCarrito(String nombreProducto) {
        By locatorEliminar = By.xpath(
            "//div[contains(@class,'cart-item') and .//*[contains(text(),'" + nombreProducto + "')]]" +
            "//button[contains(@class,'remove') or contains(text(),'Eliminar') or contains(text(),'×')]"
        );
        esperarElementoPorLocator(locatorEliminar);
        WebElement btnEliminar = driver.findElement(locatorEliminar);
        click(btnEliminar);
        System.out.println("🗑️ Producto eliminado del carrito: " + nombreProducto);
    }

    public boolean carritoEstaVacio() {
        return estaVisible(mensajeCarritoVacio);
    }
}
