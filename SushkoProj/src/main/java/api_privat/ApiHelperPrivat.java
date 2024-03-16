package api_privat;

import api_privat.responseDto.ExchangeRateMainDto;
import data.TestData;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class ApiHelperPrivat {
    double delta = 0.0001;

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public ValidatableResponse getExchangesRatesByDate(String date) {
        return given()
                .spec(requestSpecification)
                .when()
                .queryParam("date", date)
                .get(EndPointsPrivat.EXCHANGE_RATE_BASE_URL)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_OK));
    }

    public ValidatableResponse getExchangesRates() {
        return given()
                .spec(requestSpecification)
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", 5)
                .when()
                .get(EndPointsPrivat.PUB_INFO)
                .then()
                .spec(responseSpecification);
    }

    public void getExchangesRateForCurrency(String currency) {
        ExchangeRateMainDto[] response = getExchangesRates()
                .extract()
                .body()
                .as(ExchangeRateMainDto[].class);

        for (int i = 0; i < response.length; i++) {
            if (response[i].getCcy().equalsIgnoreCase(currency)) {
                TestData.buyRatePrivatAPI = response[i].getBuy();
                TestData.saleRatePrivatAPI = response[i].getSale();
            }
        }
    }

    public void compareExchangeRatesWithUiAndApi(String currency) {
        Assert.assertEquals("Buy rate is different for " + currency, TestData.buyRatePrivatAPI, TestData.buyRatePrivatUI, delta);
        Assert.assertEquals( "Sale rate is different for " + currency, TestData.saleRatePrivatAPI, TestData.saleRatePrivatUI, delta);
    }
}
