@AgregarCarrito
Feature: Agregar Producto al Carrito
  Como usuario del sistema
  Quiero poder agregar productos al carrito de compras
  Para poder realizar una compra

  Background:
    Given que el usuario abre el navegador y accede al sistema

  @agregarCarrito @Order1
  Scenario Outline: Agregar un producto al carrito correctamente
    # Fresh login - carrito must be empty at start
    When el usuario ingresa el correo "<user>"
    And el usuario ingresa la contrasena "<password>"
    And el usuario hace clic en el boton ingresar
    And el usuario selecciona el producto "<producto>"
    And el usuario hace clic en agregar al carrito
    Then el carrito debe mostrar 1 producto

    Examples:
      | user          | password     | producto   |
      | standard_user | secret_sauce | Sauce Labs Backpack |

