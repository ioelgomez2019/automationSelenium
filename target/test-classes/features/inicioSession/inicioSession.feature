@InicioSession @login
Feature: Inicio de Sesion - Saucedemo
  Como usuario de Saucedemo
  Quiero poder iniciar sesion con mis credenciales
  Para acceder a la tienda online

  Background:
    Given que el usuario abre el navegador y accede al sistema

  @Login @SauceDemo @funcional
  Scenario Outline: Inicio de sesion exitoso
    When el usuario ingresa el correo "<user>"
    And el usuario ingresa la contrasena "<password>"
    And el usuario hace clic en el boton ingresar
    Then el usuario debe ver el dashboard principal
    And el mensaje de bienvenida debe contener "<mensaje>"

    Examples:
      | user          | password     | mensaje   |
      | standard_user | secret_sauce | Swag Labs |


  @LoginFallido @SauceDemo @funcionalerror
  Scenario Outline: Inicio de sesion fallido
    When el usuario ingresa el correo "<user>"
    And el usuario ingresa la contrasena "<password>"
    And el usuario hace clic en el boton ingresar
    Then el sistema debe mostrar el mensaje de error "<mensaje_error>"

    Examples:
      | user            | password     | mensaje_error |
      | fall            | secret_sauce | do not match  |
