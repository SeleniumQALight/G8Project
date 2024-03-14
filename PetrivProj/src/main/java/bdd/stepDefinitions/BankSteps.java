package bdd.stepDefinitions;

import api.PrivatBankApiHelper;
import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BankSteps extends MainSteps {
    private PrivatBankApiHelper apiHelper = new PrivatBankApiHelper();

    public BankSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I receive {string} rate via API")
    public void iReceiveCurrencyRateViaAPI(String currencyName) {
        apiHelper.getRateForCurrency(currencyName);
    }

    @When("I open Main bank page")
    public void iOpenMainBankPage() {
        pageProvider.getPrivatBankMainPage().openMainPage();
    }

    @When("I see {string} rate on the page")
    public void iSeeCurrencyRateOnThePage(String currencyName) {
        pageProvider.getPrivatBankMainPage().storeRateForCurrency(currencyName);
    }

    @Then("I compare {string} rates on UI and API")
    public void iCompareCurrencyRates(String currencyName) {
        apiHelper.checkUiAndApiCurrenciesRates(currencyName);
    }
}
