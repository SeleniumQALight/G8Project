package apiTests;

import api_privat.ApiHelperPrivat;
import api_privat.responseDto.ExchangeDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiPrivatTests {
    ApiHelperPrivat apiHelper = new ApiHelperPrivat();

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
    public void getExchangesRatesByDate(){
        ExchangeDto actualResponse = apiHelper.getExchangesRatesByDate(DATE).extract().response().as(ExchangeDto.class);
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actualResponse.getDate()).isEqualTo(DATE);
        softAssertions.assertThat(actualResponse.getBank()).isEqualTo("PB");
        softAssertions.assertThat(actualResponse.getBaseCurrency()).isEqualTo("980");
        softAssertions.assertThat(actualResponse.getBaseCurrencyLit()).isEqualTo("UAH");

        for (int i = 0; i < actualResponse.getExchangeRate().length; i++) {
            softAssertions.assertThat(actualResponse.getExchangeRate()[i].getBaseCurrency()).isEqualTo("UAH");
            softAssertions.assertThat(actualResponse.getExchangeRate()[i].getCurrency()).isIn(CURRENCY);
        }

        softAssertions
                .assertThat(actualResponse)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB",
                        "exchangeRate.saleRate", "exchangeRate.purchaseRate");

        softAssertions.assertAll();
    }

    @Test
    public void exchangesRatesByDateSchema(){
        apiHelper.getExchangesRatesByDate(DATE)
                .assertThat().body(matchesJsonSchemaInClasspath("response_privat.json"));
    }

    @Test
    public void fieldsGreaterZeroValidate(){
        ExchangeDto actualResponse = apiHelper.getExchangesRatesByDate(DATE).extract().response().as(ExchangeDto.class);
        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < actualResponse.getExchangeRate().length; i++) {
            softAssertions.assertThat(actualResponse.getExchangeRate()[i].getSaleRateNB()).isGreaterThan("0");
            softAssertions.assertThat(actualResponse.getExchangeRate()[i].getPurchaseRateNB()).isGreaterThan("0");
            softAssertions.assertThat(actualResponse.getExchangeRate()[i].getSaleRate()).isGreaterThan("0");
            softAssertions.assertThat(actualResponse.getExchangeRate()[i].getPurchaseRate()).isGreaterThan("0");
        }
    }
}
