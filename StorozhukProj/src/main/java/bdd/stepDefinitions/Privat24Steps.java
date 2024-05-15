package bdd.stepDefinitions;

import api.ApiHelperPrivatBank;
import api.dto.responseDto.FxRateDto;
import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;

import java.util.List;

public class Privat24Steps extends MainSteps {

    public Privat24Steps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I have {string} currency code")
    public void iHaveCurrencyCode(String currencyCode) {
        TestData.CCY = currencyCode;
    }

    @When("I open Privat24 website")
    public void iOpenPrivat24Website() {
        pageProvider.getPrivat24HomePage().open();
    }

    @Then("I take a buy and sell exchange rates for the currency from website")
    public void iTakeFxRateForCurrency() {
        TestData.BUY_FX_SITE = pageProvider.getPrivat24HomePage().getFxRate(TestData.CCY, "buy");
        TestData.SELL_FX_SITE = pageProvider.getPrivat24HomePage().getFxRate(TestData.CCY, "sell");
    }

    @And("I take a buy and sell exchange rates for all currencies from API")
    public void iTakeExchangeRatesFromAPI() {
        ApiHelperPrivatBank api = new ApiHelperPrivatBank();
        List<FxRateDto> response = api.getCurrentFxRates();

        for (int i = 0; i < response.size(); i++) {
            if(response.get(i).getCcy().equals(TestData.CCY)) {
                TestData.BUY_FX_API = response.get(i).getBuy();
                TestData.SELL_FX_API = response.get(i).getSale();

                //we are testing against one currency at a time
                return;
            }
        }
    }

    @Then("The exchange rates are the same")
    public void theExchangeRatesAreTheSame() {
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(TestData.BUY_FX_SITE)
                .as("Buy FX rates from Site equals to Api rates")
                .isEqualTo(TestData.BUY_FX_API);
        softAssertions.assertThat(TestData.SELL_FX_SITE)
                .as("Buy FX rates from Site equals to Api rates")
                .isEqualTo(TestData.SELL_FX_API);

        softAssertions.assertAll();
    }
}
