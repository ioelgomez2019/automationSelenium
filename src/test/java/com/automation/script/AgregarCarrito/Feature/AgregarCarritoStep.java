package com.automation.script.AgregarCarrito.Feature;

import com.automation.script.componentes.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.es.*;

/**
 * AgregarCarritoStep - Step Definitions para el módulo de Carrito de Compras.
 * Conecta los pasos Gherkin (.feature) con la lógica de negocio (AgregarCarrito.java).
 */
public class AgregarCarritoStep {

    private AgregarCarrito agregarCarrito;

    // ──────────────────────────────────────────────
    //  Hooks
    // ──────────────────────────────────────────────

    @Before("@AgregarCarrito")
    public void antesDelEscenario(Scenario scenario) {
        System.out.println("\n🚀 Iniciando escenario: " + scenario.getName());
        DriverManager.iniciarDriver();
        agregarCarrito = new AgregarCarrito();
    }

    @After("@AgregarCarrito")
    public void despuesDelEscenario(Scenario scenario) {
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

    @Dado("que el usuario está autenticado en el sistema")
    public void queElUsuarioEstaAutenticado() {
        agregarCarrito.autenticarseEnElSistema();
    }

    @Y("que el usuario se encuentra en la página de productos")
    public void queElUsuarioEstaEnPaginaProductos() {
        System.out.println("📦 Usuario en página de productos.");
        // Si la URL requiere navegación adicional, agregar aquí
    }

    @Dado("que el carrito tiene el producto {string}")
    public void queElCarritoTieneElProducto(String nombreProducto) {
        agregarCarrito.seleccionarProducto(nombreProducto);
        agregarCarrito.agregarProductoAlCarrito();
    }

    @Cuando("el usuario selecciona el producto {string}")
    public void elUsuarioSeleccionaElProducto(String nombreProducto) {
        agregarCarrito.seleccionarProducto(nombreProducto);
    }

    @Y("el usuario hace clic en {string}")
    public void elUsuarioHaceClicEn(String nombreBoton) {
        if (nombreBoton.equalsIgnoreCase("Agregar al Carrito")) {
            agregarCarrito.agregarProductoAlCarrito();
        }
    }

    @Cuando("el usuario elimina el producto {string} del carrito")
    public void elUsuarioEliminaElProducto(String nombreProducto) {
        agregarCarrito.eliminarProductoDelCarrito(nombreProducto);
    }

    @Entonces("el carrito debe mostrar {string} producto")
    public void elCarritoDebeMostrarUnProducto(String cantidad) {
        agregarCarrito.validarCantidadProductosEnCarrito(cantidad);
    }

    @Entonces("el carrito debe mostrar {string} productos")
    public void elCarritoDebeMostrarProductos(String cantidad) {
        agregarCarrito.validarCantidadProductosEnCarrito(cantidad);
    }

    @Y("el producto {string} debe aparecer en el carrito")
    public void elProductoDebeAparecerEnElCarrito(String nombreProducto) {
        agregarCarrito.validarProductoEnCarrito(nombreProducto);
    }

    @Entonces("el carrito debe estar vacío")
    public void elCarritoDebeEstarVacio() {
        agregarCarrito.validarCarritoVacio();
    }
}
