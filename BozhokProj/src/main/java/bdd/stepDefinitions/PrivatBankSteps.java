package bdd.stepDefinitions;

import apiPrivatBank.ApiHelperPrivatBank;
import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.en.Given;
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

    @Given("I send request to PrivatBank API")
    public void iSendRequestToPrivatBankAPI(String currency) {
        apiHelperPrivatBank.getExchangeRatePBByCurrency(currency);
    }

    @When("I get and save response from PrivatBank API")
    public void iGetAndSaveResponseFromPrivatBankAPI() {

    }

    @When("I save exchange rates from UI")
    public void iSaveExchangeRatesFromUI() {
        pageProvider.homePagePrivatBank().getBuyExchangeRate();
        pageProvider.homePagePrivatBank().getSellExchangeRate();

    }
}
