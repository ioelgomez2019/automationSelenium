package com.automation.stepdefinitions;

import com.automation.core.DriverManager;
import com.automation.core.ScreenshotUtil;
import com.automation.business.carrito.CarritoBusiness;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.es.*;

/**
 * CarritoSteps - Step Definitions para el módulo de Carrito de Compras.
 * Conecta los pasos Gherkin (.feature) con la lógica de negocio (CarritoBusiness).
 * 
 * Arquitectura: Test Layer - Step Definitions (Carrito Module)
 */
public class AgregarCarritoStep {

    private CarritoBusiness carritoBusiness;

    // ──────────────────────────────────────────────
    //  Hooks
    // ──────────────────────────────────────────────

    @Before("@AgregarCarrito")
    public void antesDelEscenario(Scenario scenario) {
        System.out.println("\n🚀 Iniciando escenario: " + scenario.getName());
        DriverManager.iniciarDriver();
        carritoBusiness = new CarritoBusiness();
    }

    @After("@AgregarCarrito")
    public void despuesDelEscenario(Scenario scenario) {
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

    @Dado("que el usuario está autenticado en el sistema")
    public void queElUsuarioEstaAutenticado() {
        carritoBusiness.autenticarseEnElSistema();
    }

    @Y("que el usuario se encuentra en la página de productos")
    public void queElUsuarioEstaEnPaginaProductos() {
        System.out.println("📦 Usuario en página de productos.");
        // Si la URL requiere navegación adicional, agregar aquí
    }

    @Dado("que el carrito tiene el producto {string}")
    public void queElCarritoTieneElProducto(String nombreProducto) {
        carritoBusiness.seleccionarProducto(nombreProducto);
        carritoBusiness.agregarProductoAlCarrito();
    }

    @Cuando("el usuario selecciona el producto {string}")
    public void elUsuarioSeleccionaElProducto(String nombreProducto) {
        carritoBusiness.seleccionarProducto(nombreProducto);
    }

    @Y("el usuario hace clic en {string}")
    public void elUsuarioHaceClicEn(String nombreBoton) {
        if (nombreBoton.equalsIgnoreCase("Agregar al Carrito")) {
            carritoBusiness.agregarProductoAlCarrito();
        }
    }

    @Cuando("el usuario elimina el producto {string} del carrito")
    public void elUsuarioEliminaElProducto(String nombreProducto) {
        carritoBusiness.eliminarProductoDelCarrito(nombreProducto);
    }

    @Entonces("el carrito debe mostrar {string} producto")
    public void elCarritoDebeMostrarUnProducto(String cantidad) {
        carritoBusiness.validarCantidadProductosEnCarrito(cantidad);
    }

    @Entonces("el carrito debe mostrar {string} productos")
    public void elCarritoDebeMostrarProductos(String cantidad) {
        carritoBusiness.validarCantidadProductosEnCarrito(cantidad);
    }

    @Y("el producto {string} debe aparecer en el carrito")
    public void elProductoDebeAparecerEnElCarrito(String nombreProducto) {
        carritoBusiness.validarProductoEnCarrito(nombreProducto);
    }

    @Entonces("el carrito debe estar vacío")
    public void elCarritoDebeEstarVacio() {
        carritoBusiness.validarCarritoVacio();
    }
}
