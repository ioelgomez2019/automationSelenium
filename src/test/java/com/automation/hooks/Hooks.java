package com.automation.hooks;

import com.automation.core.DriverManager;
import com.automation.core.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * Hooks - Setup and teardown for all scenarios.
 */
public class Hooks {

    @Before
    public void setup(Scenario scenario) {
        System.out.println("\nScenario: " + scenario.getName());
        System.out.println("Tags: " + scenario.getSourceTagNames());
        DriverManager.iniciarDriver();
    }

    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ScreenshotUtil.capturarPantallaBytes();
            if (screenshot != null) {
                scenario.attach(screenshot, "image/png", "Failure Screenshot");
            }
        }

        try {
            DriverManager.cerrarDriver();
        } catch (Exception e) {
            System.out.println("Warning closing driver: " + e.getMessage());
        }
    }
}
