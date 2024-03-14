package bdd.stepDefinitions;

import api.ApiHelperPB;
import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import libs.Util;
import org.assertj.core.api.SoftAssertions;


public class PBSteps extends MainSteps {


    public PBSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I open page PB")
    public void iOpenPagePB() {
        pageProvider.getPBPage().openPBpage();
        Util.waitABit(1);
        pageProvider.getPBPage().checkIsTitleExchangeRatesVisible();
        Util.waitABit(1);
    }


    @When("I getting a course {string} the site")
    public void iGettingACourseTheSite(String currency) {
        pageProvider.getPBPage().getExchangeRateBuyOnTheWeb1(currency);
        pageProvider.getPBPage().getExchangeRateSaleOnTheWeb(currency);
    }

    @And("I getting a course {string} via API")
    public void iGettingACourseViaAPI(String currency) {
        ApiHelperPB.getCurseViaApi(currency);

    }

    @Then("I compare course {string} via API and site")
    public void i_compare_course_via_api_and_site(String currency) {
        double exchangeRateBuyDouble = Double.parseDouble(TestData.exchangeRateBuy);
        double exchangeRateSaleDouble = Double.parseDouble(TestData.exchangeRateSale);
        double cursViaApiSaleDouble = Double.parseDouble(TestData.cursViaApiSale);
        double cursViaApiBuiDouble = Double.parseDouble(TestData.cursViaApiBui);
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(exchangeRateBuyDouble)
                .as("Курс продажу валюти " + currency + " на сайті і отриманого через API не співпадають")
                .isEqualTo(cursViaApiBuiDouble);

        softAssertions.assertThat(exchangeRateSaleDouble)
                .as("Курс купівлі валюти " + currency + " на сайті і отриманого через API не співпадають")
                .isEqualTo(cursViaApiSaleDouble);

        softAssertions.assertAll();
    }
}