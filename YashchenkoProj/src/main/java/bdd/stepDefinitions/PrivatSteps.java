package bdd.stepDefinitions;

import api.privatBank.PrivatBankApiHelper;
import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static data.TestData.apiRates;
import static data.TestData.uiRates;

public class PrivatSteps extends MainSteps {
    public PrivatSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    PrivatBankApiHelper privatBankApiHelper = new PrivatBankApiHelper();

    @Given("I receive current exchange rate from API for needed {string}")
    public void iReceiveCurrentExchangeRateFromAPIForNeeded(String currency) {
        privatBankApiHelper.getCurrentRatesAsHashMapForSpecificCurrency(currency);
    }

    @When("I open PrivatBank home page")
    public void iOpenPrivatBankHomePage() {
        pageProvider.getPrivatPage().openPrivatHomePage().checkIsRedirectedToPrivatHomePage();
    }

    @Then("I see correct rate for {string} on UI")
    public void iSeeCorrectRateForOnUI(String currency) {
        pageProvider.getPrivatPage().getCurrentCurrencyRatesOnUiAsHashMap(currency);
        Assert.assertEquals("api rates is not equal to ui rates", apiRates, uiRates);
    }
}
