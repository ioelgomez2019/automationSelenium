# Jenkins CI/CD — Guía completa de instalación y configuración

> Proyecto: `automationSelenium` — Selenium + Cucumber + TestNG + Maven  
> OS: Windows | Jenkins puerto: `8081`

---

## Requisitos previos

| Herramienta | Versión | Descarga |
|---|---|---|
| Java JDK | 17 LTS | https://adoptium.net |
| Maven | 3.9+ | https://maven.apache.org/download.cgi |
| Chrome | última | https://www.google.com/chrome |
| Git | última | https://git-scm.com |

### Verificar instalaciones

```powershell
java -version
mvn -version
git --version
```

---

## 1. Instalación de Jenkins

1. Descargar el instalador `.msi` desde: https://www.jenkins.io/download/ → **Windows**
2. Ejecutar el `.msi` y seguir el asistente:
   - Destination folder: `C:\Program Files\Jenkins`
   - Run service as: **LocalSystem**
   - Port: `8081` (o el que prefieras)
   - Java path: ruta de tu JDK instalado
3. Jenkins se instala como servicio de Windows y arranca automáticamente

### Acceder por primera vez

```
http://localhost:8081
```

Obtener contraseña inicial:

```powershell
Get-Content "C:\Program Files\Jenkins\secrets\initialAdminPassword"
```

4. Pegar la contraseña → **Install suggested plugins** → Crear usuario admin → **Start using Jenkins**

---

## 2. Plugins necesarios

Ir a **Manage Jenkins → Plugins → Available plugins** e instalar:

| Plugin | Uso |
|---|---|
| `GitHub` | Recibir webhooks de GitHub |
| `HTML Publisher` | Publicar reportes HTML de Cucumber |
| `Maven Integration` | Soporte Maven en pipelines |
| `Email Extension Plugin` | Notificaciones por email |

Reiniciar Jenkins después de instalar.

---

## 3. Configurar JDK y Maven en Jenkins

Ir a **Manage Jenkins → Tools**

### Obtener paths en PowerShell

```powershell
# Path del JDK
Get-Command java | Select-Object -ExpandProperty Source
# Ejemplo resultado: C:\Program Files\Eclipse Adoptium\jdk-17.x.x\bin\java.exe
# JAVA_HOME = todo menos \bin\java.exe

# Path de Maven
Get-Command mvn | Select-Object -ExpandProperty Source
# Ejemplo resultado: C:\apache-maven-3.9.x\bin\mvn.cmd
# MAVEN_HOME = todo menos \bin\mvn.cmd
```

### JDK
- Name: `JDK17`
- Desmarcar "Install automatically"
- JAVA_HOME: `C:\Program Files\Eclipse Adoptium\jdk-17.x.x`

### Maven
- Name: `Maven3`
- Desmarcar "Install automatically"
- MAVEN_HOME: `C:\apache-maven-3.9.x`

---

## 4. Crear el job Pipeline

1. Dashboard → **Nueva tarea**
2. Nombre: `automationSelenium`
3. Tipo: **Pipeline** → OK

### Configuración del job

**Sección General:**
- ✅ GitHub project
- Project url: `https://github.com/ioelgomez2019/automationSelenium`

**Sección Build Triggers:**
- ✅ GitHub hook trigger for GITScm polling

**Sección Pipeline:**
- Definition: `Pipeline script from SCM`
- SCM: `Git`
- Repository URL: `https://github.com/ioelgomez2019/automationSelenium.git`
- Branch Specifier: `*/main`
- Script Path: `Jenkinsfile`

---

## 5. Jenkinsfile

Crear el archivo `Jenkinsfile` en la raíz del proyecto (mismo nivel que `pom.xml`):

```groovy
pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk   'JDK17'
    }

    triggers {
        githubPush()
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/ioelgomez2019/automationSelenium.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean -DskipTests test-compile'
            }
        }

        stage('Tests') {
            parallel {

                stage('Login') {
                    steps {
                        bat 'mvn test -Dtest=LoginRunner'
                    }
                    post {
                        always {
                            publishHTML(target: [
                                allowMissing:          false,
                                alwaysLinkToLastBuild: true,
                                keepAll:               true,
                                reportDir:             'target/reports',
                                reportFiles:           'login-report.html',
                                reportName:            'Login Report'
                            ])
                        }
                    }
                }

                stage('Product') {
                    steps {
                        bat 'mvn test -Dtest=ProductRunner'
                    }
                    post {
                        always {
                            publishHTML(target: [
                                allowMissing:          false,
                                alwaysLinkToLastBuild: true,
                                keepAll:               true,
                                reportDir:             'target/reports',
                                reportFiles:           'product-report.html',
                                reportName:            'Product Report'
                            ])
                        }
                    }
                }

                stage('E2E') {
                    steps {
                        bat 'mvn test -Dtest=E2ERunner'
                    }
                    post {
                        always {
                            publishHTML(target: [
                                allowMissing:          false,
                                alwaysLinkToLastBuild: true,
                                keepAll:               true,
                                reportDir:             'target/reports',
                                reportFiles:           'e2e-report.html',
                                reportName:            'E2E Report'
                            ])
                        }
                    }
                }

            }
        }
    }

    post {
        failure {
            mail to:      'tu-correo@gmail.com',
                 subject:  "FALLO: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body:     """El pipeline falló.
Job:   ${env.JOB_NAME}
Build: ${env.BUILD_NUMBER}
URL:   ${env.BUILD_URL}"""
        }
        always {
            junit allowEmptyResults: true,
                  testResults: 'target/reports/*-junit.xml'
            cleanWs()
        }
    }
}
```

