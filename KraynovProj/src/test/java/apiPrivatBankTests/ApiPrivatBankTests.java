package apiPrivatBankTests;

import apiPrivatBank.ApiHelperPrivatBank;
import apiPrivatBank.responsePrivatBankDto.ExchangeDto;
import apiPrivatBank.responsePrivatBankDto.ExchangeRateDto;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiPrivatBankTests {

    ApiHelperPrivatBank apiHelper = new ApiHelperPrivatBank();

    final String DATE = "22.03.2022";
    String[] CURRENCY = {
            "AUD",
            "AZN",
            "BYN",
            "CAD",
            "CHF",
            "CZK",
            "EUR",
            "GEL",
            "ILS",
            "KZT",
            "NOK",
            "UAH",
            "SEK",
            "TMT",
            "UZS",
            "USD",
            "CNY",
            "DKK",
            "HUF",
            "GBP",
            "JPY",
            "MDL",
            "PLN",
            "SGD",
            "TRY"
    };

    @Test
    public void ExchangeRatesValidation() {
        Response actualResponse = apiHelper.getExchangeRateByDate(DATE).extract().response();

        SoftAssertions softAssertions = new SoftAssertions();
        ExchangeDto actualResponseAsDto = actualResponse.as(ExchangeDto.class);
        softAssertions
                .assertThat(actualResponseAsDto)
                .usingRecursiveComparison()
                .ignoringFields("saleRateNB", "purchaseRateNB", "saleRate", "purchaseRate ");
        softAssertions.assertThat(actualResponseAsDto.getDate()).isEqualTo(DATE);
        softAssertions.assertThat(actualResponseAsDto.getBank()).isEqualTo("PB");
        softAssertions.assertThat(actualResponseAsDto.getBaseCurrency()).isEqualTo("980");
        softAssertions.assertThat(actualResponseAsDto.getBaseCurrencyLit()).isEqualTo("UAH");

        for (int i = 0; i < actualResponseAsDto.getExchangeRate().length; i++) {
            softAssertions.assertThat(actualResponseAsDto.getExchangeRate()[i].getBaseCurrency()).isEqualTo("UAH");
            softAssertions.assertThat(actualResponseAsDto.getExchangeRate()[i].getCurrency()).isIn(CURRENCY);
        }
        softAssertions.assertAll();
    }

    @Test
    public void jsonSchemaValidation() {
        apiHelper.getExchangeRateByDate(DATE)
                .assertThat().body(matchesJsonSchemaInClasspath("responsePrivatBank.json"));
    }

    @Test
    public void checkExchangeRateGreaterZero () {
        Response actualResponse = apiHelper.getExchangeRateByDate(DATE).extract().response();
        ExchangeDto actualResponseAsDto = actualResponse.as(ExchangeDto.class);

        SoftAssertions softAssertions = new SoftAssertions();
        for (ExchangeRateDto exchangeRate : actualResponseAsDto.getExchangeRate()) {
            if (exchangeRate.getSaleRateNB() != null) {
                softAssertions.assertThat(exchangeRate.getSaleRateNB()).isGreaterThan("0");
            }
            if (exchangeRate.getPurchaseRateNB() != null) {
                softAssertions.assertThat(exchangeRate.getPurchaseRateNB()).isGreaterThan("0");
            }
            if (exchangeRate.getSaleRate() != null) {
                softAssertions.assertThat(exchangeRate.getSaleRate()).isGreaterThan("0");
            }
            if (exchangeRate.getPurchaseRate() != null) {
                softAssertions.assertThat(exchangeRate.getPurchaseRate()).isGreaterThan("0");
            }
        }
        softAssertions.assertAll();
    }
}