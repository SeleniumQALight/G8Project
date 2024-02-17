package apiTests;

import apiPrivatbank.ApiHelperPrivatBank;
import apiPrivatbank.dto.responsePrivatBank.ExchangeDTO;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.text.DecimalFormat;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiTestsPrivatbank {

    Logger logger = Logger.getLogger(getClass());
    ApiHelperPrivatBank apiHelper = new ApiHelperPrivatBank();

    final String DATE = "22.03.2022";

    @Test
    public void getExchangeRateByDate() {


        logger.info("Get exchange rate by date");

        Response actualResponse = apiHelper.getExchangeRateByDate(DATE).extract().response();


        SoftAssertions softAssertions = new SoftAssertions();
        ExchangeDTO actualResponseAsDto = actualResponse.as(ExchangeDTO.class);
        softAssertions
                .assertThat(actualResponseAsDto)
                .usingRecursiveComparison()
                .ignoringFields("saleRateNB", "purchaseRateNB", "saleRate", "purchaseRate ");
        softAssertions.assertThat(actualResponseAsDto.getDate()).isEqualTo(DATE);
        softAssertions.assertThat(actualResponseAsDto.getBank()).isEqualTo("PB");
        softAssertions.assertThat(actualResponseAsDto.getBaseCurrency()).isEqualTo("980");
        softAssertions.assertThat(actualResponseAsDto.getBaseCurrencyLit()).isEqualTo("UAH");
        softAssertions.assertThat(actualResponseAsDto.getExchangeRate().length).isEqualTo(25);

        for (int i = 0; i < actualResponseAsDto.getExchangeRate().length; i++) {
            softAssertions.assertThat(actualResponseAsDto.getExchangeRate()[i].getBaseCurrency()).isEqualTo("UAH");
            softAssertions.assertThat(actualResponseAsDto.getExchangeRate()[i].getCurrency()).isIn("AUD", "AZN", "BYN", "CAD", "CHF", "CZK", "EUR", "GEL", "ILS", "KZT",
                    "NOK", "UAH", "SEK", "TMT", "UZS", "USD", "CNY", "DKK", "HUF", "GBP", "JPY", "MDL", "PLN", "SGD", "TRY");
        }
        softAssertions.assertAll();
    }


    @Test
    public void validateJsonSchema() {
        logger.info("Validate JSON schema");
        apiHelper.getExchangeRateByDate(DATE).assertThat().body(matchesJsonSchemaInClasspath("privatbank_response.json"));
    }

    @Test
    public void validateKursMoreThanZero() {
        logger.info("Validate kurs more than zero");
        Response actualResponse = apiHelper.getExchangeRateByDate(DATE).extract().response();
        ExchangeDTO actualResponseAsDto = actualResponse.as(ExchangeDTO.class);

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < actualResponseAsDto.getExchangeRate().length; i++) {
            softAssertions.assertThat(Float.parseFloat(actualResponseAsDto.getExchangeRate()[i].getSaleRateNB().toString())).isGreaterThan(0);
            softAssertions.assertThat(Float.parseFloat(actualResponseAsDto.getExchangeRate()[i].getPurchaseRateNB().toString())).isGreaterThan(0);
            if (actualResponseAsDto.getExchangeRate()[i].getSaleRate() != null) {
                softAssertions.assertThat(Float.parseFloat(actualResponseAsDto.getExchangeRate()[i].getSaleRate().toString())).isGreaterThan(0);
            }
            if (actualResponseAsDto.getExchangeRate()[i].getPurchaseRate() != null) {
                softAssertions.assertThat(Float.parseFloat(actualResponseAsDto.getExchangeRate()[i].getPurchaseRate().toString())).isGreaterThan(0);
            }
        }
        softAssertions.assertAll();
    }


}
