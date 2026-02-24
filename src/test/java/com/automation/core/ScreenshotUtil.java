package com.automation.core;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ScreenshotUtil - Screenshot capture utility for failure documentation.
 */
public class ScreenshotUtil {

    private static final String SCREENSHOT_DIR = "target/screenshots";

    /**
     * Take screenshot and save to file
     */
    public static String takeScreenshot(String screenshotName) {
        WebDriver driver = DriverManager.getDriver();
        try {
            File screenshotDir = new File(SCREENSHOT_DIR);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            String timestamp = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date());
            String filename = SCREENSHOT_DIR + "/" + screenshotName + "_" + timestamp + ".png";

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshot.toPath(), new File(filename).toPath(), StandardCopyOption.REPLACE_EXISTING);
            return filename;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Take screenshot as byte array (for Cucumber attach)
     */
    public static byte[] takeScreenshotAsBytes() {
        WebDriver driver = DriverManager.getDriver();
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            return null;
        }
    }

    // Backward compatibility
    public static byte[] capturarPantallaBytes() {
        return takeScreenshotAsBytes();
    }

    public static String capturarPantalla(String nombre) {
        return takeScreenshot(nombre);
    }
}
