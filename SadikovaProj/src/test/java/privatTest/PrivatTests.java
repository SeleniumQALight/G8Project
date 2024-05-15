package privatTest;

import apiPrivat.ApiHelperPrivatBank;
import apiPrivat.EndPointsPrivatBank;
import apiPrivat.responsePrivatBankDTO.ExchangeDTO;
import apiPrivat.responsePrivatBankDTO.ExchangeRateDTO;

import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PrivatTests {

    ApiHelperPrivatBank apiHelperPrivatBank = new ApiHelperPrivatBank();
    final static String DATE = "22.03.2022";
    final static String BANK = "PB";
    final static Integer BASE_CURRENCY = 980;
    final static String BASE_CURRENCY_LIT = "UAH";

    Logger logger = Logger.getLogger(getClass());
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
    public void validation() {
        ExchangeDTO actualResponse = apiHelperPrivatBank.getExchangeRateByDate(DATE).extract().response().as(ExchangeDTO.class);
        ExchangeRateDTO[] massiveCurrency = new ExchangeRateDTO[25];
        for (int i = 0; i < 25; i++) {
            massiveCurrency[i] = ExchangeRateDTO.createExchangeRateWithDefCurrency(CURRENCY[i]);
        }
        ExchangeDTO expectedResponse =
                ExchangeDTO.builder()
                        .date(DATE)
                        .bank(BANK)
                        .baseCurrency(BASE_CURRENCY)
                        .baseCurrencyLit(BASE_CURRENCY_LIT)
                        .exchangeRate(massiveCurrency)
                        .build();

        SoftAssertions softAssertions = new SoftAssertions();
        Assert.assertEquals("test ", expectedResponse.getExchangeRate().length, actualResponse.getExchangeRate().length);
        softAssertions.assertThat(actualResponse)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB", "exchangeRate.saleRate", "exchangeRate.purchaseRate")
                .isEqualTo(expectedResponse);
        softAssertions.assertAll();

    }

    @Test
    public void schema() {
        apiHelperPrivatBank.getExchangeRateByDate(DATE)
                .assertThat().body(matchesJsonSchemaInClasspath("responsePrivat.json"));
    }

    @Test
    public void testExchangeRates() {
        ExchangeDTO actualResponse = apiHelperPrivatBank.getExchangeRateByDate(DATE).extract().response().as(ExchangeDTO.class);
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < actualResponse.getExchangeRate().length; i++) {
            if (actualResponse.getExchangeRate()[i].getSaleRateNB() != null) {
                softAssertions.assertThat(actualResponse.getExchangeRate()[i].getSaleRateNB()).isGreaterThan(0);
            } else {
                logger.info(actualResponse.getExchangeRate()[i].getSaleRateNB() + " Sale Rate NB is null");
            }
            if (actualResponse.getExchangeRate()[i].getPurchaseRateNB() != null) {
                softAssertions.assertThat(actualResponse.getExchangeRate()[i].getPurchaseRateNB()).isGreaterThan(0);
            } else {
                logger.info(actualResponse.getExchangeRate()[i].getPurchaseRateNB() + " Purchase Rate NB is null");
            }
            if (actualResponse.getExchangeRate()[i].getSaleRate() != null) {
                softAssertions.assertThat(actualResponse.getExchangeRate()[i].getSaleRate()).isGreaterThan(0);
                logger.info("Sale Rate Not null индекс " + i + " ,число " + actualResponse.getExchangeRate()[i].getSaleRate());
            } else {
                logger.warn("Sale Rate is null for index " + i);
            }
            if (actualResponse.getExchangeRate()[i].getPurchaseRate() != null) {
                softAssertions.assertThat(actualResponse.getExchangeRate()[i].getPurchaseRate()).isGreaterThan(0);
                logger.info("Purchase Rate Not null индекс " + i + " , число " + actualResponse.getExchangeRate()[i].getPurchaseRate());
            } else {
                logger.warn("Purchase Rate is null for index " + i);
            }

        }
        softAssertions.assertAll();


    }
}


