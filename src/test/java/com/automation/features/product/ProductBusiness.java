package com.automation.features.product;

import com.automation.features.product.ProductsPage;
import org.testng.Assert;

public class ProductBusiness {

    private final ProductsPage productsPage;

    public ProductBusiness() {
        this.productsPage = new ProductsPage();
    }

    public void addProductToCart(String productName) {
        productsPage.addProductToCart(productName);
    }

    public void validateCartCount(int expectedCount) {
        int actualCount = productsPage.getCartItemCount();
        Assert.assertEquals(actualCount, expectedCount,
            "Cart should contain " + expectedCount + " item(s), but found " + actualCount);
    }
}
