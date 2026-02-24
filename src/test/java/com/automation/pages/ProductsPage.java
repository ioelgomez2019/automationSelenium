package com.automation.pages;

import com.automation.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * ProductsPage - Page Object for Saucedemo products/inventory page.
 */
public class ProductsPage extends BasePage {

    private static final By PRODUCTS_LIST = By.className("inventory_item");
    private static final By PRODUCT_NAME = By.className("inventory_item_name");
    private static final By ADD_TO_CART_BTN = By.xpath(".//button[contains(text(), 'Add to cart')]");
    private static final By CART_BADGE = By.className("shopping_cart_badge");

    public void addProductToCart(String productName) {
        List<WebElement> products = driver.findElements(PRODUCTS_LIST);

        for (WebElement product : products) {
            String name = product.findElement(PRODUCT_NAME).getText();
            if (name.equalsIgnoreCase(productName)) {
                product.findElement(ADD_TO_CART_BTN).click();
                return;
            }
        }

        throw new IllegalArgumentException("Product not found on page: " + productName);
    }

    public int getCartItemCount() {
        try {
            String count = getText(CART_BADGE);
            return Integer.parseInt(count);
        } catch (Exception e) {
            return 0;
        }
    }
}