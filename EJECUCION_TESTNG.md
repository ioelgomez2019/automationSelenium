# Guía de Ejecución con TestNG

## Migración Completada ✅

El proyecto ha sido migrado de **JUnit** a **TestNG** exitosamente. Ahora puedes ejecutar pruebas de manera más flexible utilizando tags y múltiples runners.

---

## 📋 Runners Disponibles

### 1. **TestRunner** (Todas las pruebas)
Ejecuta todos los escenarios disponibles en el proyecto.

```bash
mvn test
# O específicamente:
mvn test -Dtest=TestRunner
mvn test -DsuiteXmlFile=testng.xml
```

### 2. **LoginRunner** (Solo pruebas de Login - Tag: @login)
Ejecuta únicamente las pruebas de inicio de sesión.

```bash
mvn test -Dtest=LoginRunner
mvn test -DsuiteXmlFile=testng-login.xml
```

### 3. **CarritoRunner** (Solo pruebas de Carrito)
Ejecuta únicamente las pruebas del carrito de compras.

```bash
mvn test -Dtest=CarritoRunner
mvn test -DsuiteXmlFile=testng-carrito.xml
```

### 4. **E2ERunner** (Flujo End-to-End)
Ejecuta una secuencia completa: Login exitoso + Agregar al carrito.

```bash
mvn test -Dtest=E2ERunner
mvn test -DsuiteXmlFile=testng-e2e.xml
```

---

## 🏷️ Ejecución por Tags

### Usar tags desde línea de comando con cualquier runner:

#### Solo pruebas de login:
```bash
mvn test -Dcucumber.filter.tags="@login"
```

#### Solo pruebas exitosas de login:
```bash
mvn test -Dcucumber.filter.tags="@login and @LoginExitoso"
```

#### Login o Carrito (E2E):
```bash
mvn test -Dcucumber.filter.tags="@login or @AgregarCarrito"
```

#### Excluir pruebas WIP (Work In Progress):
```bash
mvn test -Dcucumber.filter.tags="not @WIP"
```

#### Combinaciones complejas:
```bash
mvn test -Dcucumber.filter.tags="(@login or @AgregarCarrito) and not @WIP"
```

---

## 📊 Tags Disponibles en el Proyecto

### Tags de Feature:
- `@login` - Todas las pruebas de inicio de sesión
- `@InicioSession` - Feature de inicio de sesión
- `@AgregarCarrito` - Feature de agregar al carrito
- `@SauceDemo` - Pruebas específicas de SauceDemo

### Tags de Escenario:
- `@LoginExitoso` - Login exitoso
- `@LoginFallido` - Login fallido
- `@LoginCamposVacios` - Validación de campos vacíos
- `@AgregarProductoExitoso` - Agregar producto exitosamente
- `@AgregarMultiplesProductos` - Agregar múltiples productos
- `@EliminarProductoCarrito` - Eliminar productos del carrito

---

## 🔗 Encadenar Ejecuciones E2E

### Opción 1: Usar E2ERunner
```bash
mvn test -Dtest=E2ERunner
```

### Opción 2: Usar tags combinados
```bash
mvn test -Dcucumber.filter.tags="@LoginExitoso or @AgregarCarrito"
```

### Opción 3: Ejecutar runners en secuencia
```bash
mvn test -Dtest=LoginRunner && mvn test -Dtest=CarritoRunner
```

---

## 🚀 Ejecutar desde IntelliJ IDEA

### Opción 1: Ejecutar un Runner específico
1. Abre el archivo del runner (ej: `LoginRunner.java`)
2. Clic derecho → **Run 'LoginRunner'**
3. Los resultados aparecerán en la ventana de ejecución

### Opción 2: Ejecutar un archivo testng.xml
1. Abre el archivo XML (ej: `testng-e2e.xml`)
2. Clic derecho → **Run 'testng-e2e.xml'**

### Opción 3: Ejecutar un Scenario específico
1. Abre el archivo `.feature`
2. Clic en el ícono ▶️ verde al lado del escenario
3. IntelliJ ejecutará solo ese escenario

---

## 📁 Reportes Generados

Después de cada ejecución, los reportes se generan en:

```
target/reports/
├── cucumber-report.html      # Reporte HTML nativo
├── cucumber-report.json      # JSON para CI/CD
├── cucumber-junit.xml        # XML para Jenkins
├── rerun.txt                 # Escenarios fallidos
└── extent-report/            # ExtentReports (si configurado)
```

### Reportes específicos por runner:
- **LoginRunner**: `target/reports/login-report.html`
- **CarritoRunner**: `target/reports/carrito-report.html`
- **E2ERunner**: `target/reports/e2e-report.html`

---

## ⚙️ Configuración Adicional

### Ejecución Paralela

Para habilitar ejecución paralela de escenarios, edita el runner correspondiente:

```java
@Override
@DataProvider(parallel = true)  // Cambiar a true
public Object[][] scenarios() {
    return super.scenarios();
}
```

### Modificar Tags en el Código

Edita el archivo del runner y descomenta/modifica la línea de tags:

```java
@CucumberOptions(
    // ...
    tags = "@login",  // Descomentar y modificar según necesidad
    // ...
)
```

---

## 📝 Ejemplos de Uso Común

### 1. Desarrollo: Ejecutar solo login
```bash
mvn test -Dtest=LoginRunner
```

### 2. Regression: Ejecutar todo
```bash
mvn test
```

### 3. Pre-producción: Flujo E2E
```bash
mvn test -Dtest=E2ERunner
```

### 4. Debugging: Un escenario específico
```bash
mvn test -Dcucumber.filter.tags="@LoginExitoso"
```

### 5. CI/CD Pipeline: Sin pruebas WIP
```bash
mvn clean test -Dcucumber.filter.tags="not @WIP"
```

---

## 🆘 Troubleshooting

### Error: "No tests were executed"
- Verifica que los tags en el runner coincidan con los del feature file
- Asegúrate de que el path de `features` y `glue` sean correctos

### Error: "Could not find testng.xml"
- Verifica que el archivo `testng.xml` esté en la raíz del proyecto
- O especifica el path completo: `-DsuiteXmlFile=./testng.xml`

### Los reportes no se generan
- Verifica los plugins en `@CucumberOptions`
- Asegúrate de que la carpeta `target/reports` tenga permisos de escritura

---

## 📚 Recursos Adicionales

- [Documentación TestNG](https://testng.org/doc/)
- [Documentación Cucumber](https://cucumber.io/docs/cucumber/)
- [Expresiones de Tags de Cucumber](https://cucumber.io/docs/cucumber/api/#tags)

---

**¡La migración a TestNG está completa! 🎉**

Ahora puedes ejecutar tus pruebas de manera más flexible y encadenar ejecuciones E2E fácilmente.
