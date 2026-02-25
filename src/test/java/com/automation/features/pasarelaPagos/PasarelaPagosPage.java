package com.automation.features.pasarelaPagos;

import com.automation.core.BasePage;
import org.openqa.selenium.By;

public class PasarelaPagosPage extends BasePage {

    private static final By CART_LINK = By.className("shopping_cart_link");
    private static final By CHECKOUT_BUTTON = By.id("checkout");
    private static final By FIRST_NAME = By.id("first-name");
    private static final By LAST_NAME = By.id("last-name");
    private static final By POSTAL_CODE = By.id("postal-code");
    private static final By CONTINUE_BUTTON = By.id("continue");
    private static final By SUMMARY_TOTAL_LABEL = By.className("summary_total_label");
    private static final By FINISH_BUTTON = By.id("finish");
    private static final By COMPLETE_HEADER = By.className("complete-header");

    public void openCart() {
        click(CART_LINK);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void clickCheckout() {
        click(CHECKOUT_BUTTON);
    }

    public void fillCheckoutData(String firstName, String lastName, String postalCode) {
        type(FIRST_NAME, firstName);
        type(LAST_NAME, lastName);
        type(POSTAL_CODE, postalCode);
    }

    public void clickContinue() {
        click(CONTINUE_BUTTON);
    }

    public String getSummaryTotalText() {
        return getText(SUMMARY_TOTAL_LABEL);
    }

    public void clickFinish() {
        click(FINISH_BUTTON);
    }

    public String getCompleteHeaderText() {
        return getText(COMPLETE_HEADER);
    }
}
