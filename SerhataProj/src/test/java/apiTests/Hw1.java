package apiTests;

import api.ApiHelper;
import api.dto.responsePrivat.ExchangeRateDto;
import api.dto.responsePrivat.MainExchangeDataDto;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class Hw1 {

    Logger logger = Logger.getLogger(getClass());
    ApiHelper apiHelper = new ApiHelper();
    String[] CURRENCY = {
            "AUD",
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
            "UZS",
            "XAU"
    };

    @Test
    public void getExchangeRateByDate() {

        Response actualResponse = apiHelper.getExchangeRateByDate("29.03.2023")
                .extract().response();

        SoftAssertions softAssertions = new SoftAssertions();

        MainExchangeDataDto actualResponseAsDto = actualResponse.as(MainExchangeDataDto.class);

        softAssertions.assertThat(actualResponseAsDto)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB",
                        "exchangeRate.saleRate", "exchangeRate.purchaseRate");
                softAssertions.assertThat(actualResponseAsDto.getDate()).isEqualTo("29.03.2023");
                softAssertions.assertThat(actualResponseAsDto.getBank()).isEqualTo("PB");
                softAssertions.assertThat(actualResponseAsDto.getBaseCurrency()).isEqualTo(980);
                softAssertions.assertThat(actualResponseAsDto.getBaseCurrencyLit()).isEqualTo("UAH");

        for (int i = 0; i < actualResponseAsDto.getExchangeRate().length; i++) {
            softAssertions.assertThat(actualResponseAsDto.getExchangeRate()[i].getBaseCurrency()).isEqualTo("UAH");
            softAssertions.assertThat(actualResponseAsDto.getExchangeRate()[i].getCurrency()).isIn(CURRENCY);
        }

                softAssertions.assertAll();
    }

    @Test
    public void validateSchema() {
        apiHelper.getExchangeRateByDate("29.03.2023")
                .assertThat().body(matchesJsonSchemaInClasspath("hw1response.json"));
        }

    @Test
    public void checkThatAllCurrenciesValuesMoreThanNull() {
        Response actualResponse = apiHelper.getExchangeRateByDate("29.03.2023")
                .extract().response();
        SoftAssertions softAssertions = new SoftAssertions();

        List<Double> actualListOfSaleRateNB = actualResponse.jsonPath().getList("exchangeRate.saleRateNB", double.class);
        for (int i = 0; i < actualListOfSaleRateNB.size(); i++) {
            softAssertions.assertThat(actualListOfSaleRateNB.get(i)).as("Item number " + i).isGreaterThan(0);
        }

        List<Double> actualListOfPurchaseRateNB = actualResponse.jsonPath().getList("exchangeRate.purchaseRateNB", double.class);
        for (int i = 0; i < actualListOfPurchaseRateNB.size(); i++) {
            softAssertions.assertThat(actualListOfPurchaseRateNB.get(i)).as("Item number " + i).isGreaterThan(0);
        }

        List<Double> actualListOfSaleRate = actualResponse.jsonPath().getList("exchangeRate.saleRate", double.class);
        for (int i = 0; i < actualListOfSaleRate.size(); i++) {
            if (actualListOfSaleRate.get(i) != null) {
                softAssertions.assertThat(actualListOfSaleRate.get(i)).as("Item number " + i).isGreaterThan(0);
            }
        }

        List<Double> actualListOfPurchaseRate = actualResponse.jsonPath().getList("exchangeRate.purchaseRate", double.class);
        for (int i = 0; i < actualListOfPurchaseRate.size(); i++) {
            if (actualListOfPurchaseRate.get(i) != null) {
                softAssertions.assertThat(actualListOfPurchaseRate.get(i)).as("Item number " + i).isGreaterThan(0);
            }
        }

        softAssertions.assertAll();
    }

    }