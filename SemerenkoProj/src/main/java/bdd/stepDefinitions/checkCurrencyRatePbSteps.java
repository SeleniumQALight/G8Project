package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class checkCurrencyRatePbSteps extends MainSteps
{
    public checkCurrencyRatePbSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I send request for {string} rates to API")
    public void i_send_request_for_rates_to_api(String string) {

    }

    @When("I open PrivatBank page")
    public void i_open_privat_bank_page() {

    }

    @Then("I take currency rate from page and compare values with API")
    public void i_take_currency_rate_from_page_and_compare_values_with_api() {

    }

}
