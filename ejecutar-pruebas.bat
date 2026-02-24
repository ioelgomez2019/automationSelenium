@echo off
REM Automation Corebank - Test Execution Script
REM Usage: ejecutar-pruebas.bat [option]

echo.
echo ==================================================
echo    AUTOMATION COREBANK - TEST EXECUTION
echo ==================================================
echo.

if "%1"=="" (
    echo Usage: ejecutar-pruebas [option]
    echo.
    echo Options:
    echo   all          Run all tests
    echo   login        Run only @login scenarios
    echo   product      Run only @AgregarCarrito scenarios
    echo   e2e          Run login + product E2E workflow
    echo.
    exit /b 1
)

if "%1"=="all" (
    echo Running: ALL TESTS
    echo.
    call mvn clean test
    goto end
)

if "%1"=="login" (
    echo Running: LOGIN TESTS (@login)
    echo.
    call mvn clean test -Dtest=LoginRunner
    goto end
)

if "%1"=="product" (
    echo Running: PRODUCT TESTS (@AgregarCarrito)
    echo.
    call mvn clean test -Dtest=ProductRunner
    goto end
)

if "%1"=="e2e" (
    echo Running: E2E TESTS (Login + Product)
    echo.
    call mvn clean test -Dtest=E2ERunner
    goto end
)

echo Unknown option: %1
exit /b 1

:end
echo.
echo ==================================================
echo    Test execution completed
echo ==================================================
pause
