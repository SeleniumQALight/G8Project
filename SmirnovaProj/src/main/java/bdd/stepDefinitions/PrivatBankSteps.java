package bdd.stepDefinitions;

import api.ApiHelperPrivatBank;
import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class PrivatBankSteps extends MainSteps {

    private ApiHelperPrivatBank apiHelperPrivatBank = new ApiHelperPrivatBank();
    Logger logger = Logger.getLogger(getClass());


    public PrivatBankSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I get the exchange rate for {string} from PrivatBank using API")
    public void iGetTheExchangeRateForCurrencyFromPrivatBankUsingAPI(String currency) {
        apiHelperPrivatBank.getExchangeRate();
        apiHelperPrivatBank.saveExchangeRateByCurrency(currency);
    }

    @When("I open PrivatBank page")
    public void iOpenPrivatBankPage() {
        pageProvider.getPrivatBankPage().openPrivatBankPage();
    }

    @Then("I see the exchange rate for {string} on the page")
    public void iSeeTheExchangeRateForCurrencyOnThePage(String currency) {
        pageProvider.getPrivatBankPage().getBuyCurrency(currency);
        pageProvider.getPrivatBankPage().getSellCurrency(currency);
    }

    @And("I compare the exchange rate from API with the exchange rate from the page")
    public void iCompareTheExchangeRateFromAPIWithTheExchangeRateFromThePage() {
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
