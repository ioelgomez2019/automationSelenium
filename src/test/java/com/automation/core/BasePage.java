package com.automation.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * BasePage - Base class with common WebDriver operations.
 * Inherited by all Page Objects for reusable element interactions.
 */
public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final long TIMEOUT = 10;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    // ──────────────────────────────────────────────
    //  Click Operations
    // ──────────────────────────────────────────────

    public void click(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    // ──────────────────────────────────────────────
    //  Input Operations
    // ──────────────────────────────────────────────

    public void type(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    public void type(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    // ──────────────────────────────────────────────
    //  Read Operations
    // ──────────────────────────────────────────────

    public String getText(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator)).getText();
    }

    public String getText(WebElement element) {
        return element.getText();
    }

    public String getAttribute(By locator, String attribute) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator)).getAttribute(attribute);
    }

    // ──────────────────────────────────────────────
    //  Wait/Visibility Operations
    // ──────────────────────────────────────────────

    public void waitForElement(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean isElementDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    // ──────────────────────────────────────────────
    //  Dropdown Operations
    // ──────────────────────────────────────────────

    public void selectDropdownByValue(By locator, String value) {
        WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        new Select(dropdown).selectByValue(value);
    }

    public void selectDropdownByText(By locator, String text) {
        WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        new Select(dropdown).selectByVisibleText(text);
    }

    // ──────────────────────────────────────────────
    //  Scroll Operations
    // ──────────────────────────────────────────────

    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // ──────────────────────────────────────────────
    //  Navigation
    // ──────────────────────────────────────────────

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // ──────────────────────────────────────────────
    //  Utility
    // ──────────────────────────────────────────────

    public void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
