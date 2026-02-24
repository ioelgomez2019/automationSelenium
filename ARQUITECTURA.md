# 🏗️ Arquitectura del Proyecto - AutomationCorebank

## 📋 Estructura Maven Estándar (Actualizada)

Este proyecto sigue las **convenciones estándar de Maven** para evitar errores de compilación.

```
AutomationCorebank/
│
├── 📁 Scenarios/                              # Archivos .feature (Gherkin)
│   ├── inicioSession/
│   │   └── inicioSession.feature              # Feature de Login (@login)
│   └── agregarCarrito/
│       └── agregarCarrito.feature             # Feature de Carrito
│
├── 📁 src/
│   │
│   ├── 📁 main/                               # CÓDIGO PRINCIPAL
│   │   ├── java/com/automation/
│   │   │   └── script/
│   │   │       │
│   │   │       ├── 🔧 componentes/            # Componentes Generales
│   │   │       │   ├── BasePage.java          # Clase base para Pages
│   │   │       │   ├── DriverManager.java     # Gestión de WebDriver
│   │   │       │   └── ScreenshotUtil.java    # Capturas de pantalla
│   │   │       │
│   │   │       ├── ⚙️ config/                 # Configuración
│   │   │       │   └── ConfigManager.java     # Lector de config
│   │   │       │
│   │   │       ├── 📄 InicioSession/          # Módulo Login
│   │   │       │   ├── Components/
│   │   │       │   │   └── LoginComponent.java
│   │   │       │   └── Feature/
│   │   │       │       ├── InicioSesionPage.java   # Page Object
│   │   │       │       └── InicioSesion.java       # Lógica de negocio
│   │   │       │
│   │   │       └── 🛒 AgregarCarrito/         # Módulo Carrito
│   │   │           ├── Components/
│   │   │           │   └── CarritoComponent.java
│   │   │           └── Feature/
│   │   │               ├── AgregarCarritoPage.java # Page Object
│   │   │               └── AgregarCarrito.java     # Lógica de negocio
│   │   │
│   │   └── resources/
│   │       └── config/
│   │           └── environment.config         # Configuración de ambientes
│   │
│   └── 📁 test/                               # CÓDIGO DE PRUEBAS
│       ├── java/
│       │   └── com/automation/
│       │       │
│       │       ├── 🧪 stepdefinitions/        # Step Definitions (Cucumber)
│       │       │   ├── InicioSessionStep.java # Steps de Login
│       │       │   └── AgregarCarritoStep.java # Steps de Carrito
│       │       │
│       │       └── 🚀 runners/                # Runners TestNG
│       │           ├── TestRunner.java        # Todas las pruebas
│       │           ├── LoginRunner.java       # Solo @login
│       │           ├── CarritoRunner.java     # Solo carrito
│       │           └── E2ERunner.java         # E2E (Login + Carrito)
│       │
│       └── resources/
│           ├── cucumber.properties            # Config Cucumber
│           └── extent.properties              # Config Reportes
│
├── 📁 target/                                 # Archivos compilados y reportes
│
├── 📄 pom.xml                                 # Dependencias Maven
├── 📄 testng.xml                              # Suite TestNG principal
├── 📄 testng-login.xml                        # Suite Login
├── 📄 testng-carrito.xml                      # Suite Carrito
├── 📄 testng-e2e.xml                          # Suite E2E
└── 📄 ejecutar-pruebas-testng.bat             # Script de ejecución
```

---

## 🎯 Separación de Responsabilidades

### 📦 src/main/java - Código Principal

Contiene todo el código reutilizable y lógica de negocio:

| Paquete | Contenido | Propósito |
|---------|-----------|-----------|
| `componentes/` | BasePage, DriverManager, ScreenshotUtil | Componentes base reutilizables |
| `config/` | ConfigManager | Gestión de configuración |
| `script.InicioSession/` | Pages y lógica de Login | Módulo de autenticación |
| `script.AgregarCarrito/` | Pages y lógica de Carrito | Módulo de carrito de compras |

### 🧪 src/test/java - Código de Pruebas

Contiene el código específico de testing:

| Paquete | Contenido | Propósito |
|---------|-----------|-----------|
| `stepdefinitions/` | Step Definitions | Implementación de pasos Gherkin |
| `runners/` | Test Runners | Configuración de ejecución TestNG |

---

## 📁 Flujo de Archivos

