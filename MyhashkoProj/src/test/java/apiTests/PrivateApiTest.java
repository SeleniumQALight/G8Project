package apiTests;

import api.ApiHelperPrivat;
import api.dto.privat.ExchangeDTO;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class PrivateApiTest {

    Logger logger = Logger.getLogger(getClass());
    ApiHelperPrivat apiHelperPrivat = new ApiHelperPrivat();

    final String DATE = "22.03.2022";

    @Test
    public void validationFieldsOfExchangeRateByDateTest() {

        SoftAssertions softAssertions = new SoftAssertions();

        Response response = apiHelperPrivat.getExchangeRateByDate(DATE).extract().response();
        ExchangeDTO exchangeDTO = response.as(ExchangeDTO.class);

        softAssertions.assertThat(exchangeDTO).usingRecursiveComparison()
                .ignoringFields("saleRateNB", "purchaseRateNB", "saleRate", "purchaseRate ").isEqualTo(exchangeDTO);
        softAssertions.assertThat(exchangeDTO.getDate()).isEqualTo(DATE);
        softAssertions.assertThat(exchangeDTO.getBank()).isEqualTo("PB");
        softAssertions.assertThat(exchangeDTO.getBaseCurrency()).isEqualTo("980");
        softAssertions.assertThat(exchangeDTO.getBaseCurrencyLit()).isEqualTo("UAH");

        if (exchangeDTO.getExchangeRate().length > 0) {
            for (int i = 0; i < exchangeDTO.getExchangeRate().length; i++) {
                softAssertions.assertThat(exchangeDTO.getExchangeRate()[i].getBaseCurrency()).isEqualTo("UAH");
                softAssertions.assertThat(exchangeDTO.getExchangeRate()[i].getCurrency()).isIn("AUD", "AZN", "BYN",
                        "CAD", "CHF", "CZK", "EUR", "GEL", "ILS", "KZT", "NOK", "UAH", "SEK", "TMT", "UZS", "USD",
                        "CNY", "DKK", "HUF", "GBP", "JPY", "MDL", "PLN", "SGD", "TRY");
            }
            softAssertions.assertAll();
        } else {
            logger.error("Exchange rate is empty");
        }
    }

    @Test
    public void validateSchemeOfPrivateResponse() {
        apiHelperPrivat.getExchangeRateByDate(DATE).assertThat().body(matchesJsonSchemaInClasspath("responsePrivate.json"));
    }

    @Test
    public void validateThatExchangeRateIsGreaterThanZero() {

        SoftAssertions softAssertions = new SoftAssertions();

        Response response = apiHelperPrivat.getExchangeRateByDate(DATE).extract().response();
        ExchangeDTO exchangeDTO = response.as(ExchangeDTO.class);

        for (int i = 0; i < exchangeDTO.getExchangeRate().length; i++) {
            softAssertions.assertThat(exchangeDTO.getExchangeRate()[i].getSaleRateNB()).isGreaterThan("0");
            softAssertions.assertThat(exchangeDTO.getExchangeRate()[i].getPurchaseRateNB()).isGreaterThan("0");
        }
        softAssertions.assertAll();
    }
}