# language: es
@InicioSession
Feature: Inicio de Sesión
  Como usuario del sistema Corebank
  Quiero poder iniciar sesión con mis credenciales
  Para acceder a las funcionalidades del sistema

  Background:
    Given que el usuario abre el navegador y accede al sistema

  @LoginExitoso
  Scenario: Inicio de sesión exitoso con credenciales válidas
    When el usuario ingresa el correo "admin@corebank.com"
    And el usuario ingresa la contraseña "Admin123*"
    And el usuario hace clic en el botón "Ingresar"
    Then el usuario debe ver el dashboard principal
    And el mensaje de bienvenida debe contener "Bienvenido"

  @LoginFallido
  Scenario: Inicio de sesión fallido con credenciales incorrectas
    When el usuario ingresa el correo "usuario@invalido.com"
    And el usuario ingresa la contraseña "ClaveIncorrecta"
    And el usuario hace clic en el botón "Ingresar"
    Then el sistema debe mostrar el mensaje de error "Credenciales inválidas"

  @LoginCamposVacios
  Scenario: Inicio de sesión con campos vacíos
    When el usuario no ingresa ningún dato
    And el usuario hace clic en el botón "Ingresar"
    Then el sistema debe mostrar validaciones de campos requeridos
