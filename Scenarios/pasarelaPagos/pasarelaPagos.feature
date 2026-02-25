@PasarelaPagos
Feature: hacer la pasarela de pagos

  Background:
    Given que el usuario abre el navegador y accede al sistema
    When el usuario ingresa el correo "standard_user"
    And el usuario ingresa la contrasena "secret_sauce"
    And el usuario hace clic en el boton ingresar
    And el usuario agrega la lista de productos "Sauce Labs Backpack;Sauce Labs Bike Light;Sauce Labs Bolt T-Shirt"

  @PasarelaPagos
  Scenario Outline: reliza la pasarela de pagos
    Given abre el carrito de compra
    When verifica que estems en la ruta correcta
    And el usuario hace click en checkout
    And el usuario completa los datos del checkout
    And el usuario hace click en continuar
    And el usuario verifica el total
    And el usuario hace click en finish
    Then espera el mensaje "<mensaje>"

    Examples:
      | mensaje                            |
      | Thank you for your order!          |
