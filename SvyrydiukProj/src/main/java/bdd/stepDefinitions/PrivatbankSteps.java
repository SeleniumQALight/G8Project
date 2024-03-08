package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import pages.PrivatBankPage;


public class PrivatbankSteps extends MainSteps{
    private apiPrivatbank.ApiHelperPrivatBank apiHelper = new apiPrivatbank.ApiHelperPrivatBank();
    protected Logger logger = Logger.getLogger(getClass());

    public PrivatbankSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I get the cours for {string} from API")
    public void iGetTheCoursForFromAPI(String currency) {
        apiHelper.saveExchangeRateByCoursIdToTestDataForSelectedCurrency(currency);
        logger.info("Buy Cours from API: " + TestData.privatBankBuyCours + " Sell Cours from API: " + TestData.privatBankSaleCours);
    }

    @When("I get the cours for {string} from UI")
    public void iGetTheCoursForFromUI(String currency) {
        PrivatBankPage privatBankPage = new PrivatBankPage(webDriverHelper.getWebDriver());
        privatBankPage.openPage();
        privatBankPage.getBuyRate(currency);
        privatBankPage.getSellRate(currency);
        logger.info("Buy Cours from UI: " + TestData.privatBankBuyCoursUI + " Sell Cours from UI: " + TestData.privatBankSaleCoursUI);
    }

    @Then("I compare the cours from API and UI")
    public void iCompareTheCoursFromAPIAndUI() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(TestData.privatBankBuyCoursUI).isEqualTo(TestData.privatBankBuyCours);
        softAssertions.assertThat(TestData.privatBankSaleCoursUI).isEqualTo(TestData.privatBankSaleCours);
        softAssertions.assertAll();
    }
}
