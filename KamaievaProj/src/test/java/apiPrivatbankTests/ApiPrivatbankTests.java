package apiPrivatbankTests;

import apiPrivatbank.ApiPrivatbankHelper;
import apiPrivatbank.dto.responseDto.CurrencyDTO;
import apiPrivatbank.dto.responseDto.ExchangeRatesDTO;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiPrivatbankTests {

    ApiPrivatbankHelper apiPrivatbankHelper = new ApiPrivatbankHelper();

    final String DATE = "22.03.2022";
    final String BASE_CURRENCY_LIT = "UAH";
    final Integer BASE_CURRENCY_CODE = 980;
    final String BANK = "PB";
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
    public void getExchangeRateByDateTest() {
        CurrencyDTO actualResponseAsDto = apiPrivatbankHelper
                .getExchangeRateByDate(DATE)
                .extract()
                .response().as(CurrencyDTO.class);

        CurrencyDTO expectedResponseDto = CurrencyDTO.builder()
                .date(DATE)
                .bank(BANK)
                .baseCurrency(BASE_CURRENCY_CODE)
                .baseCurrencyLit(BASE_CURRENCY_LIT)
                .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.
                assertThat(actualResponseAsDto)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate")
                .isEqualTo(expectedResponseDto);

        for (int i = 0; i < actualResponseAsDto.getExchangeRate().length; i++) {
            softAssertions.assertThat(actualResponseAsDto.getExchangeRate()[i].getCurrency()).isEqualTo(CURRENCIES[i]);
        }

        softAssertions.assertAll();
    }

    @Test
    public void getExchangeRateByDateSchemaTest() {
        apiPrivatbankHelper
                .getExchangeRateByDate(DATE)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("privatbankResponse.json"));
    }

    @Test
    public void validateCurrencyRatesGreaterThanZero() {
        CurrencyDTO actualResponseAsDto = apiPrivatbankHelper
                .getExchangeRateByDate(DATE)
                .extract()
                .response().as(CurrencyDTO.class);

        List<ExchangeRatesDTO> exchangeRatesList = Arrays.asList(actualResponseAsDto.getExchangeRate());

        SoftAssertions softAssertions = new SoftAssertions();

        exchangeRatesList.forEach(rate -> {

            softAssertions.assertThat(rate.getSaleRateNB()).isGreaterThan(0);
            softAssertions.assertThat(rate.getPurchaseRateNB()).isGreaterThan(0);

            if (rate.getSaleRate() != null) {
                softAssertions.assertThat(rate.getSaleRate()).isGreaterThan(0);
            }
            if (rate.getPurchaseRate() != null) {
                softAssertions.assertThat(rate.getPurchaseRate()).isGreaterThan(0);
            }
        });
        softAssertions.assertAll();
    }
}