> ⚠️ En Windows usar `bat` en vez de `sh`

Hacer push al repo:

```bash
git add Jenkinsfile
git commit -m "Add Jenkinsfile for CI/CD pipeline"
git push origin main
```

---

## 6. Configurar Email (Gmail)

### Crear App Password en Gmail

1. Ir a: https://myaccount.google.com/security
2. Activar **Verificación en 2 pasos** si no está activa
3. Ir a: https://myaccount.google.com/apppasswords
4. Crear nueva → nombre: `Jenkins`
5. Copiar la contraseña de **16 caracteres**

### Configurar en Jenkins

Ir a **Manage Jenkins → System → Configure**

#### Sección "E-mail Notification" → Avanzado

| Campo | Valor |
|---|---|
| SMTP server | `smtp.gmail.com` |
| ✅ Use SMTP Authentication | marcado |
| Username | `tu-correo@gmail.com` |
| Password | App Password de 16 chars |
| ✅ Use TLS | marcado |
| SMTP Port | `587` |

#### Sección "Extended E-mail Notification"

| Campo | Valor |
|---|---|
| SMTP server | `smtp.gmail.com` |
| SMTP Port | `587` |
| Credentials | `Gmail SMTP` |
| ✅ Use TLS | marcado |
| Default suffix | `@gmail.com` |

### Agregar credencial Gmail

**Manage Jenkins → Credentials → Add Credentials:**
- Kind: `Username with password`
- Username: `tu-correo@gmail.com`
- Password: App Password
- ID: `gmail-credentials`
- Description: `Gmail SMTP`

---

## 7. Webhook con ngrok (Jenkins local)

Como Jenkins corre en PC local, GitHub no puede llamarlo directamente. Se usa ngrok como túnel.

### Instalar ngrok

1. Descargar desde: https://ngrok.com/download → Windows
2. Extraer `ngrok.exe` a `C:\ngrok\`
3. Crear cuenta gratis en: https://dashboard.ngrok.com/signup
4. Copiar el Authtoken desde el dashboard

### Configurar y levantar ngrok

```powershell
# Guardar token (solo la primera vez)
C:\ngrok\ngrok config add-authtoken TU_TOKEN_AQUI

# Levantar túnel (ejecutar cada vez que se reinicie la PC)
C:\ngrok\ngrok http 8081
```

Copiar la URL que aparece, ejemplo:
```
Forwarding  https://abc123.ngrok-free.app -> http://localhost:8081
```

> ⚠️ La URL de ngrok cambia cada vez que se reinicia. Con cuenta gratuita no es fija.

### Configurar Webhook en GitHub

1. Ir al repo → **Settings → Webhooks → Add webhook**

| Campo | Valor |
|---|---|
| Payload URL | `https://TU-URL.ngrok-free.app/github-webhook/` |
| Content type | `application/json` |
| Secret | vacío |
| Which events | `Just the push event` |
| Active | ✅ |

2. Clic **Add webhook**
3. Verificar que aparezca ✅ verde en "Recent Deliveries"

---

## 8. Gestionar el servicio Jenkins

```powershell
# Ver estado
Get-Service -Name Jenkins

# Iniciar
Start-Service -Name Jenkins

# Detener
Stop-Service -Name Jenkins

# Reiniciar
Restart-Service -Name Jenkins
```

---

## 9. Ver Chrome ejecutándose en pantalla

Por defecto Jenkins corre como servicio del sistema y Chrome no es visible. Para verlo:

1. Presionar `Win + R` → escribir `services.msc` → Enter
2. Buscar **Jenkins** → clic derecho → **Propiedades**
3. Pestaña **"Iniciar sesión"**
4. Seleccionar **"Esta cuenta"**
5. Escribir tu usuario Windows: `.\TuUsuario`
6. Escribir tu contraseña de Windows
7. Clic **Aceptar** → clic derecho → **Reiniciar**

---

## 10. Flujo completo del CI/CD

```
Push a GitHub (main)
    ↓
Webhook notifica a Jenkins (via ngrok)
    ↓
Jenkins clona el repo
    ↓
mvn clean test-compile (Build)
    ↓
Tests en paralelo:
    ├── LoginRunner   → login-report.html
    ├── ProductRunner → product-report.html
    └── E2ERunner     → e2e-report.html
    ↓
Publica reportes HTML en Jenkins
    ↓
Si falla → envía email de alerta
```

---

## Reportes HTML

Los reportes se generan en `target/reports/` y se publican en Jenkins:

```
http://localhost:8081/job/automationSelenium/Login_20Report/
http://localhost:8081/job/automationSelenium/Product_20Report/
http://localhost:8081/job/automationSelenium/E2E_20Report/
```

---

## Comandos útiles de ejecución manual

```bash
# Ejecutar todos los tests
mvn clean test

# Por runner individual
mvn test -Dtest=LoginRunner
mvn test -Dtest=ProductRunner
mvn test -Dtest=E2ERunner
mvn test -Dtest=TestRunner

# Por tags (PowerShell)
mvn test "-Dcucumber.filter.tags=@login"
mvn test "-Dcucumber.filter.tags=@AgregarCarrito"

# Dry-run (sin abrir navegador)
mvn -Dtest=LoginRunner "-Dcucumber.execution.dry-run=true" test

# Solo compilar
mvn clean -DskipTests test-compile
```

---

*Documentación generada el 16/03/2026*