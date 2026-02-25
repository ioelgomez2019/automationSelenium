package com.automation.features.login;

import com.automation.core.BasePage;
import org.openqa.selenium.By;

/**
 * LoginPage - Page Object for Saucedemo login page.
 * Contains only element locators - NO LOGIC.
 */
public class LoginPage extends BasePage {

    private static final By USERNAME = By.id("user-name");
    private static final By PASSWORD = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By ERROR_MESSAGE = By.cssSelector("h3[data-test='error']");
    private static final By PRODUCTS_CONTAINER = By.className("inventory_container");

    public void enterUsername(String username) {
        type(USERNAME, username);
    }

    public void enterPassword(String password) {
        type(PASSWORD, password);
    }

    public void clickLoginButton() {
        click(LOGIN_BUTTON);
    }

    public String getErrorMessage() {
        return getText(ERROR_MESSAGE);
    }

    public boolean isLoginPageDisplayed() {
        return isElementDisplayed(LOGIN_BUTTON);
    }

    public boolean isProductsPageDisplayed() {
        return isElementDisplayed(PRODUCTS_CONTAINER);
    }

    public boolean isErrorDisplayed() {
        return isElementDisplayed(ERROR_MESSAGE);
    }
}
