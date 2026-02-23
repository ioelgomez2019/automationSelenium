# language: es
@AgregarCarrito
Feature: Agregar Producto al Carrito
  Como usuario del sistema
  Quiero poder agregar productos al carrito de compras
  Para poder realizar una compra

  Background:
    Given que el usuario está autenticado en el sistema
    And que el usuario se encuentra en la página de productos

  @AgregarProductoExitoso
  Scenario: Agregar un producto al carrito correctamente
    When el usuario selecciona el producto "Laptop Dell XPS 15"
    And el usuario hace clic en "Agregar al Carrito"
    Then el carrito debe mostrar "1" producto
    And el producto "Laptop Dell XPS 15" debe aparecer en el carrito

  @AgregarMultiplesProductos
  Scenario: Agregar múltiples productos al carrito
    When el usuario selecciona el producto "Laptop Dell XPS 15"
    And el usuario hace clic en "Agregar al Carrito"
    And el usuario selecciona el producto "Mouse Logitech MX"
    And el usuario hace clic en "Agregar al Carrito"
    Then el carrito debe mostrar "2" productos

  @EliminarProductoCarrito
  Scenario: Eliminar un producto del carrito
    Given que el carrito tiene el producto "Laptop Dell XPS 15"
    When el usuario elimina el producto "Laptop Dell XPS 15" del carrito
    Then el carrito debe estar vacío
