package com.automation.business.carrito;

import com.automation.business.login.LoginBusiness;
import com.automation.pages.carrito.CarritoPage;
import org.testng.Assert;

/**
 * CarritoBusiness - Lógica de negocio del módulo Carrito de Compras.
 * Orquesta las acciones entre el Page Object y los Step Definitions.
 * 
 * Arquitectura: Business Logic Layer (Carrito Module)
 * Responsabilidad: Flujos de negocio y validaciones
 */
public class CarritoBusiness {

    private CarritoPage page;

    // ──────────────────────────────────────────────
    //  Constructor
    // ──────────────────────────────────────────────

    public CarritoBusiness() {
        this.page = new CarritoPage();
    }

    // ──────────────────────────────────────────────
    //  Flujo: Login previo (requerido por Background)
    // ──────────────────────────────────────────────

    public void autenticarseEnElSistema() {
        LoginBusiness login = new LoginBusiness();
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
