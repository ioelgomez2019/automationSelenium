package com.automation.business;

import com.automation.pages.ProductsPage;
import org.testng.Assert;

/**
 * ProductBusiness - Business logic for product operations.
 * Handles product selection, cart operations, and assertions.
 */
public class ProductBusiness {

    private ProductsPage productsPage;

    public ProductBusiness() {
        this.productsPage = new ProductsPage();
    }

    /**
     * Verify user is on products page
     */
    public void verifyOnProductsPage() {
        productsPage.waitForElementToBeVisible(org.openqa.selenium.By.className("inventory_container"));
    }

    /**
     * Add first product to cart
     */
    public void addFirstProductToCart() {
        productsPage.addFirstProductToCart();
    }

    /**
     * Add specific product to cart by name
     */
    public void addProductToCart(String productName) {
        productsPage.addProductToCart(productName);
    }

    /**
     * Get current cart item count
     */
    public int getCartCount() {
        return productsPage.getCartItemCount();
    }

    /**
     * Validate product count in cart
     */
    public void validateCartCount(int expectedCount) {
        int actualCount = getCartCount();
        Assert.assertEquals(actualCount, expectedCount, 
            "Cart should contain " + expectedCount + " item(s)");
    }

    /**
     * Open shopping cart
     */
    public void openCart() {
        productsPage.openCart();
    }

    /**
     * Proceed to checkout
     */
    public void proceedToCheckout() {
        productsPage.checkout();
    }

    /**
     * Continue shopping
     */
    public void continueShopping() {
        productsPage.continueShopping();
    }

    /**
     * Validate cart is empty
     */
    public void validateCartEmpty() {
        int cartCount = getCartCount();
        Assert.assertEquals(cartCount, 0, "Cart should be empty but has " + cartCount + " item(s)");
    }

    // Backward compatibility
    public void seleccionarProducto(String productName) {
        addProductToCart(productName);
    }

    public void agregarProductoAlCarrito() {
        addFirstProductToCart();
    }

    public void validarCantidadProductosEnCarrito(String cantidad) {
        int count = Integer.parseInt(cantidad);
        validateCartCount(count);
    }
}
