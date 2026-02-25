package com.automation.features.pasarelaPagos;

import org.testng.Assert;

public class PasarelaPagosBusiness {

    private static final String DEFAULT_FIRST_NAME = "Automation";
    private static final String DEFAULT_LAST_NAME = "User";
    private static final String DEFAULT_POSTAL_CODE = "10001";

    private final PasarelaPagosPage pasarelaPagosPage;

    public PasarelaPagosBusiness() {
        this.pasarelaPagosPage = new PasarelaPagosPage();
    }

    public void openCart() {
        pasarelaPagosPage.openCart();
    }

    public void validateCartUrl() {
        String currentUrl = pasarelaPagosPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/cart.html"),
            "Expected cart url to contain '/cart.html' but was: " + currentUrl);
    }

    public void clickCheckout() {
        pasarelaPagosPage.clickCheckout();
    }

    public void completeCheckoutData() {
        pasarelaPagosPage.fillCheckoutData(DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_POSTAL_CODE);
    }

    public void clickContinue() {
        pasarelaPagosPage.clickContinue();
    }

    public void validateTotalGreaterThanZero() {
        String totalText = pasarelaPagosPage.getSummaryTotalText();
        double totalValue = parseTotal(totalText);
        Assert.assertTrue(totalValue > 0,
            "Expected checkout total > 0 but was: " + totalValue + " (raw: " + totalText + ")");
    }

    public void clickFinish() {
        pasarelaPagosPage.clickFinish();
    }

    public void validateCompleteMessage(String expectedMessage) {
        String actualMessage = pasarelaPagosPage.getCompleteHeaderText();
        Assert.assertEquals(actualMessage.trim(), expectedMessage.trim(),
            "Unexpected complete checkout message");
    }

    private double parseTotal(String totalText) {
        String numeric = totalText.replaceAll("[^0-9.,]", "");
        if (numeric.isEmpty()) {
            throw new IllegalArgumentException("Could not parse total from text: " + totalText);
        }

        if (numeric.contains(",") && numeric.contains(".")) {
            numeric = numeric.replace(",", "");
        } else if (numeric.contains(",") && !numeric.contains(".")) {
            numeric = numeric.replace(",", ".");
        }

        return Double.parseDouble(numeric);
    }
}
