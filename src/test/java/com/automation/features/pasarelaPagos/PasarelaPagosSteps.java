package com.automation.features.pasarelaPagos;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PasarelaPagosSteps {

    private PasarelaPagosBusiness pasarelaPagosBusiness;

    private PasarelaPagosBusiness getPasarelaPagosBusiness() {
        if (pasarelaPagosBusiness == null) {
            pasarelaPagosBusiness = new PasarelaPagosBusiness();
        }
        return pasarelaPagosBusiness;
    }

    @Given("abre el carrito de compra")
    public void abreElCarritoDeCompra() {
        getPasarelaPagosBusiness().openCart();
    }

    @When("verifica que estems en la ruta correcta")
    public void verificaQueEstemsEnLaRutaCorrecta() {
        getPasarelaPagosBusiness().validateCartUrl();
    }

    @And("el usuario hace click en checkout")
    public void elUsuarioHaceClickEnCheckout() {
        getPasarelaPagosBusiness().clickCheckout();
    }

    @And("el usuario completa los datos del checkout")
    public void elUsuarioCompletaLosDatosDelCheckout() {
        getPasarelaPagosBusiness().completeCheckoutData();
    }

    @And("el usuario hace click en continuar")
    public void elUsuarioHaceClickEnContinuar() {
        getPasarelaPagosBusiness().clickContinue();
    }

    @And("el usuario verifica el total")
    public void elUsuarioVerificaElTotal() {
        getPasarelaPagosBusiness().validateTotalGreaterThanZero();
    }

    @And("el usuario hace click en finish")
    public void elUsuarioHaceClickEnFinish() {
        getPasarelaPagosBusiness().clickFinish();
    }

    @Then("espera el mensaje {string}")
    public void esperaElMensaje(String mensaje) {
        getPasarelaPagosBusiness().validateCompleteMessage(mensaje);
    }
}
