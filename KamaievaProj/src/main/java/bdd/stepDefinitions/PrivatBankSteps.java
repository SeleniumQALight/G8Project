package bdd.stepDefinitions;

import apiPrivatbank.ApiPrivatbankHelper;
import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class PrivatBankSteps extends MainSteps{

    Logger logger = Logger.getLogger(getClass());
    ApiPrivatbankHelper apiPrivatbankHelper = new ApiPrivatbankHelper();

    public PrivatBankSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I get exchange rates for {string} currency from API")
    public void iGetExchangeRatesForCurrencyFromAPI(String currency) {
        apiPrivatbankHelper.saveExchangeRateByCurrency(currency);
        logger.info("Buy exchange rates from API: " + TestData.EXCHANGE_BUY_API);
        logger.info("Sell exchange rates from API: " + TestData.EXCHANGE_SELL_API);
    }

    @When("I open PrivateBank main web page")
    public void iOpenPrivateBankMainWebPage() {
        pageProvider.getPrivatBankPage().openPrivatBankPage();
    }

    @Then("I see exchange rates for {string} currency on PrivateBank main web page")
    public void iSeeExchangeRatesForCurrencyOnPrivateBankMainWebPage(String currency) {
        pageProvider.getPrivatBankPage().getBuyCurrency(currency);
        pageProvider.getPrivatBankPage().getSellCurrency(currency);
        logger.info("Buy exchange rates from UI: " + TestData.EXCHANGE_BUY_UI);
        logger.info("Sell exchange rates from UI: " + TestData.EXCHANGE_SELL_UI);
    }

    @And("I compare exchange rates from API with exchange rates from web page")
    public void iCompareExchangeRatesFromAPIWithExchangeRatesFromWebPage() {
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(TestData.EXCHANGE_BUY_API)
                .as("Buy exchange rates from API and UI should match")
                .isEqualTo(TestData.EXCHANGE_BUY_UI);
        softAssertions.assertThat(TestData.EXCHANGE_SELL_API)
                .as("Sell exchange rates from API and UI should match")
                .isEqualTo(TestData.EXCHANGE_SELL_UI);

        logger.info("Buy exchange rates from API: " + TestData.EXCHANGE_BUY_API);
        logger.info("Buy exchange rates from UI: " + TestData.EXCHANGE_BUY_UI);

        logger.info("Sell exchange rates from API: " + TestData.EXCHANGE_SELL_API);
        logger.info("Sell exchange rates from UI: " + TestData.EXCHANGE_SELL_UI);

        softAssertions.assertAll();
    }
}
