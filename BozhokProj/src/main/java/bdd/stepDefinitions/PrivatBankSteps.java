package bdd.stepDefinitions;

import apiPrivatBank.ApiHelperPrivatBank;
import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PrivatBankSteps extends MainSteps {
    public PrivatBankSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    ApiHelperPrivatBank apiHelperPrivatBank = new ApiHelperPrivatBank();

    @Given("I open Home page PrivatBank")
    public void iOpenHomePagePrivatBank() {
        pageProvider.homePagePrivatBank().openHomePage();
    }

    @Given("I send request to PrivatBank API for {string} currency")
    public void iSendRequestToPrivatBankAPI(String currency) {
        apiHelperPrivatBank.getExchangeRatePBByCurrency(currency);
    }

    @When("I get and save response from PrivatBank API")
    public void iGetAndSaveResponseFromPrivatBankAPI() {

    }

    @When("I save exchange rates from UI for {string} currency")
    public void iSaveExchangeRatesFromUI(String currency) {
        pageProvider.homePagePrivatBank().getBuyExchangeRate(currency);
        pageProvider.homePagePrivatBank().getSellExchangeRate(currency);

    }

    @Then("I compare exchange rates from API with UI for {string} currency")
    public void iCompareExchangeRatesFromAPIWithUIForCurrency(String currency) {
        pageProvider.homePagePrivatBank().compareExchangeRates(currency);
    }
}
