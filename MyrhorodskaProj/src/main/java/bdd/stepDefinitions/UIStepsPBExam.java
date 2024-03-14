package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UIStepsPBExam extends MainSteps {
    public UIStepsPBExam(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @When("I open the PrivatBank website")
    public void iOpenThePrivatBankWebsite() {
        pageProvider.getPrivatBankHomePage().openPBHomePage();
    }

    @When("I save exchange rate for buy and sale for {string} from UI")
    public void iSaveExchangeRateForBuyAndSaleForFromUi(String currencyName) {
        pageProvider.getPrivatBankHomePage().getBuyAndSaleRateFromUI(currencyName);
    }

    @Then("I compare the API exchange rate with the UI exchange rate")
    public void iCompareTheApiExchangeRateWithTheUiExchangeRate() {
        pageProvider.getPrivatBankHomePage().compareApiAndUiRates();
    }
}
