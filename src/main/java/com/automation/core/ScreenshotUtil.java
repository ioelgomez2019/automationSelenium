package com.automation.core;

import com.automation.config.ConfigManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ScreenshotUtil - Utilidad para captura de pantallas.
 * Usado principalmente en casos de fallo de escenarios.
 * 
 * Arquitectura: Core Component (Utility)
 */
public class ScreenshotUtil {

    private static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";

    public static String capturarPantalla(String nombreEscenario) {
        try {
            String timestamp = new SimpleDateFormat(TIMESTAMP_FORMAT).format(new Date());
            String rutaBase  = ConfigManager.getInstance().getScreenshotsPath();
            String nombreArchivo = nombreEscenario.replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp + ".png";
            String rutaCompleta = rutaBase + File.separator + nombreArchivo;

            File screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            File destino = new File(rutaCompleta);
            destino.getParentFile().mkdirs();
            FileUtils.copyFile(screenshot, destino);

            System.out.println("📸 Screenshot guardado: " + rutaCompleta);
            return rutaCompleta;

        } catch (IOException e) {
            System.err.println("❌ Error al capturar screenshot: " + e.getMessage());
            return null;
        }
    }

    public static byte[] capturarPantallaBytes() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
