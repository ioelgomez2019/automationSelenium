# AutomationCorebank 🏦
### Framework de Automatización BDD - Patrón POM + Cucumber + Selenium + Maven

---

## 📁 Arquitectura del Proyecto

```
AutomationCorebank/
│
├── Scenarios/                          # Archivos Gherkin .feature
│   ├── inicioSession/
│   │   └── inicioSession.feature
│   └── agregarCarrito/
│       └── agregarCarrito.feature
│
├── Script/
│   ├── Config/
│   │   ├── environment.config          # ⚙️ Configuración de ambientes
│   │   └── ConfigManager.java          # Lector del config
│   │
│   ├── Componentes/                    # Componentes generales del proyecto
│   │   ├── DriverManager.java          # Manejo del WebDriver
│   │   ├── BasePage.java               # Clase base para Page Objects
│   │   └── ScreenshotUtil.java         # Captura de pantallas
│   │
│   ├── InicioSession/
│   │   ├── Components/
│   │   │   └── LoginComponent.java     # Componentes UI del módulo
│   │   └── Feature/
│   │       ├── InicioSesionPage.java   # Page Object (elementos mapeados)
│   │       ├── InicioSesion.java       # Lógica de negocio
│   │       └── InicioSessionStep.java  # Step Definitions Cucumber
│   │
│   └── AgregarCarrito/
│       ├── Components/
│       │   └── CarritoComponent.java   # Componentes UI del módulo
│       └── Feature/
│           ├── AgregarCarritoPage.java # Page Object (elementos mapeados)
│           ├── AgregarCarrito.java     # Lógica de negocio
│           └── AgregarCarritoStep.java # Step Definitions Cucumber
│
├── src/
│   └── test/
│       ├── java/runners/
│       │   └── TestRunner.java         # 🚀 Runner principal
│       └── resources/
│           ├── extent.properties       # Configuración de reportes
│           └── cucumber.properties     # Configuración de Cucumber
│
└── pom.xml                             # Dependencias Maven
```

---

## 🛠️ Requisitos Previos

- **Java JDK 11+**
- **Maven 3.8+**
- **IntelliJ IDEA** (recomendado)
- **Google Chrome** instalado

---

## 🚀 Cómo Importar en IntelliJ IDEA

1. Abrir IntelliJ IDEA
2. `File` → `Open` → seleccionar la carpeta `AutomationCorebank`
3. IntelliJ detectará el `pom.xml` automáticamente → clic en **"Add as Maven Project"**
4. Esperar que se descarguen las dependencias (ícono 🔄 esquina inferior derecha)

---

## ▶️ Cómo Ejecutar las Pruebas

### Opción 1: Desde IntelliJ IDEA
```
Clic derecho en TestRunner.java → Run 'TestRunner'
```

### Opción 2: Desde Terminal / Maven
```bash
# Ejecutar TODOS los tests
mvn test

# Ejecutar solo InicioSession
mvn test -Dcucumber.filter.tags="@InicioSession"

# Ejecutar solo AgregarCarrito
mvn test -Dcucumber.filter.tags="@AgregarCarrito"

# Ejecutar un escenario específico
mvn test -Dcucumber.filter.tags="@LoginExitoso"

# Ejecutar en modo headless
mvn test -Dbrowser.headless=true
```

---

## ⚙️ Configurar el Ambiente

Editar `Script/Config/environment.config`:

```properties
# Cambiar el ambiente activo
environment=qa       # opciones: dev | qa | staging | prod

# Cambiar el navegador
browser=chrome       # opciones: chrome | firefox | edge

# Modo sin ventana (CI/CD)
browser.headless=false
```

---

## 📊 Reportes

Después de ejecutar, los reportes se generan en:

| Tipo | Ruta |
|------|------|
| HTML nativo Cucumber | `target/reports/cucumber-report.html` |
| Extent Report (bonito) | `target/reports/ExtentReport.html` |
| JSON (para CI/CD) | `target/reports/cucumber-report.json` |
| JUnit XML | `target/reports/cucumber-junit.xml` |
| Screenshots de fallos | `target/screenshots/` |

---

## 📦 Dependencias Principales

| Librería | Versión | Propósito |
|----------|---------|-----------|
| selenium-java | 4.15.0 | Automatización web |
| cucumber-java | 7.14.0 | BDD framework |
| cucumber-junit | 7.14.0 | Runner Cucumber |
| webdrivermanager | 5.6.3 | Gestión automática de drivers |
| extentreports | 5.1.1 | Reportes HTML profesionales |

---

## 🏗️ Capas del Framework

```
Feature File (.feature)
       ↓
Step Definitions (*Step.java)   ← conecta Gherkin con Java
       ↓
Lógica de Negocio (*java)       ← orquesta el flujo
       ↓
Page Object (*Page.java)        ← interactúa con la UI
       ↓
BasePage + DriverManager        ← manejo del WebDriver
       ↓
ConfigManager                   ← configuración de ambiente
```
# automationSelenium
