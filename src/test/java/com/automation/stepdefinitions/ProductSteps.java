package com.automation.stepdefinitions;

import com.automation.negocio.ProductBusiness;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductSteps {

    private ProductBusiness productBusiness;
    private String selectedProductName;

    private ProductBusiness getProductBusiness() {
        if (productBusiness == null) {
            productBusiness = new ProductBusiness();
        }
        return productBusiness;
    }

    @When("el usuario selecciona el producto {string}")
    public void elUsuarioSeleccionaElProducto(String productName) {
        selectedProductName = productName;
    }

    @When("el usuario hace clic en agregar al carrito")
    public void elUsuarioHaceClicEnAgregarAlCarrito() {
        if (selectedProductName == null || selectedProductName.isEmpty()) {
            throw new IllegalStateException("Debe seleccionar un producto antes de agregar al carrito.");
        }

        getProductBusiness().addProductToCart(selectedProductName);
        selectedProductName = null;
    }

    @When("el usuario agrega la lista de productos {string}")
    public void elUsuarioAgregaLaListaDeProductos(String productos) {
        if (productos == null || productos.trim().isEmpty()) {
            throw new IllegalArgumentException("La lista de productos no puede estar vacia.");
        }

        String[] lista = productos.split(";");
        int agregados = 0;
        for (String producto : lista) {
            String nombre = producto.trim();
            if (!nombre.isEmpty()) {
                getProductBusiness().addProductToCart(nombre);
                agregados++;
            }
        }

        if (agregados == 0) {
            throw new IllegalArgumentException("No se encontraron productos validos en la lista: " + productos);
        }
    }

    @Then("el carrito debe mostrar {int} producto")
    public void elCarritoDebeMostrarUnProducto(int cantidad) {
        getProductBusiness().validateCartCount(cantidad);
    }

    @Then("el carrito debe mostrar {int} productos")
    public void elCarritoDebeMostrarProductos(int cantidad) {
        getProductBusiness().validateCartCount(cantidad);
    }
}
