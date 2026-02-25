package com.automation.features.login;

import com.automation.config.ConfigManager;
import com.automation.features.login.LoginPage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginBusiness {

    private final LoginPage loginPage;
    private final ConfigManager config;

    public LoginBusiness() {
        this.loginPage = new LoginPage();
        this.config = ConfigManager.getInstance();
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public void abrirPaginaLogin() {
        loginPage.navigateTo(config.getBaseUrl());
    }

    public void ingresarEmail(String email) {
        loginPage.enterUsername(email);
    }

    public void ingresarContrasena(String password) {
        loginPage.enterPassword(password);
    }

    public void clickBotonIngresar() {
        loginPage.clickLoginButton();
    }

    public void validarLoginExitoso() {
        loginPage.waitForElementToBeVisible(By.className("inventory_container"));
        Assert.assertTrue(loginPage.isProductsPageDisplayed(),
            "Products page should be displayed after successful login");
    }

    public void validarMensajeError(String expectedError) {
        Assert.assertTrue(loginPage.isErrorDisplayed(),
            "Error message should be displayed");

        String actualError = loginPage.getErrorMessage();
        Assert.assertTrue(actualError.contains(expectedError),
            "Error message should contain: " + expectedError + ". Actual: " + actualError);
    }
}
