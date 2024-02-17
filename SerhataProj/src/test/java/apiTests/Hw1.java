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

    @Test
    public void getExchangeRateByDate() {

        Response actualResponse = apiHelper.getExchangeRateByDate("29.03.2023")
                .extract().response();

        MainExchangeDataDto actualResponseAsDto = actualResponse.as(MainExchangeDataDto.class);

        MainExchangeDataDto expectedDto=
                MainExchangeDataDto.builder()
                        .date("29.03.2023")
                        .bank("PB")
                        .baseCurrency(980)
                        .baseCurrencyLit("UAH")
                        .exchangeRate(new ExchangeRateDto[]{
                                ExchangeRateDto.builder().baseCurrency("UAH").currency("AUD").build(),
                                ExchangeRateDto.builder().baseCurrency("UAH").currency("AZN").build(),
                                ExchangeRateDto.builder().baseCurrency("UAH").currency("BYN").build(),
                                ExchangeRateDto.builder().baseCurrency("UAH").currency("CAD").build(),
                                ExchangeRateDto.builder().baseCurrency("UAH").currency("CHF").build(),
                                ExchangeRateDto.builder().baseCurrency("UAH").currency("CNY").build(),
                                ExchangeRateDto.builder().baseCurrency("UAH").currency("CZK").build(),
                                ExchangeRateDto.builder().baseCurrency("UAH").currency("DKK").build(),
                                ExchangeRateDto.builder().baseCurrency("UAH").currency("EUR").build(),
                                ExchangeRateDto.builder().baseCurrency("UAH").currency("GBP").build(),
                                ExchangeRateDto.builder().baseCurrency("UAH").currency("GEL").build(),
                                ExchangeRateDto.builder().baseCurrency("UAH").currency("HUF").build(),
                                ExchangeRateDto.builder().baseCurrency("UAH").currency("ILS").build(),
                                ExchangeRateDto.builder().baseCurrency("UAH").currency("JPY").build(),
                                ExchangeRateDto.builder().baseCurrency("UAH").currency("KZT").build(),
                                ExchangeRateDto.builder().baseCurrency("UAH").currency("MDL").build(),
                                ExchangeRateDto.builder().baseCurrency("UAH").currency("NOK").build(),
                                ExchangeRateDto.builder().baseCurrency("UAH").currency("PLN").build(),
                                ExchangeRateDto.builder().baseCurrency("UAH").currency("SEK").build(),
                                ExchangeRateDto.builder().baseCurrency("UAH").currency("SGD").build(),
                                ExchangeRateDto.builder().baseCurrency("UAH").currency("TMT").build(),
                                ExchangeRateDto.builder().baseCurrency("UAH").currency("TRY").build(),
                                ExchangeRateDto.builder().baseCurrency("UAH").currency("UAH").build(),
                                ExchangeRateDto.builder().baseCurrency("UAH").currency("USD").build(),
                                ExchangeRateDto.builder().baseCurrency("UAH").currency("UZS").build(),
                                ExchangeRateDto.builder().baseCurrency("UAH").currency("XAU").build()
                        }).build();


        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualResponseAsDto)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB",
                        "exchangeRate.saleRate", "exchangeRate.purchaseRate")
                .isEqualTo(expectedDto);
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