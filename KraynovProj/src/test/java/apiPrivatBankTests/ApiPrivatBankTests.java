package apiPrivatBankTests;

import apiPrivatBank.ApiHelperPrivatBank;
import apiPrivatBank.responsePrivatBankDto.ExchangeDto;
import apiPrivatBank.responsePrivatBankDto.ExchangeRateDto;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
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
    public void ExchangeRatesValidation() {
        ExchangeDto actualResponseAsDto = apiHelper.getExchangeRateByDate(DATE).extract().response().as(ExchangeDto.class);

        ExchangeRateDto[] exchangeRateDTOCurrencyMassive = new ExchangeRateDto[CURRENCY.length];
        for (int i = 0; i < CURRENCY.length; i++) {
            exchangeRateDTOCurrencyMassive[i] = ExchangeRateDto.createExchangeRateWithSpecificCurrency(CURRENCY[i]);
        }

        ExchangeDto expectedResponse =
                ExchangeDto.builder()
                        .date(DATE)
                        .bank("PB")
                        .baseCurrency(980)
                        .baseCurrencyLit("UAH")
                        .exchangeRate(exchangeRateDTOCurrencyMassive)
                        .build();

        SoftAssertions softAssertions = new SoftAssertions();

        Assert.assertEquals("The number of exchange rates is different ", expectedResponse.getExchangeRate().length, actualResponseAsDto.getExchangeRate().length);
        softAssertions
                .assertThat(actualResponseAsDto)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB", "exchangeRate.saleRate", "exchangeRate.purchaseRate")
                .isEqualTo(expectedResponse);
        softAssertions.assertAll();
        softAssertions.assertThat(actualResponseAsDto.getDate()).isEqualTo(DATE);
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