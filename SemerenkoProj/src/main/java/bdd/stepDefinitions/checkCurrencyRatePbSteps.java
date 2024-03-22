package bdd.stepDefinitions;


import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class checkCurrencyRatePbSteps extends MainSteps {
    public checkCurrencyRatePbSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @When("I open PrivatBank page")
    public void i_open_privat_bank_page() {
        pageProvider.privatBankPage().openPrivatBankPage();
    }

    @Then("I take currency rate from page by {string} and compare values with API")
    public void i_take_currency_rate_from_page_by_and_compare_values_with_api(String currency) {
        pageProvider.privatBankPage().checkCurrencyRateCompareUiAndApi(currency, TestData.currencyRatesMap);
    }

}
