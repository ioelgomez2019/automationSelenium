@echo off
REM ============================================================
REM Script de ejecución de pruebas - Saucedemo
REM Proyecto: AutomationCorebank - Banco Pichincha
REM ============================================================

echo.
echo ========================================
echo  PRUEBAS DE AUTOMATIZACION - SAUCEDEMO
echo ========================================
echo.
echo Seleccione una opcion:
echo.
echo [1] Ejecutar TODAS las pruebas
echo [2] Ejecutar solo pruebas de Login Exitoso
echo [3] Ejecutar solo pruebas de Login Fallido
echo [4] Ejecutar todas las pruebas de Saucedemo
echo [5] Ejecutar con reporte detallado
echo [6] Compilar proyecto
echo [0] Salir
echo.

set /p opcion="Ingrese opcion (0-6): "

if "%opcion%"=="1" goto todas
if "%opcion%"=="2" goto exitoso
if "%opcion%"=="3" goto fallido
if "%opcion%"=="4" goto saucedemo
if "%opcion%"=="5" goto detallado
if "%opcion%"=="6" goto compilar
if "%opcion%"=="0" goto salir

:todas
echo.
echo Ejecutando TODAS las pruebas...
call mvn clean test
goto fin

:exitoso
echo.
echo Ejecutando pruebas de Login Exitoso...
call mvn test -Dcucumber.filter.tags="@LoginExitoso"
goto fin

:fallido
echo.
echo Ejecutando pruebas de Login Fallido...
call mvn test -Dcucumber.filter.tags="@LoginFallido"
goto fin

:saucedemo
echo.
echo Ejecutando todas las pruebas de Saucedemo...
call mvn test -Dcucumber.filter.tags="@SauceDemo"
goto fin

:detallado
echo.
echo Ejecutando con reporte detallado...
call mvn clean test -X
goto fin

:compilar
echo.
echo Compilando proyecto...
call mvn clean compile
goto fin

:salir
echo.
echo Saliendo...
goto fin

:fin
echo.
echo ========================================
echo  EJECUCION COMPLETADA
echo ========================================
echo.
echo Reportes disponibles en: target/reports/cucumber-report.html
echo.
pause
