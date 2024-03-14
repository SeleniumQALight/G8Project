package bdd.stepDefinitions;

import api_privat.ApiHelperPrivat;
import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PrivatSteps extends MainSteps{
    private ApiHelperPrivat apiHelperPrivat = new ApiHelperPrivat();
    public PrivatSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I get the exchange rate for {string} with API")
    public void iGetTheExchangeRateForCurrencyWithAPI(String currency) {
        apiHelperPrivat.getExchangesRateForCurrency(currency);
    }

    @When("I open the main page")
    public void iOpenTheMainPage() {
        pageProvider.getPrivatMainPage().openPrivatMainPage();
    }

    @When("I get exchange rate for {string} with UI")
    public void iGetExchangeRateForCurrencyWithUI(String currency) {
        pageProvider.getPrivatMainPage()
                .getBuyExchangeRate(currency)
                .getSaleExchangeRate(currency);
    }

    @Then("I compare the received data for {string}")
    public void iCompareTheReceivedDataForCurrency(String currency) {
        apiHelperPrivat.compareExchangeRatesWithUiAndApi(currency);
    }
}
