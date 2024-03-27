package apiTests;


import api.privat24.ApiHelperPrivat;
import api.privat24.responseDTO.ExchangeDTO;
import api.privat24.responseDTO.ExchangeRateDTO;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Privat24_Tests {

    final String DATE = "22.03.2022";
    ApiHelperPrivat apiHelperPrivat = new ApiHelperPrivat();
    Logger logger = Logger.getLogger(getClass());
    final String[] CURRENCIES = {"AUD",
            "AZN",
            "BYN",
            "CAD",
            "CHF",
            "CNY",
            "CZK",
            "DKK",
            "EUR",
            "GBP",
            "GEL",
            "HUF",
            "ILS",
            "JPY",
            "KZT",
            "MDL",
            "NOK",
            "PLN",
            "SEK",
            "SGD",
            "TMT",
            "TRY",
            "UAH",
            "USD",
            "UZS"
    };


    @Test
    public void validationFieldsOfExchangeRateByDateTestFullResponse() {
        ExchangeDTO actualResponseAsDto = apiHelperPrivat.getExchangeRateByDate(DATE).extract().as(
                ExchangeDTO.class);
        SoftAssertions softAssertions = new SoftAssertions();

        ExchangeDTO expectedResponse =
                ExchangeDTO.builder()
                        .date(DATE)
                        .bank("PB")
                        .baseCurrency("980")
                        .baseCurrencyLit("UAH")
                        .exchangeRate(new ExchangeRateDTO[]{

                                ExchangeRateDTO.builder().baseCurrency("UAH").currency("AUD").build(),
                                ExchangeRateDTO.builder().baseCurrency("UAH").currency("AZN").build(),
                                ExchangeRateDTO.builder().baseCurrency("UAH").currency("BYN").build(),
                                ExchangeRateDTO.builder().baseCurrency("UAH").currency("CAD").build(),
                                ExchangeRateDTO.builder().baseCurrency("UAH").currency("CHF").build(),
                                ExchangeRateDTO.builder().baseCurrency("UAH").currency("CNY").build(),
                                ExchangeRateDTO.builder().baseCurrency("UAH").currency("CZK").build(),
                                ExchangeRateDTO.builder().baseCurrency("UAH").currency("DKK").build(),
                                ExchangeRateDTO.builder().baseCurrency("UAH").currency("EUR").build(),
                                ExchangeRateDTO.builder().baseCurrency("UAH").currency("GBP").build(),
                                ExchangeRateDTO.builder().baseCurrency("UAH").currency("GEL").build(),
                                ExchangeRateDTO.builder().baseCurrency("UAH").currency("HUF").build(),
                                ExchangeRateDTO.builder().baseCurrency("UAH").currency("ILS").build(),
                                ExchangeRateDTO.builder().baseCurrency("UAH").currency("JPY").build(),
                                ExchangeRateDTO.builder().baseCurrency("UAH").currency("KZT").build(),
                                ExchangeRateDTO.builder().baseCurrency("UAH").currency("MDL").build(),
                                ExchangeRateDTO.builder().baseCurrency("UAH").currency("NOK").build(),
                                ExchangeRateDTO.builder().baseCurrency("UAH").currency("PLN").build(),
                                ExchangeRateDTO.builder().baseCurrency("UAH").currency("SEK").build(),
                                ExchangeRateDTO.builder().baseCurrency("UAH").currency("SGD").build(),
                                ExchangeRateDTO.builder().baseCurrency("UAH").currency("TMT").build(),
                                ExchangeRateDTO.builder().baseCurrency("UAH").currency("TRY").build(),
                                ExchangeRateDTO.builder().baseCurrency("UAH").currency("UAH").build(), //   UAH
                                ExchangeRateDTO.builder().baseCurrency("UAH").currency("USD").build(),
                                ExchangeRateDTO.builder().baseCurrency("UAH").currency("UZS").build()


                        })
                        .build();
        Assert.assertEquals("Number of posts ", expectedResponse.getExchangeRate().length, actualResponseAsDto.getExchangeRate().length);
        softAssertions.assertThat(actualResponseAsDto).usingRecursiveComparison()

                .ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB",
                        "exchangeRate.saleRate", "exchangeRate.purchaseRate")
                .isEqualTo(expectedResponse);
        softAssertions.assertAll();

    }


    @Test
    //інший варіант валідації
    public void validationFieldsOfExchangeRateByDateTest() {


        Response actualResponse = apiHelperPrivat.getExchangeRateByDate(DATE).extract().response();
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actualResponse.jsonPath().getString("date")).isEqualTo(DATE);
        softAssertions.assertThat(actualResponse.jsonPath().getString("bank")).isEqualTo("PB");
        softAssertions.assertThat(actualResponse.jsonPath().getString("baseCurrency")).isEqualTo("980");
        softAssertions.assertThat(actualResponse.jsonPath().getString("baseCurrencyLit")).isEqualTo("UAH");

        for (int i = 0; i < actualResponse.jsonPath().getList("exchangeRate").size(); i++) {
            softAssertions.assertThat(actualResponse.jsonPath().getString("exchangeRate[" + i + "].baseCurrency")).isEqualTo("UAH");
            softAssertions.assertThat(actualResponse.jsonPath().getString("exchangeRate[" + i + "].currency")).isEqualTo(CURRENCIES[i]);

            softAssertions.assertAll();

        }
    }

    @Test
    public void getExchangeRateByDateSchemaTest() {
        apiHelperPrivat.getExchangeRateByDate(DATE)
                .assertThat().body(matchesJsonSchemaInClasspath("privatbankResponse.json"));
    }


}
