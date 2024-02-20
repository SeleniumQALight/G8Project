package apiTest;

import api.dto.responseDto.ExchangeDTO;
import apiPrivatbank.ApiHelperPrivatbank;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


    public class ApiPrivatbankTests {

        ApiHelperPrivatbank apiHelperPrivatbank = new ApiHelperPrivatbank();

        final String DATE = "22.03.2022";
        final String[] CURRENCY =
                {"AUD", "AZN", "BYN", "CAD", "CHF", "CNY", "CZK", "DKK", "EUR", "GBP", "GEL", "HUF", "ILS", "JPY", "KZT", "MDL", "NOK", "PLN", "SEK", "SGD", "TMT", "TRY", "UAH", "USD", "UZS"};
        @Test
        public void ValidationExchangeRates(){
            ExchangeDTO actualResponse = apiHelperPrivatbank.getExchangeRateByDate(DATE).extract().response().as(ExchangeDTO.class);
            SoftAssertions softAssertions = new SoftAssertions();

            softAssertions.assertThat(actualResponse.getDate()).isEqualTo(DATE);
            softAssertions.assertThat(actualResponse.getBaseCurrency()).isEqualTo("980");
            softAssertions.assertThat(actualResponse.getBank()).isEqualTo("PB");
            softAssertions.assertThat(actualResponse.getBaseCurrencyLit()).isEqualTo("UAH");

            for (int i = 0; i <  actualResponse.getExchangeRate().length; i++) {
                softAssertions.assertThat(actualResponse.getExchangeRate()[i].getBaseCurrency()).isEqualTo("UAH");
                softAssertions.assertThat(actualResponse.getExchangeRate()[i].getCurrency()).isIn(CURRENCY);
            }

            softAssertions.assertAll();
        }

        @Test
        public void exchangeRatesByDateSchema(){
            apiHelperPrivatbank.getExchangeRateByDate(DATE)
                    .assertThat().body(matchesJsonSchemaInClasspath("privatBankResponse.json"));
        }

        @Test
        public void validationExchangeRateGreaterZero (){
            ExchangeDTO actualResponse = apiHelperPrivatbank.getExchangeRateByDate(DATE).extract().response().as(ExchangeDTO.class);
            SoftAssertions softAssertions = new SoftAssertions();

            for (int i = 0; i < actualResponse.getExchangeRate().length; i++) {
                softAssertions.assertThat(actualResponse.getExchangeRate()[i].getPurchaseRateNB()).isGreaterThan("0");
                softAssertions.assertThat(actualResponse.getExchangeRate()[i].getSaleRateNB()).isGreaterThan("0");
                softAssertions.assertThat(actualResponse.getExchangeRate()[i].getPurchaseRate()).isGreaterThan("0");
                softAssertions.assertThat(actualResponse.getExchangeRate()[i].getSaleRate()).isGreaterThan("0");

            }
        }
    }

