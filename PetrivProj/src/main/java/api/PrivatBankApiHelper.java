package api;

import api.dto.responseDto.CurrencyRateDto;
import data.TestData;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class PrivatBankApiHelper extends ApiHelper {
    private final String USD = "USD";
    private final String EUR = "EUR";

    Double eurBuyRateApi;
    Double eurSaleRateApi;
    Double usdBuyRateApi;
    Double usdSaleRateApi;

    public ValidatableResponse getAllExchangeRatesRequest(String date) {
        return given()
                .spec(requestSpecification)
                .queryParam("date", date)
                .when()
                .get(PrivatBankEndPoints.BASE_URL + PrivatBankEndPoints.EXCHANGE_RATES)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_OK));
    }

    public ValidatableResponse getCurrenciesRates() {
        return given()
                .spec(requestSpecification)
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", 5)
                .when()
                .get(PrivatBankEndPoints.BASE_URL + PrivatBankEndPoints.PUB_INFO)
                .then()
                .spec(responseSpecification);
    }

    public void getRateForCurrency(String currencyName) {
        CurrencyRateDto[] responseAsDto = getCurrenciesRates().extract().body().as(CurrencyRateDto[].class);

        for (int i = 0; i < responseAsDto.length; i++) {
            if (USD.equalsIgnoreCase(currencyName) && responseAsDto[i].getCcy().equalsIgnoreCase(USD)) {
                usdBuyRateApi = responseAsDto[i].getBuy();
                usdSaleRateApi = responseAsDto[i].getSale();
            }
            if (EUR.equalsIgnoreCase(currencyName) && responseAsDto[i].getCcy().equalsIgnoreCase(EUR)) {
                eurBuyRateApi = responseAsDto[i].getBuy();
                eurSaleRateApi = responseAsDto[i].getSale();
            }
        }
    }

    public void checkUiAndApiCurrenciesRates(String currencyName) {
        if (USD.equalsIgnoreCase(currencyName)) {
            Assert.assertEquals(currencyName + " rate is different ", usdBuyRateApi, TestData.BUY_RATE_UI);
            Assert.assertEquals(currencyName + " rate is different ", usdSaleRateApi, TestData.SALE_RATE_UI);
        }
        if (EUR.equalsIgnoreCase(currencyName)) {
            Assert.assertEquals(currencyName + " rate is different ", eurBuyRateApi, TestData.BUY_RATE_UI);
            Assert.assertEquals(currencyName + " rate is different ", eurSaleRateApi, TestData.SALE_RATE_UI);
        }
    }
}
