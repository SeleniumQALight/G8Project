package apiTests;

import apiPrivatBank.ApiHelperPrivatBank;
import apiPrivatBank.dto.responsePrivatBankDTO.ExchangeDTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiPrivatTests {

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
    public void ValidationExchangeRates(){
        ExchangeDTO actualResponse = apiHelper.getExchangesRatesByDate(DATE).extract().response().as(ExchangeDTO.class);
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actualResponse.getDate()).isEqualTo(DATE);
        softAssertions.assertThat(actualResponse.getBank()).isEqualTo("PB");
        softAssertions.assertThat(actualResponse.getBaseCurrency()).isEqualTo("980");
        softAssertions.assertThat(actualResponse.getBaseCurrencyLit()).isEqualTo("UAH");

        for (int i = 0; i <  actualResponse.getExchangeRate().length; i++) {
            softAssertions.assertThat(actualResponse.getExchangeRate()[i].getBaseCurrency()).isEqualTo("UAH");
            softAssertions.assertThat(actualResponse.getExchangeRate()[i].getCurrency()).isIn(CURRENCY);
        }

        softAssertions.assertAll();
        }

        @Test
    public void exchangesRatesByDateSchema(){
        apiHelper.getExchangesRatesByDate(DATE)
                .assertThat().body(matchesJsonSchemaInClasspath("exchange_rates_privat.json"));
        }

        @Test
    public void validationExchangeRateGreaterZero (){
            ExchangeDTO actualResponse = apiHelper.getExchangesRatesByDate(DATE).extract().response().as(ExchangeDTO.class);
            SoftAssertions softAssertions = new SoftAssertions();

            for (int i = 0; i < actualResponse.getExchangeRate().length; i++) {
                softAssertions.assertThat(actualResponse.getExchangeRate()[i].getSaleRateNB()).isGreaterThan("0");;
                softAssertions.assertThat(actualResponse.getExchangeRate()[i].getPurchaseRateNB()).isGreaterThan("0");
                softAssertions.assertThat(actualResponse.getExchangeRate()[i].getSaleRate()).isGreaterThan("0");
                softAssertions.assertThat(actualResponse.getExchangeRate()[i].getPurchaseRate()).isGreaterThan("0");
                
            }
        }
        



}

