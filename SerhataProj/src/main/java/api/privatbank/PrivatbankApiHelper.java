package api.privatbank;

import api.ApiHelper;
import api.EndPoints;
import api.dto.PrivatbankDto.CurrencyRateDTO;
import data.TestData;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PrivatbankApiHelper extends ApiHelper {
    private final String USD = "USD";
    private final String EUR = "EUR";
    private Map<String, CurrencyRateDTO> currencyRatesMap = new HashMap<>();

    Double eurBuyRateApi;
    Double eurSaleRateApi;
    Double usdBuyRateApi;
    Double usdSaleRateApi;

        public ValidatableResponse getCurrenciesRates() {
            return given()
                    .spec(requestSpecification)
                    .queryParam("json")
                    .queryParam("exchange")
                    .queryParam("coursid", 5)
                    .when()
                    .get(EndPoints.BASE_URL_PRIVATBANK + EndPoints.PUB_INFO)
                    .then()
                    .spec(responseSpecification);
        }

    public void getRateForCurrency(String currencyName) {
        CurrencyRateDTO[] responseAsDto = getCurrenciesRates().extract().body().as(CurrencyRateDTO[].class);

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