# 🚀 Instrucciones para Automatizar Saucedemo

## 📋 Resumen
Este proyecto de automatización ha sido configurado para ejecutar pruebas de login en **https://www.saucedemo.com/**

## 🔑 Credenciales Configuradas

### Usuarios Disponibles en Saucedemo:
- ✅ `standard_user` - Usuario estándar (configurado por defecto)
- 🔒 `locked_out_user` - Usuario bloqueado
- ⚠️ `problem_user` - Usuario con problemas
- 🐌 `performance_glitch_user` - Usuario con retrasos de rendimiento
- ❌ `error_user` - Usuario con errores
- 👁️ `visual_user` - Usuario visual

### Contraseña (para todos los usuarios):
```
secret_sauce
```

## ⚙️ Configuración Actual

El archivo de configuración se encuentra en:
```
Script/Componentes/Config/environment.config
```

Configuración activa:
```properties
environment=saucedemo
url.saucedemo=https://www.saucedemo.com/
credentials.saucedemo.user=standard_user
credentials.saucedemo.password=secret_sauce
browser=chrome
browser.headless=false
```

## 🧪 Escenarios de Prueba Disponibles

### 1. Login Exitoso con standard_user ✅
- **Tag**: `@LoginExitoso @SauceDemo`
- **Usuario**: `standard_user`
- **Resultado esperado**: Acceso exitoso al inventario

### 2. Login Exitoso con performance_glitch_user ✅
- **Tag**: `@LoginExitoso @SauceDemo`
- **Usuario**: `performance_glitch_user`
- **Resultado esperado**: Acceso exitoso (puede ser lento)

### 3. Login Fallido con locked_out_user ❌
- **Tag**: `@LoginFallido @SauceDemo`
- **Usuario**: `locked_out_user`
- **Resultado esperado**: Mensaje de error "locked out"

### 4. Login Fallido con credenciales incorrectas ❌
- **Tag**: `@LoginFallido @SauceDemo`
- **Usuario**: `usuario_invalido`
- **Resultado esperado**: Mensaje de error de credenciales inválidas

### 5. Login con campos vacíos ⚠️
- **Tag**: `@LoginCamposVacios @SauceDemo`
- **Resultado esperado**: Mensaje "Username is required"

## 🏃 Cómo Ejecutar las Pruebas

### Opción 1: Ejecutar TODAS las pruebas de Saucedemo
```bash
mvn clean test
```

### Opción 2: Ejecutar solo pruebas con tag específico
```bash
# Solo pruebas de login exitoso
mvn test -Dcucumber.filter.tags="@LoginExitoso"

# Solo pruebas de Saucedemo
mvn test -Dcucumber.filter.tags="@SauceDemo"

# Solo pruebas de login fallido
mvn test -Dcucumber.filter.tags="@LoginFallido"

# Solo el módulo de inicio de sesión
mvn test -Dcucumber.filter.tags="@InicioSession"
```

### Opción 3: Ejecutar desde IDE (IntelliJ/Eclipse)
1. Abrir el archivo: `src/test/java/runners/TestRunner.java`
2. Clic derecho en la clase
3. Seleccionar "Run 'TestRunner'"

### Opción 4: Ejecutar un escenario específico desde el .feature
1. Abrir el archivo: `Scenarios/inicioSession/inicioSession.feature`
2. Clic derecho en el escenario deseado
3. Seleccionar "Run Scenario"

## 📊 Reportes Generados

Después de ejecutar las pruebas, encontrarás los reportes en:

```
target/reports/
├── cucumber-report.html      ← Reporte HTML principal
├── cucumber-report.json      ← JSON para CI/CD
├── cucumber-junit.xml        ← XML para Jenkins
└── screenshots/              ← Capturas de pantalla de errores
```

