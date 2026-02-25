package com.automation.features.pasarelaPagos;

import com.automation.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PasarelaPagosPage extends BasePage {

    private static final By CART_LINK = By.className("shopping_cart_link");
    private static final By CHECKOUT_BUTTON = By.id("checkout");
    private static final By FIRST_NAME = By.id("first-name");
    private static final By LAST_NAME = By.id("last-name");
    private static final By POSTAL_CODE = By.id("postal-code");
    private static final By CONTINUE_BUTTON = By.id("continue");
    private static final By CHECKOUT_ERROR = By.cssSelector("h3[data-test='error']");
    private static final By SUMMARY_TOTAL_LABEL = By.className("summary_total_label");
    private static final By FINISH_BUTTON = By.id("finish");
    private static final By COMPLETE_HEADER = By.className("complete-header");
    private static final long CHECKOUT_TIMEOUT_SECONDS = 20;

    private WebDriverWait checkoutWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(CHECKOUT_TIMEOUT_SECONDS));
    }

    public void openCart() {
        click(CART_LINK);
        checkoutWait().until(ExpectedConditions.urlContains("cart.html"));
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void clickCheckout() {
        click(CHECKOUT_BUTTON);
        checkoutWait().until(ExpectedConditions.urlContains("checkout-step-one.html"));
    }

    public void fillCheckoutData(String firstName, String lastName, String postalCode) {
        checkoutWait().until(ExpectedConditions.urlContains("checkout-step-one.html"));

        fillFieldReliably(FIRST_NAME, firstName);
        fillFieldReliably(LAST_NAME, lastName);
        fillFieldReliably(POSTAL_CODE, postalCode);
    }

    public void clickContinue() {
        click(CONTINUE_BUTTON);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("checkout-step-two.html"));
            return;
        } catch (TimeoutException ignored) {
            // Fallback click for flaky cases where regular click doesn't trigger navigation.
        }

        WebElement continueButton = driver.findElement(CONTINUE_BUTTON);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButton);

        try {
            checkoutWait().until(ExpectedConditions.urlContains("checkout-step-two.html"));
        } catch (TimeoutException ex) {
            String currentUrl = driver.getCurrentUrl();
            String errorText = getCheckoutErrorText();
            String detail = "Could not continue to checkout-step-two. Current url: " + currentUrl;
            if (!errorText.isEmpty()) {
                detail += ". Checkout error: " + errorText;
            }
            detail += ". Field values: first-name='" + readFieldValue(FIRST_NAME)
                + "', last-name='" + readFieldValue(LAST_NAME)
                + "', postal-code='" + readFieldValue(POSTAL_CODE) + "'";
            throw new TimeoutException(detail, ex);
        }
    }

    public String getSummaryTotalText() {
        return checkoutWait().until(ExpectedConditions.visibilityOfElementLocated(SUMMARY_TOTAL_LABEL)).getText();
    }

    public void clickFinish() {
        WebElement finishButton = checkoutWait().until(ExpectedConditions.elementToBeClickable(FINISH_BUTTON));
        finishButton.click();

        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("checkout-complete.html"));
            return;
        } catch (TimeoutException ignored) {
            // Fallback click for flaky cases where regular click doesn't trigger navigation.
        }

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", finishButton);

        try {
            checkoutWait().until(ExpectedConditions.urlContains("checkout-complete.html"));
        } catch (TimeoutException ex) {
            throw new TimeoutException(
                "Could not finish checkout. Current url: " + driver.getCurrentUrl(),
                ex
            );
        }
    }

    public String getCompleteHeaderText() {
        return checkoutWait().until(ExpectedConditions.visibilityOfElementLocated(COMPLETE_HEADER)).getText();
    }

    private void fillFieldReliably(By locator, String value) {
        for (int attempt = 1; attempt <= 3; attempt++) {
            WebElement field = checkoutWait().until(ExpectedConditions.elementToBeClickable(locator));
            field.click();
            field.clear();
            field.sendKeys(value);

            if (hasExpectedValue(locator, value)) {
                return;
            }

            setValueWithJavascript(locator, value);
            if (hasExpectedValue(locator, value)) {
                return;
            }
        }

        String currentValue = readFieldValue(locator);
        throw new TimeoutException("Could not populate checkout field " + locator + ". Current value: " + currentValue);
    }

    private boolean hasExpectedValue(By locator, String expectedValue) {
        String currentValue = readFieldValue(locator);
        return expectedValue.equals(currentValue);
    }

    private void setValueWithJavascript(By locator, String value) {
        WebElement field = checkoutWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript(
            "const input = arguments[0];" +
                "const value = arguments[1];" +
                "const nativeSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                "input.focus();" +
                "nativeSetter.call(input, value);" +
                "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));" +
                "input.blur();",
            field, value
        );
    }

    private String readFieldValue(By locator) {
        WebElement field = driver.findElement(locator);
        Object jsValue = ((JavascriptExecutor) driver).executeScript("return arguments[0].value;", field);
        return jsValue == null ? "" : jsValue.toString();
    }

    private String getCheckoutErrorText() {
        List<WebElement> errors = driver.findElements(CHECKOUT_ERROR);
        if (errors.isEmpty()) {
            return "";
        }
        return errors.get(0).getText();
    }
}
