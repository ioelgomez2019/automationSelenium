package com.automation.script.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigManager - Administrador de configuraciones del proyecto.
 * Lee el archivo environment.config y expone las propiedades.
 * Patrón: Singleton
 */
public class ConfigManager {

    private static ConfigManager instance;
    private Properties properties;

    // Ruta relativa al archivo de configuración
    private static final String CONFIG_PATH = "Script/Config/environment.config";

    // ──────────────────────────────────────────────
    //  Singleton: privado para forzar uso de getInstance()
    // ──────────────────────────────────────────────
    private ConfigManager() {
        properties = new Properties();
        cargarPropiedades();
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    // ──────────────────────────────────────────────
    //  Carga del archivo .config
    // ──────────────────────────────────────────────
    private void cargarPropiedades() {
        try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
            properties.load(fis);
            System.out.println("✅ Configuración cargada: " + CONFIG_PATH);
        } catch (IOException e) {
            throw new RuntimeException("❌ No se pudo cargar el archivo de configuración: " + CONFIG_PATH, e);
        }
    }

    // ──────────────────────────────────────────────
    //  Métodos de acceso a propiedades
    // ──────────────────────────────────────────────

    public String getPropiedad(String clave) {
        String valor = properties.getProperty(clave);
        if (valor == null) {
            throw new RuntimeException("❌ Propiedad no encontrada en config: " + clave);
        }
        return valor.trim();
    }

    public String getAmbiente() {
        return getPropiedad("environment");
    }

    public String getUrl() {
        String ambiente = getAmbiente();
        return getPropiedad("url." + ambiente);
    }

    public String getUsuario() {
        String ambiente = getAmbiente();
        return getPropiedad("credentials." + ambiente + ".user");
    }

    public String getContrasena() {
        String ambiente = getAmbiente();
        return getPropiedad("credentials." + ambiente + ".password");
    }

    public String getBrowser() {
        return getPropiedad("browser");
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(getPropiedad("browser.headless"));
    }

    public int getTimeoutImplicito() {
        return Integer.parseInt(getPropiedad("timeout.implicit"));
    }

    public int getTimeoutExplicito() {
        return Integer.parseInt(getPropiedad("timeout.explicit"));
    }

    public boolean isScreenshotOnFailure() {
        return Boolean.parseBoolean(getPropiedad("reports.screenshots.on.failure"));
    }

    public String getScreenshotsPath() {
        return getPropiedad("reports.screenshots.path");
    }
}
