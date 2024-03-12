package bdd.stepDefinitions;

import apiPrivatBank.ApiHelperPrivatBank;
import bdd.helpers.WebDriverHelper;

import data.TestData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

public class PrivatBankSteps extends  MainSteps{

    Logger logger = Logger.getLogger(getClass());
    ApiHelperPrivatBank apiHelperPrivatBank = new ApiHelperPrivatBank();

    public PrivatBankSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I get course for {string} from Api")
    public void iGetCourseForFromApi(String currency) {
        apiHelperPrivatBank.recordExchangeRateForCourseIdInTestDataWithSelectedCurrency(currency);
        logger.info("Buy course for " + TestData.privatBankBuyCourse + " Sell course from API: " + TestData.privatBankSaleCourse);
    }

    @When("I open main web page PrivateBank")
    public void iOpenMainWebPagePrivateBank() {
        pageProvider.getPrivatBankPage().openPage();
    }

    @Then("I check course for {string} from web page")
    public void iCheckCourseForFromWebPage(String currency) {
        pageProvider.getPrivatBankPage().getBuyCourse(currency);
        pageProvider.getPrivatBankPage().getSellCourse(currency);
        logger.info("Buy course for " + TestData.privatBankBuyCourseWebPage + " Sell course from web page: " + TestData.privatBankSaleCourseWebPage);

    }

    @And("I compare the course from API and web page")
    public void iCompareTheCourseFromAPIAndWebPage() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(TestData.privatBankBuyCourse).isEqualTo(TestData.privatBankBuyCourseWebPage);
        softAssertions.assertThat(TestData.privatBankSaleCourse).isEqualTo(TestData.privatBankSaleCourseWebPage);
        softAssertions.assertAll();
    }


}
