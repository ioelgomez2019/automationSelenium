# AutomationCorebank

Framework BDD con Selenium + Cucumber + TestNG + Maven.

## Arquitectura actual

```text
AutomationCorebank/
|-- pom.xml
|-- Scenarios/
|   |-- inicioSession/
|   |   `-- inicioSession.feature
|   `-- agregarCarrito/
|       `-- agregarCarrito.feature
|-- src/test/java/
|   |-- com/automation/
|   |   |-- core/
|   |   |   |-- DriverManager.java
|   |   |   |-- BasePage.java
|   |   |   `-- ScreenshotUtil.java
|   |   |-- config/
|   |   |   `-- ConfigManager.java
|   |   |-- pages/
|   |   |   |-- LoginPage.java
|   |   |   `-- ProductsPage.java
|   |   |-- negocio/
|   |   |   |-- LoginBusiness.java
|   |   |   `-- ProductBusiness.java
|   |   |-- stepdefinitions/
|   |   |   |-- LoginSteps.java
|   |   |   `-- ProductSteps.java
|   |   `-- hooks/
|   |       `-- Hooks.java
|   `-- runners/
|       |-- LoginRunner.java
|       |-- ProductRunner.java
|       |-- E2ERunner.java
|       `-- TestRunner.java
`-- src/test/resources/
    |-- config/environment.config
    |-- cucumber.properties
    `-- extent.properties
```

## Flujo de capas

```text
Feature (.feature)
  -> StepDefinitions
  -> Negocio
  -> Pages
  -> Core (Driver/Base/Screenshot)
```

## Regla importante para Examples

Cuando uses placeholders como `<user>`, `<password>`, `<producto>`:

1. Deben ir en `Scenario Outline`.
2. Los valores se definen en `Examples`.
3. No usar placeholders en `Background` si esperas sustitucion por fila.

## Ejecucion de pruebas

### Ejecutar todo

```bash
mvn clean test
```

### Ejecutar por runner

```bash
mvn test -Dtest=LoginRunner
mvn test -Dtest=ProductRunner
mvn test -Dtest=E2ERunner
mvn test -Dtest=TestRunner
```

### Ejecutar por tags (PowerShell)

```bash
mvn test "-Dcucumber.filter.tags=@login"
mvn test "-Dcucumber.filter.tags=@AgregarCarrito"
mvn test "-Dcucumber.filter.tags=@login or @AgregarCarrito"
```

### Validar mapeo de pasos sin abrir navegador (dry-run)

```bash
mvn -Dtest=LoginRunner "-Dcucumber.execution.dry-run=true" test
mvn -Dtest=ProductRunner "-Dcucumber.execution.dry-run=true" test
```

### Solo compilar pruebas

```bash
mvn clean -DskipTests test-compile
```

## Reportes

Los runners generan reportes en `target/reports/`:

- `login-report.html`, `login-report.json`, `login-junit.xml`
- `product-report.html`, `product-report.json`, `product-junit.xml`
- `e2e-report.html`, `e2e-report.json`, `e2e-junit.xml`
- `full-report.html`, `full-report.json`, `full-junit.xml`

## Configuracion

Archivo: `src/test/resources/config/environment.config`

Claves principales:

- `base.url`
- `browser`
- `implicit.wait`
- `explicit.wait`

## Requisitos

- Java 11+
- Maven 3.8+
- Chrome instalado