### Ver el Reporte HTML:
```
target/reports/cucumber-report.html
```
Abre este archivo en tu navegador para ver un reporte detallado con:
- ✅ Escenarios ejecutados
- ⏱️ Tiempo de ejecución
- 📸 Screenshots de fallos
- 📝 Logs detallados

## 🔄 Cambiar de Usuario para Probar

### Método 1: Editar el archivo de configuración
Abre `Script/Componentes/Config/environment.config` y cambia:
```properties
credentials.saucedemo.user=problem_user
```

### Método 2: Editar el archivo .feature
Abre `Scenarios/inicioSession/inicioSession.feature` y modifica el usuario en el paso:
```gherkin
When el usuario ingresa el correo "error_user"
```

## 🛠️ Tecnologías Utilizadas

- **Selenium WebDriver 4.15.0** - Automatización de navegador
- **Cucumber 7.14.0** - Framework BDD (Gherkin)
- **JUnit 4.13.2** - Framework de testing
- **WebDriverManager 5.6.3** - Gestión automática de drivers
- **Maven** - Gestión de dependencias
- **Page Object Model (POM)** - Patrón de diseño

## 📁 Estructura del Proyecto

```
AutomationCorebank/
├── Scenarios/inicioSession/
│   └── inicioSession.feature          ← Casos de prueba en Gherkin
├── src/main/java/com/automation/script/
│   ├── InicioSession/Feature/
│   │   ├── InicioSesionPage.java      ← Page Object (localizadores)
│   │   └── InicioSesion.java          ← Lógica de negocio
│   ├── componentes/
│   │   ├── BasePage.java              ← Métodos base reutilizables
│   │   └── DriverManager.java         ← Gestión del WebDriver
│   └── config/
│       └── ConfigManager.java         ← Lectura de configuración
├── src/test/java/
│   ├── com/automation/script/InicioSession/Feature/
│   │   └── InicioSessionStep.java     ← Step Definitions (Cucumber)
│   └── runners/
│       └── TestRunner.java            ← Ejecutor principal
├── Script/Componentes/Config/
│   └── environment.config             ← Configuración de ambientes
└── pom.xml                            ← Dependencias Maven
```

## 🔍 Elementos Automatizados de Saucedemo

| Elemento | Localizador | Descripción |
|----------|-------------|-------------|
| Campo Usuario | `id="user-name"` | Input para nombre de usuario |
| Campo Contraseña | `id="password"` | Input para contraseña |
| Botón Login | `id="login-button"` | Botón para iniciar sesión |
| Mensaje Error | `css="h3[data-test='error']"` | Mensaje de error de login |
| Lista Inventario | `css=".inventory_list"` | Lista de productos (post-login) |
| Logo App | `css=".app_logo"` | Logo "Swag Labs" |
| Menú Hamburguesa | `css=".bm-burger-button"` | Botón del menú lateral |

## ✅ Checklist de Verificación

Antes de ejecutar las pruebas, asegúrate de:

- [ ] Tener Java 11+ instalado (`java -version`)
- [ ] Tener Maven instalado (`mvn -version`)
- [ ] Tener conexión a Internet (para descargar drivers)
- [ ] Tener Chrome instalado (o cambiar browser en config)
- [ ] Haber ejecutado `mvn clean install` al menos una vez

## 🐛 Solución de Problemas

### Error: "Driver not found"
```bash
mvn clean install
```

### Error: "Connection refused" o "Timeout"
- Verifica tu conexión a Internet
- Verifica que https://www.saucedemo.com/ esté accesible

### Error: "Element not found"
- La página de Saucedemo puede haber cambiado
- Verifica los localizadores en `InicioSesionPage.java`

### Las pruebas se ejecutan pero no veo el navegador
- Cambia `browser.headless=false` en `environment.config`

## 📞 Contacto y Soporte

Para preguntas o problemas, contacta al equipo de automatización de Banco Pichincha.

---
**Última actualización**: Febrero 2026
**Versión**: 1.0
**Automatización de**: https://www.saucedemo.com/