```
┌─────────────────┐
│ Scenarios/      │  ← Archivos .feature (Gherkin)
│ *.feature       │
└────────┬────────┘
         │
         │ usa
         ▼
┌─────────────────────────┐
│ stepdefinitions/        │  ← Step Definitions (src/test/java)
│ *Step.java              │
└────────┬────────────────┘
         │
         │ llama
         ▼
┌─────────────────────────┐
│ script/*/Feature/       │  ← Lógica de negocio (src/main/java)
│ *.java                  │
└────────┬────────────────┘
         │
         │ usa
         ▼
┌─────────────────────────┐
│ script/*/Feature/       │  ← Page Objects (src/main/java)
│ *Page.java              │
└────────┬────────────────┘
         │
         │ extiende
         ▼
┌─────────────────────────┐
│ componentes/            │  ← Componentes base (src/main/java)
│ BasePage.java           │
└─────────────────────────┘
```

---

## ⚙️ Convenciones Maven

### ✅ Ubicaciones CORRECTAS

```bash
src/main/java/          # Código fuente principal (Pages, Components, Business Logic)
src/main/resources/     # Archivos de configuración
src/test/java/          # Código de pruebas (Steps, Runners)
src/test/resources/     # Recursos de prueba
```

### ❌ Ubicaciones INCORRECTAS (causan errores)

```bash
Script/                 # ❌ FUERA de la estructura Maven
cualquier_carpeta/      # ❌ Archivos .java fuera de src/
```

**Error típico:**
```
"files located outside of the module source root, so it won't be compiled"
```

**Solución:** Todos los archivos `.java` deben estar dentro de `src/main/java` o `src/test/java`.

---

## 🚀 Compilación y Ejecución

### Compilar el proyecto:

```bash
mvn clean compile
```

### Ejecutar pruebas:

```bash
# Todas las pruebas
mvn test

# Solo login (@login)
mvn test -Dtest=LoginRunner

# Solo carrito
mvn test -Dtest=CarritoRunner

# Flujo E2E
mvn test -Dtest=E2ERunner

# Por tags
mvn test -Dcucumber.filter.tags="@login"
```

---

## 📝 Paquetes y Namespaces

### Código Principal (src/main/java):

```
package com.automation.script.componentes;      // Componentes base
package com.automation.script.config;           // Configuración
package com.automation.script.InicioSession.Feature;      // Login
package com.automation.script.AgregarCarrito.Feature;     // Carrito
```

### Código de Pruebas (src/test/java):

```
package com.automation.stepdefinitions;         // Step Definitions
package runners;                                // Test Runners
```

---

## 🔧 Configuración del POM

El `pom.xml` está configurado con:

```
<sourceDirectory>src/main/java</sourceDirectory>
<testSourceDirectory>src/test/java</testSourceDirectory>
```

Esto indica a Maven dónde buscar código fuente y código de pruebas.

---

## 📊 Reportes Generados

Después de ejecutar pruebas:

```
target/
├── reports/
│   ├── cucumber-report.html       # Reporte HTML
│   ├── cucumber-report.json       # JSON para CI/CD
│   ├── cucumber-junit.xml         # XML para Jenkins
│   ├── login-report.html          # Reporte específico Login
│   ├── carrito-report.html        # Reporte específico Carrito
│   └── e2e-report.html            # Reporte E2E
└── surefire-reports/              # Reportes TestNG
```

---

## 🛠️ Migración Completada

### Cambios Realizados:

✅ Migración de JUnit a TestNG  
✅ Implementación de tags (`@login`, `@AgregarCarrito`)  
✅ Reorganización según convenciones Maven  
✅ Step Definitions movidos a `src/test/java`  
✅ Runners actualizados con nuevos paquetes  
✅ Eliminación de carpeta `Script/` obsoleta  

### Para Limpiar Archivos Obsoletos:

Ejecuta el script:

```bash
limpiar-arquitectura.bat
```

Este script eliminará la carpeta `Script/` que causaba el error de compilación.

---

## 📚 Buenas Prácticas Aplicadas

1. ✅ **Separación de responsabilidades**: Código principal vs código de pruebas
2. ✅ **Convenciones Maven**: Uso de `src/main/java` y `src/test/java`
3. ✅ **Page Object Model**: Separación de Pages, Lógica y Steps
4. ✅ **TestNG + Cucumber**: Motor de ejecución robusto con BDD
5. ✅ **Ejecución por tags**: Flexibilidad para ejecutar subsets de pruebas
6. ✅ **Reportes múltiples**: HTML, JSON, XML para diferentes propósitos

---

**¡Arquitectura optimizada y lista para usar!** 🎉
