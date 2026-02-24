package com.automation.stepdefinitions;

import com.automation.core.DriverManager;
import com.automation.core.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * Hooks - Setup and teardown for all scenarios.
 * NOTE: This class contains ONLY hooks (@Before, @After), no Step Definitions.
 * This allows other Step Definition classes to use composition without inheritance issues.
 */
public class Hooks {

    @Before
    public void setup(Scenario scenario) {
        System.out.println("\n▶ Scenario: " + scenario.getName());
        System.out.println("  Tags: " + scenario.getSourceTagNames());
        DriverManager.iniciarDriver();
    }

    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("✗ FAILED: " + scenario.getName());
            byte[] screenshot = ScreenshotUtil.capturarPantallaBytes();
            if (screenshot != null) {
                scenario.attach(screenshot, "image/png", "Failure Screenshot");
            }
        } else {
            System.out.println("✓ PASSED: " + scenario.getName());
        }
        
        // Always close driver and clean up
        try {
            DriverManager.cerrarDriver();
        } catch (Exception e) {
            System.out.println("⚠ Warning: Error closing driver: " + e.getMessage());
        }
    }
}
