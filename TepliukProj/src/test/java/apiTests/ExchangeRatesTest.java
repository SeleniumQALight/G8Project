package apiTests;

import api.PrivatApiHelper;
import api.dto.responseDto.ExchangeRatesGeneralDataDto;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ExchangeRatesTest {
    final String DATE = "22.03.2022";
    final String[] CURRENCIES = {"AUD", "AZN", "BYN", "CAD", "CHF", "CNY", "CZK", "DKK", "EUR", "GBP", "GEL", "HUF", "ILS", "JPY", "KZT", "MDL", "NOK", "PLN", "SEK", "SGD", "TMT", "TRY", "UAH", "USD", "UZS"};
    final String BASE_CURRENCY = "UAH";

    PrivatApiHelper privateApiHelper = new PrivatApiHelper();
    @Test
    public void getAllExchangeRatesTest() {
        Response actualResponse = privateApiHelper.getAllExchangeRatesRequest(DATE).extract().response();

        ExchangeRatesGeneralDataDto actualResponseAsDto = actualResponse.as(ExchangeRatesGeneralDataDto.class);

        SoftAssertions softAssertions = new SoftAssertions();

        ExchangeRatesGeneralDataDto expectedGeneralDataDto = ExchangeRatesGeneralDataDto.builder().date(DATE).bank("PB").baseCurrency(980).baseCurrencyLit(BASE_CURRENCY).build();

        softAssertions
                .assertThat(actualResponseAsDto)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate")
                .isEqualTo(expectedGeneralDataDto);

        for (int i = 0; i < actualResponseAsDto.getExchangeRate().length; i++) {
            softAssertions.assertThat(actualResponseAsDto.getExchangeRate()[i].getBaseCurrency()).contains(BASE_CURRENCY);
            softAssertions.assertThat(actualResponseAsDto.getExchangeRate()[i].getCurrency()).isIn(CURRENCIES);
        }

        softAssertions.assertAll();
    }

    @Test
    public void getAllExchangeRatesSchema() {
        privateApiHelper.getAllExchangeRatesRequest(DATE)
                .assertThat().body(matchesJsonSchemaInClasspath("pb_response.json"));
    }

    @Test
    public void verifyExchangeRatesAreNotNegative() {
        Response actualResponse = privateApiHelper.getAllExchangeRatesRequest(DATE).extract().response();

        ExchangeRatesGeneralDataDto actualResponseAsDto = actualResponse.as(ExchangeRatesGeneralDataDto.class);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < actualResponseAsDto.getExchangeRate().length; i++) {
            softAssertions.assertThat(actualResponseAsDto.getExchangeRate()[i].getSaleRateNB()).isGreaterThan(0);
            softAssertions.assertThat(actualResponseAsDto.getExchangeRate()[i].getPurchaseRateNB()).isGreaterThan(0);
            if (actualResponseAsDto.getExchangeRate()[i].getSaleRate() != null || actualResponseAsDto.getExchangeRate()[i].getPurchaseRate() != null) {
                softAssertions.assertThat(actualResponseAsDto.getExchangeRate()[i].getSaleRate()).isGreaterThan(0);
                softAssertions.assertThat(actualResponseAsDto.getExchangeRate()[i].getPurchaseRate()).isGreaterThan(0);
            }
        }
        softAssertions.assertAll();
    }
}