@AgregarCarrito
Feature: Agregar Producto al Carrito
  Como usuario del sistema
  Quiero poder agregar productos al carrito de compras
  Para poder realizar una compra

  Background:
    Given que el usuario abre el navegador y accede al sistema
    When el usuario ingresa el correo "standard_user"
    And el usuario ingresa la contrasena "secret_sauce"
    And el usuario hace clic en el boton ingresar

  @agregarCarrito @Order1
  Scenario Outline: Agregar lista de productos al carrito
    And el usuario agrega la lista de productos "<productos>"
    Then el carrito debe mostrar <cantidad> productos

    Examples:
      | productos                                                         | cantidad |
      | Sauce Labs Backpack;Sauce Labs Bike Light;Sauce Labs Bolt T-Shirt  | 3        |
