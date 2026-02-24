package com.automation.pages;

import com.automation.core.BasePage;
import org.openqa.selenium.By;
import java.util.List;
import org.openqa.selenium.WebElement;

/**
 * ProductsPage - Page Object for Saucedemo products/inventory page.
 * Contains only element locators for product operations and shopping cart.
 */
public class ProductsPage extends BasePage {

    private static final By PRODUCTS_LIST = By.className("inventory_item");
    private static final By PRODUCT_NAME = By.className("inventory_item_name");
    private static final By ADD_TO_CART_BTN = By.xpath("//button[contains(text(), 'Add to cart')]");
    private static final By CART_BADGE = By.className("shopping_cart_badge");
    private static final By CART_LINK = By.className("shopping_cart_link");
    private static final By CART_ITEMS = By.className("cart_item");
    private static final By CHECKOUT_BTN = By.id("checkout");
    private static final By CONTINUE_SHOPPING = By.id("continue-shopping");
    private static final By FINISH_BTN = By.id("finish");

    public int getProductCount() {
        return driver.findElements(PRODUCTS_LIST).size();
    }

    public void addFirstProductToCart() {
        WebElement firstAddButton = driver.findElement(ADD_TO_CART_BTN);
        click(firstAddButton);
    }

    public void addProductToCart(String productName) {
        List<WebElement> products = driver.findElements(PRODUCTS_LIST);
        for (WebElement product : products) {
            String name = product.findElement(PRODUCT_NAME).getText();
            if (name.equalsIgnoreCase(productName)) {
                product.findElement(ADD_TO_CART_BTN).click();
                break;
            }
        }
    }

    public int getCartItemCount() {
        try {
            String count = getText(CART_BADGE);
            return Integer.parseInt(count);
        } catch (Exception e) {
            return 0;
        }
    }

    public void openCart() {
        click(CART_LINK);
    }

    public void checkout() {
        click(CHECKOUT_BTN);
    }

    public void continueShopping() {
        click(CONTINUE_SHOPPING);
    }

    public void finishCheckout() {
        click(FINISH_BTN);
    }

    public int getCartItemsDisplayedCount() {
        return driver.findElements(CART_ITEMS).size();
    }
}
