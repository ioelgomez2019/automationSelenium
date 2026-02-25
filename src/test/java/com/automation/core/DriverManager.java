package com.automation.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

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
            WebDriver driver = buildChromeDriver();
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
                DRIVER.set(buildChromeDriver());
            }
            DRIVER.get().manage().window().maximize();
        }
    }

    private static WebDriver buildChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        prefs.put("autofill.profile_enabled", false);
        prefs.put("autofill.credit_card_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-notifications");

        return new ChromeDriver(options);
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
