package com.automation.business;

import com.automation.config.ConfigManager;
import com.automation.pages.LoginPage;
import org.testng.Assert;

/**
 * LoginBusiness - REUSABLE login business logic for all workflows.
 * Used by Login tests, E2E flows, Cart flows, and any other flow requiring authentication.
 */
public class LoginBusiness {

    protected LoginPage loginPage;
    protected ConfigManager config;

    public LoginBusiness() {
        this.loginPage = new LoginPage();
        this.config = ConfigManager.getInstance();
    }

    /**
     * Get LoginPage instance for direct element interactions
     */
    public LoginPage getLoginPage() {
        return loginPage;
    }

    /**
     * Open login page
     */
    public void openLoginPage() {
        String baseUrl = config.getBaseUrl();
        loginPage.navigateTo(baseUrl);
    }

    /**
     * Perform complete login with credentials
     */
    public void login(String username, String password) {
        openLoginPage();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }

    /**
     * Perform standard login with default credentials (standard_user / secret_sauce)
     */
    public void loginAsStandardUser() {
        login("standard_user", "secret_sauce");
    }

    /**
     * Perform login with locked user
     */
    public void loginAsLockedUser() {
        login("locked_out_user", "secret_sauce");
    }

    /**
     * Perform login with performance test user
     */
    public void loginAsPerformanceUser() {
        login("performance_glitch_user", "secret_sauce");
    }

    /**
     * Validate successful login (products page displayed)
     */
    public void validateLoginSuccess() {
        loginPage.waitForElementToBeVisible(org.openqa.selenium.By.className("inventory_container"));
        Assert.assertTrue(loginPage.isProductsPageDisplayed(), 
            "Products page should be displayed after successful login");
    }

    /**
     * Validate login failure with error message
     */
    public void validateLoginFailure(String expectedErrorText) {
        Assert.assertTrue(loginPage.isErrorDisplayed(), 
            "Error message should be displayed");
        String actualError = loginPage.getErrorMessage();
        Assert.assertTrue(actualError.contains(expectedErrorText), 
            "Error message should contain: " + expectedErrorText);
    }

    /**
     * Validate login page is shown
     */
    public void validateLoginPageIsDisplayed() {
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), 
            "Login page should be displayed");
    }

    // Backward compatibility (Spanish names)
    public void abrirPaginaLogin() {
        openLoginPage();
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
        validateLoginSuccess();
    }

    public void validarMensajeError(String expectedError) {
        validateLoginFailure(expectedError);
    }
}
