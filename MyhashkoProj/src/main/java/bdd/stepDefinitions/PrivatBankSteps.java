package bdd.stepDefinitions;

import api.ApiHelperPrivatBankExam;
import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.assertj.core.api.SoftAssertions;

public class PrivatBankSteps extends MainSteps{
    public PrivatBankSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I save rate from Api for {}")
    public void iSaveCurrencyRateFromApi(String currency) {
        ApiHelperPrivatBankExam apiHelperPrivat = new ApiHelperPrivatBankExam();
        apiHelperPrivat.getCurrencyRateWhithApiInPrivatBank(currency);
    }

    @And("I save rate from Ui for {}")
    public void iSaveRateFromUiForCurrency(String currency) {
        pageProvider.getPrivatBankStartPage().openPrivatBankStartPage();
        pageProvider.getPrivatBankStartPage().getCurrencyRateWithUiInPrivatBank(currency);
    }

    @Then("I compare rates for {}")
    public void iCompareRatesForCurrency(String currency) {
        SoftAssertions softAssertions = new SoftAssertions();
        try { int compareCursBuy = Double.parseDouble(TestData.api_curs_buy) == Double.parseDouble(TestData.ui_curs_buy) ? 0 : 1;
            if (compareCursBuy == 0) {
                System.out.println("Curs buy by API " + Double.parseDouble(TestData.api_curs_buy) + " is equal to Curs buy by UI " + Double.parseDouble(TestData.ui_curs_buy));
            } else {
                System.out.println("Curs buy by API " + Double.parseDouble(TestData.api_curs_buy) + " is not equal to Curs buy by UI " + Double.parseDouble(TestData.ui_curs_buy));
                softAssertions.assertThat(Double.parseDouble(TestData.api_curs_buy)).isEqualTo(Double.parseDouble(TestData.ui_curs_buy));
            }} catch (Exception NullPointerException) {
            System.out.println("No buy rate by API or UI for " + currency + " in PrivatBank");
            softAssertions.assertThat(false).isTrue();
            assert false;
        }
        try { int compareCursSale = Double.parseDouble(TestData.api_curs_sale) == Double.parseDouble(TestData.ui_curs_sale) ? 0 : 1;
            if (compareCursSale == 0) {
                System.out.println("Curs sale by API " + Double.parseDouble(TestData.api_curs_sale) + " is equal to Curs sale by UI " + Double.parseDouble(TestData.ui_curs_sale));
            } else {
                System.out.println("Curs sale by API " + Double.parseDouble(TestData.api_curs_sale) + " is not equal to Curs sale by UI " + Double.parseDouble(TestData.ui_curs_sale));
                softAssertions.assertThat(Double.parseDouble(TestData.api_curs_sale)).isEqualTo(Double.parseDouble(TestData.ui_curs_sale));
            }} catch (Exception NullPointerException) {
            System.out.println("No sale rate by API or UI for " + currency + " in PrivatBank");
            softAssertions.assertThat(false).isTrue();
        }
        softAssertions.assertAll();
    }
}
