@echo off
REM ============================================
REM Script de EjecuciÃ³n de Pruebas BDD - TestNG
REM Automation Corebank - Banco Pichincha
REM ============================================

echo.
echo ============================================
echo   MENU DE EJECUCION DE PRUEBAS - TESTNG
echo ============================================
echo.
echo Seleccione el tipo de prueba a ejecutar:
echo.
echo 1. TODAS LAS PRUEBAS (E2ERunner)
echo 2. SOLO LOGIN (@login - LoginRunner)
echo 3. SOLO CARRITO (ProductRunner)
echo 4. FLUJO E2E - End to End (E2ERunner)
echo 5. EJECUTAR POR TAG PERSONALIZADO
echo 6. SALIR
echo.

set /p opcion="Ingrese una opcion (1-6): "

if "%opcion%"=="1" goto todas
if "%opcion%"=="2" goto login
if "%opcion%"=="3" goto carrito
if "%opcion%"=="4" goto e2e
if "%opcion%"=="5" goto custom
if "%opcion%"=="6" goto salir

echo Opcion invalida. Intente nuevamente.
pause
goto inicio

:todas
echo.
echo ============================================
echo   Ejecutando TODAS las pruebas...
echo ============================================
echo.
call mvn clean test
goto fin

:login
echo.
echo ============================================
echo   Ejecutando pruebas de LOGIN (@login)...
echo ============================================
echo.
call mvn clean test -Dtest=LoginRunner
goto fin

:carrito
echo.
echo ============================================
echo   Ejecutando pruebas de CARRITO...
echo ============================================
echo.
call mvn clean test -Dtest=ProductRunner
goto fin

:e2e
echo.
echo ============================================
echo   Ejecutando FLUJO E2E (Login + Carrito)...
echo ============================================
echo.
call mvn clean test -Dtest=E2ERunner
goto fin

:custom
echo.
echo ============================================
echo   Ejecucion con TAG PERSONALIZADO
echo ============================================
echo.
echo Ejemplos de tags:
echo   @login
echo   @LoginExitoso
echo   @AgregarCarrito
echo   @login and @LoginExitoso
echo   @login or @AgregarCarrito
echo   not @WIP
echo.
set /p tag="Ingrese el tag a ejecutar: "
echo.
echo Ejecutando pruebas con tag: %tag%
echo.
call mvn clean test -Dcucumber.filter.tags="%tag%"
goto fin

:fin
echo.
echo ============================================
echo   EJECUCION COMPLETADA
echo ============================================
echo.
echo Los reportes se encuentran en:
echo   - HTML: target\reports\cucumber-report.html
echo   - JSON: target\reports\cucumber-report.json
echo   - XML:  target\reports\cucumber-junit.xml
echo.
pause
goto salir

:salir
echo.
echo Saliendo...
exit
