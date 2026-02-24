package com.automation.config;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * ConfigManager - Singleton configuration manager for environment properties.
 */
public class ConfigManager {

    private static ConfigManager instance;
    private Properties properties;

    private ConfigManager() {
        properties = new Properties();
        loadProperties();
    }

    public static synchronized ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    private void loadProperties() {
        try {
            String configPath = "src/test/resources/config/environment.config";
            FileInputStream fis = new FileInputStream(configPath);
            properties.load(fis);
            fis.close();
        } catch (Exception e) {
            System.out.println("Warning: Config file not found, using defaults");
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key, "");
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public String getBaseUrl() {
        return getProperty("base.url", "https://www.saucedemo.com");
    }

    public String getBrowser() {
        return getProperty("browser", "chrome");
    }

    public long getImplicitWait() {
        return Long.parseLong(getProperty("implicit.wait", "10"));
    }

    public long getExplicitWait() {
        return Long.parseLong(getProperty("explicit.wait", "10"));
    }
}
