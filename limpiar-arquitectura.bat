@echo off
REM ============================================
REM Script para Limpiar Carpeta Script/
REM Este script elimina la carpeta Script/ obsoleta
REM ============================================

echo.
echo ============================================
echo   LIMPIAR ARQUITECTURA - ELIMINAR Script/
echo ============================================
echo.
echo Este script eliminara la carpeta Script/ de la raiz del proyecto.
echo.
echo RAZON: Los archivos .java en Script/ estan FUERA de la estructura
echo Maven estandar (src/main/java) y causan el error:
echo "files located outside of the module source root, so it won't be compiled"
echo.
echo Todos los archivos ya fueron migrados a:
echo   - src/main/java/com/automation/... (codigo principal)
echo   - src/test/java/com/automation/... (tests y steps)
echo.
set /p confirmar="Desea eliminar la carpeta Script/? (S/N): "

if /i "%confirmar%"=="S" goto eliminar
if /i "%confirmar%"=="s" goto eliminar
goto cancelar

:eliminar
echo.
echo Eliminando carpeta Script/...
if exist "Script\" (
    rd /s /q "Script\"
    echo.
    echo ============================================
    echo   CARPETA Script/ ELIMINADA EXITOSAMENTE
    echo ============================================
    echo.
    echo La arquitectura ahora sigue las convenciones Maven:
    echo.
    echo src/
    echo   ├── main/java/com/automation/
    echo   │   ├── script/                # Codigo principal
    echo   │   │   ├── componentes/       # DriverManager, BasePage, etc.
    echo   │   │   ├── config/            # ConfigManager
    echo   │   │   ├── InicioSession/     # Pages y logica de Login
    echo   │   │   └── AgregarCarrito/    # Pages y logica de Carrito
    echo   └── test/java/com/automation/
    echo       ├── stepdefinitions/       # Step Definitions (Cucumber)
    echo       └── runners/               # Runners (TestNG)
    echo.
    echo Para compilar y ejecutar ahora ejecute:
    echo   mvn clean compile
    echo   mvn test -Dtest=LoginRunner
    echo.
) else (
    echo.
    echo ERROR: La carpeta Script/ no existe o ya fue eliminada.
    echo.
)
goto fin

:cancelar
echo.
echo Operacion cancelada por el usuario.
echo.
goto fin

:fin
pause
