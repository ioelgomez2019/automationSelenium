@AgregarCarrito
Feature: Agregar Producto al Carrito
  Como usuario del sistema
  Quiero poder agregar productos al carrito de compras
  Para poder realizar una compra

  Background:
    Given que el usuario abre el navegador y accede al sistema
    And que el usuario está autenticado en el sistema

  @AgregarProductoExitoso @Order_1
  Scenario: Agregar un producto al carrito correctamente
    # Fresh login - carrito must be empty at start
    When el usuario selecciona el producto "Sauce Labs Backpack"
    And el usuario hace clic en agregar al carrito
    Then el carrito debe mostrar 1 producto

  @AgregarMultiplesProductos @Order_2
  Scenario: Agregar múltiples productos al carrito
    When el usuario selecciona el producto "Sauce Labs Backpack"
    And el usuario hace clic en agregar al carrito
    And el usuario selecciona el producto "Sauce Labs Bike Light"
    And el usuario hace clic en agregar al carrito
    Then el carrito debe mostrar 2 productos

  @EliminarProductoCarrito @Order_3
  Scenario: Eliminar un producto del carrito
    Given que el carrito tiene el producto "Sauce Labs Backpack"
    When el usuario elimina el producto "Sauce Labs Backpack" del carrito
    Then el carrito debe estar vacío

