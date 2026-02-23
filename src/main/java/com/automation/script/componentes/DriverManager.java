package com.automation.script.componentes;

import com.automation.script.config.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

/**
 * DriverManager - Componente general del proyecto.
 * Gestiona el ciclo de vida del WebDriver (crear, obtener, destruir).
 * Usa ThreadLocal para soporte multi-hilo.
 */
public class DriverManager {

    // ThreadLocal para ejecución paralela
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    // ──────────────────────────────────────────────
    //  Inicialización del Driver
    // ──────────────────────────────────────────────

    public static void iniciarDriver() {
        ConfigManager config = ConfigManager.getInstance();
        String browser   = config.getBrowser().toLowerCase();
        boolean headless = config.isHeadless();

        WebDriver driver;

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            default:
                throw new RuntimeException("❌ Navegador no soportado: " + browser);
        }

        // Configurar timeouts
        driver.manage().timeouts().implicitlyWait(
            Duration.ofSeconds(config.getTimeoutImplicito())
        );
        driver.manage().timeouts().pageLoadTimeout(
            Duration.ofSeconds(30)
        );

        // Maximizar ventana
        driver.manage().window().maximize();

        driverThreadLocal.set(driver);
        System.out.println("✅ Driver iniciado: " + browser.toUpperCase());
    }

    // ──────────────────────────────────────────────
    //  Obtener el Driver activo
    // ──────────────────────────────────────────────

    public static WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver == null) {
            throw new RuntimeException("❌ El driver no ha sido inicializado. Llama a iniciarDriver() primero.");
        }
        return driver;
    }

    // ──────────────────────────────────────────────
    //  Cerrar el Driver
    // ──────────────────────────────────────────────

    public static void cerrarDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
            System.out.println("✅ Driver cerrado correctamente.");
        }
    }

    // ──────────────────────────────────────────────
    //  Navegar a URL
    // ──────────────────────────────────────────────

    public static void navegarA(String url) {
        getDriver().get(url);
        System.out.println("🌐 Navegando a: " + url);
    }

    public static void navegarAlAmbiente() {
        navegarA(ConfigManager.getInstance().getUrl());
    }
}
