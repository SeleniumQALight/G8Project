package privatBankTests;

import api.privatBank.PrivatBankApiHelper;
import api.privatBank.PrivatEndPoints;
import api.privatBank.privatDto.ArchiveRatesDTO;
import api.privatBank.privatDto.ExchangeRateDTO;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PrivatBankTests {
    String date = "22.03.2022";
    String bank = "PB";
    Integer baseCurrency = 980;
    String baseCurrencyLit = "UAH";
    PrivatBankApiHelper privatBankApiHelper = new PrivatBankApiHelper();

    @Test
    public void getExchangeRatesFromArchiveTest() {
        ArchiveRatesDTO actualArchiveRates =
                given()
                        .contentType(ContentType.JSON)
                        .queryParam("date", date)
                        .log().all()
                        .when()
                        .get(PrivatEndPoints.PRIVAT_GET_EXCHANGE_RATES)
                        .then()
                        .log().all()
                        .statusCode(HttpStatus.SC_OK)
                        .extract().body().as(ArchiveRatesDTO.class)
                ;

        String[] currenciesArray = {"AUD", "AZN", "BYN", "CAD", "CHF", "CNY", "CZK", "DKK", "EUR", "GBP", "GEL"
                , "HUF", "ILS", "JPY", "KZT", "MDL", "NOK", "PLN", "SEK", "SGD", "TMT", "TRY", "UAH", "USD", "UZS"};
        ExchangeRateDTO[] expectedExchangeRateDTOs = new ExchangeRateDTO[25];
        for (int i = 0; i < expectedExchangeRateDTOs.length; i++) {
            expectedExchangeRateDTOs[i] = new ExchangeRateDTO(currenciesArray[i]);
        }
        ArchiveRatesDTO expectedArchiveRatesDTO =
                ArchiveRatesDTO.builder()
                        .date(date)
                        .bank(bank)
                        .baseCurrency(baseCurrency)
                        .baseCurrencyLit(baseCurrencyLit)
                        .exchangeRate(expectedExchangeRateDTOs)
                        .build();

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actualArchiveRates)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB", "exchangeRate.saleRate", "exchangeRate.purchaseRate")
                .isEqualTo(expectedArchiveRatesDTO);
        softAssertions.assertAll();
    }

    @Test
    public void checkArchiveExchangeRatesSchema(){
        privatBankApiHelper.getValidatableResponseWithExchangeRatesArchive(date)
                .assertThat().body(matchesJsonSchemaInClasspath("privatBankResponse.json"));
    }

}
