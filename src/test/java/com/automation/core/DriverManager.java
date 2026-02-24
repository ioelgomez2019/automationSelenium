package com.automation.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * DriverManager - ThreadLocal WebDriver management for thread-safe execution.
 * Supports parallel test execution with isolated driver instances per thread.
 */
public class DriverManager {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    /**
     * Initialize ChromeDriver (default)
     */
    public static void startDriver() {
        if (DRIVER.get() == null) {
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            DRIVER.set(driver);
        }
    }

    /**
     * Initialize specified browser
     */
    public static void startDriver(String browser) {
        if (DRIVER.get() == null) {
            if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                DRIVER.set(new FirefoxDriver());
            } else {
                WebDriverManager.chromedriver().setup();
                DRIVER.set(new ChromeDriver());
            }
            DRIVER.get().manage().window().maximize();
        }
    }

    /**
     * Get current thread's WebDriver instance
     */
    public static WebDriver getDriver() {
        if (DRIVER.get() == null) {
            startDriver();
        }
        return DRIVER.get();
    }

    /**
     * Close and release WebDriver
     */
    public static void closeDriver() {
        if (DRIVER.get() != null) {
            DRIVER.get().quit();
            DRIVER.remove();
        }
    }

    /**
     * Restart driver (close and reinitialize)
     */
    public static void restartDriver() {
        closeDriver();
        startDriver();
    }

    // Backward compatibility
    public static void iniciarDriver() {
        startDriver();
    }

    public static void cerrarDriver() {
        closeDriver();
    }
}
