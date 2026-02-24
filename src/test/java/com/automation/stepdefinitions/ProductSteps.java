package com.automation.stepdefinitions;

import com.automation.business.ProductBusiness;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

/**
 * ProductSteps - Step definitions for product/shopping operations.
 * Inherits login functionality from CommonSteps via reusable LoginBusiness.
 */
public class ProductSteps extends CommonSteps {

    private ProductBusiness productBusiness;

    @Given("user is logged in and on products page")
    public void userIsLoggedInAndOnProductsPage() {
        loginBusiness.loginAsStandardUser();
        productBusiness = new ProductBusiness();
        productBusiness.verifyOnProductsPage();
    }

    @When("user adds first product to cart")
    public void userAddsFirstProductToCart() {
        if (productBusiness == null) {
            productBusiness = new ProductBusiness();
        }
        productBusiness.addFirstProductToCart();
    }

    @When("user adds product {string} to cart")
    public void userAddsProductToCart(String productName) {
        if (productBusiness == null) {
            productBusiness = new ProductBusiness();
        }
        productBusiness.addProductToCart(productName);
    }

    @Then("cart should have {int} item")
    public void cartShouldHaveItem(int expectedCount) {
        if (productBusiness == null) {
            productBusiness = new ProductBusiness();
        }
        productBusiness.validateCartCount(expectedCount);
    }

    @Then("cart should have {int} items")
    public void cartShouldHaveItems(int expectedCount) {
        if (productBusiness == null) {
            productBusiness = new ProductBusiness();
        }
        productBusiness.validateCartCount(expectedCount);
    }

    @When("user opens cart")
    public void userOpensCart() {
        if (productBusiness == null) {
            productBusiness = new ProductBusiness();
        }
        productBusiness.openCart();
    }

    @When("user proceeds to checkout")
    public void userProceedsToCheckout() {
        if (productBusiness == null) {
            productBusiness = new ProductBusiness();
        }
        productBusiness.proceedToCheckout();
    }

    @When("user continues shopping")
    public void userContinuesShopping() {
        if (productBusiness == null) {
            productBusiness = new ProductBusiness();
        }
        productBusiness.continueShopping();
    }

    // Spanish compatibility
    @Given("que el usuario está autenticado en el sistema")
    public void queElUsuarioEstaAutenticado() {
        userIsLoggedInAndOnProductsPage();
    }

    @When("el usuario selecciona el producto {string}")
    public void elUsuarioSeleccionaElProducto(String productName) {
        userAddsProductToCart(productName);
    }

    @And("el usuario hace clic en agregar al carrito")
    public void elUsuarioHaceClicEnAgregarAlCarrito() {
        userAddsFirstProductToCart();
    }

    @Then("el carrito debe mostrar {int} producto")
    public void elCarritoDebeMostrarUnProducto(int cantidad) {
        cartShouldHaveItem(cantidad);
    }

    @Then("el carrito debe mostrar {int} productos")
    public void elCarritoDebeMostrarProductos(int cantidad) {
        cartShouldHaveItems(cantidad);
    }
}
