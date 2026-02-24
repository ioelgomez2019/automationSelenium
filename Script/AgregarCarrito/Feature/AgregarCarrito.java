package com.automation.script.AgregarCarrito.Feature;

import com.automation.script.InicioSession.Feature.InicioSesion;
import org.testng.Assert;

/**
 * AgregarCarrito - Lógica de negocio del módulo Carrito de Compras.
 * Orquesta las acciones entre el Page Object y los Step Definitions.
 */
public class AgregarCarrito {

    private AgregarCarritoPage page;

    // ──────────────────────────────────────────────
    //  Constructor
    // ──────────────────────────────────────────────

    public AgregarCarrito() {
        this.page = new AgregarCarritoPage();
    }

    // ──────────────────────────────────────────────
    //  Flujo: Login previo (requerido por Background)
    // ──────────────────────────────────────────────

    public void autenticarseEnElSistema() {
        InicioSesion login = new InicioSesion();
        login.abrirPaginaLogin();
        login.realizarLoginConCredencialesDelAmbiente();
        login.validarLoginExitoso();
        System.out.println("✅ Autenticación completada para módulo carrito.");
    }

    // ──────────────────────────────────────────────
    //  Flujo: Interacción con Productos
    // ──────────────────────────────────────────────

    public void seleccionarProducto(String nombreProducto) {
        page.seleccionarProductoPorNombre(nombreProducto);
    }

    public void agregarProductoAlCarrito() {
        page.clickAgregarAlCarrito();
    }

    public void eliminarProductoDelCarrito(String nombreProducto) {
        page.eliminarProductoDelCarrito(nombreProducto);
    }

    // ──────────────────────────────────────────────
    //  Validaciones de Negocio
    // ──────────────────────────────────────────────

    public void validarCantidadProductosEnCarrito(String cantidadEsperada) {
        String cantidadReal = page.obtenerContadorCarrito();
        Assert.assertEquals(
            cantidadReal, cantidadEsperada,
            "❌ La cantidad en el carrito no coincide. Esperado: " + cantidadEsperada + " | Real: " + cantidadReal
        );
        System.out.println("✅ Carrito con " + cantidadReal + " producto(s).");
    }

    public void validarProductoEnCarrito(String nombreProducto) {
        Assert.assertTrue(
            page.productoEstaEnCarrito(nombreProducto),
            "❌ El producto '" + nombreProducto + "' no se encontró en el carrito."
        );
        System.out.println("✅ Producto encontrado en carrito: " + nombreProducto);
    }

    public void validarCarritoVacio() {
        Assert.assertTrue(
            page.carritoEstaVacio(),
            "❌ El carrito no está vacío."
        );
        System.out.println("✅ Carrito vacío confirmado.");
    }
}
