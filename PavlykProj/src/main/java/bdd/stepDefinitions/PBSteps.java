package bdd.stepDefinitions;

import apiPrivatBank.ApiHelperPrivatBank;
import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import pages.PBPage;

public class PBSteps extends MainSteps {

    public PBSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I open page PB")
    public void iOpenPagePB() {
        pageProvider.getPBPage().openPBPage();
        pageProvider.getPBPage().checkIsExchangeRatesBlockVisible();
    }

    @When("I getting a course {string} the site")
    public void iGettingACourseTheSite(String currency) {
        pageProvider.getPBPage().getExchangeRateBuy(currency);
        pageProvider.getPBPage().getExchangeRateSale(currency);
    }

    @When("I getting a course {string} via API")
    public void iGettingACourseViaAPI(String currency) {
        ApiHelperPrivatBank.saveExchangeRate(currency);
    }

    @Then("I compare course {string} via API and site")
    public void i_compare_course_via_api_and_site(String currency) {
        double buyRateUI = PBPage.buyRateUI;
        double saleRateUI = PBPage.saleRateUI;
        double buyRateApi = ApiHelperPrivatBank.buyRateApi;
        double saleRateApi = ApiHelperPrivatBank.saleRateApi;
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(buyRateUI)
                .as("Курсы продажи валют " + currency + " не совпадают")
                .isEqualTo(buyRateApi);
        softAssertions.assertThat(saleRateUI)
                .as("Курсы покупки валют " + currency + " не совпадают")
                .isEqualTo(saleRateApi);
        softAssertions.assertAll();
    }
}