package api.privatbank;

import api.ApiHelper;
import api.EndPoints;
import api.dto.PrivatbankDto.CurrencyRateDTO;
import data.TestData;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class PrivatbankApiHelper extends ApiHelper {
    private final String USD = "USD";
    private final String EUR = "EUR";

    Double buyRateApi;
    Double saleRateApi;


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
                buyRateApi = responseAsDto[i].getBuy();
                saleRateApi = responseAsDto[i].getSale();
            }
            if (EUR.equalsIgnoreCase(currencyName) && responseAsDto[i].getCcy().equalsIgnoreCase(EUR)) {
                buyRateApi = responseAsDto[i].getBuy();
                saleRateApi = responseAsDto[i].getSale();
            }
        }
    }

    public void checkUiAndApiCurrenciesRates(String currencyName) {
        if (USD.equalsIgnoreCase(currencyName)) {
            Assert.assertEquals(currencyName + " rate is different ", buyRateApi, TestData.BUY_RATE_UI);
            Assert.assertEquals(currencyName + " rate is different ", saleRateApi, TestData.SALE_RATE_UI);
        }
        if (EUR.equalsIgnoreCase(currencyName)) {
            Assert.assertEquals(currencyName + " rate is different ", buyRateApi, TestData.BUY_RATE_UI);
            Assert.assertEquals(currencyName + " rate is different ", saleRateApi, TestData.SALE_RATE_UI);
        }
    }
}