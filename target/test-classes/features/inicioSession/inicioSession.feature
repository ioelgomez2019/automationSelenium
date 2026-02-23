# language: es
@InicioSession
Feature: Inicio de Sesión - Saucedemo
  Como usuario de Saucedemo
  Quiero poder iniciar sesión con mis credenciales
  Para acceder a la tienda online

  Background:
    Given que el usuario abre el navegador y accede al sistema

  @LoginExitoso @SauceDemo
  Scenario: Inicio de sesión exitoso con standard_user
    When el usuario ingresa el correo "standard_user"
    And el usuario ingresa la contraseña "secret_sauce"
    And el usuario hace clic en el botón "Ingresar"
    Then el usuario debe ver el dashboard principal
    And el mensaje de bienvenida debe contener "Swag Labs"

  @LoginExitoso @SauceDemo
  Scenario: Inicio de sesión exitoso con performance_glitch_user
    When el usuario ingresa el correo "performance_glitch_user"
    And el usuario ingresa la contraseña "secret_sauce"
    And el usuario hace clic en el botón "Ingresar"
    Then el usuario debe ver el dashboard principal
    And el mensaje de bienvenida debe contener "Swag Labs"

  @LoginFallido @SauceDemo
  Scenario: Inicio de sesión fallido con locked_out_user
    When el usuario ingresa el correo "locked_out_user"
    And el usuario ingresa la contraseña "secret_sauce"
    And el usuario hace clic en el botón "Ingresar"
    Then el sistema debe mostrar el mensaje de error "locked out"

  @LoginFallido @SauceDemo
  Scenario: Inicio de sesión fallido con credenciales incorrectas
    When el usuario ingresa el correo "usuario_invalido"
    And el usuario ingresa la contraseña "password_incorrecta"
    And el usuario hace clic en el botón "Ingresar"
    Then el sistema debe mostrar el mensaje de error "do not match"

  @LoginCamposVacios @SauceDemo
  Scenario: Inicio de sesión con campo de usuario vacío
    When el usuario no ingresa ningún dato
    And el usuario hace clic en el botón "Ingresar"
    Then el sistema debe mostrar el mensaje de error "Username is required"
