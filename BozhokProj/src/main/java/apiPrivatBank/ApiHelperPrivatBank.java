package apiPrivatBank;

import apiPrivatBank.PBDto.ExchangeRateDTO;
import apiPrivatBank.PBDto.ExchangeRatesByCourseDTO;
import data.TestData;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;

public class ApiHelperPrivatBank {
    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .build();

    public ValidatableResponse getExchangeRatePBRequest(String date, int statusCode) {
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPointsPrivatBank.BASE_URL_DATE, date)
                .then()
                .spec(responseSpecification.statusCode(statusCode));
    }

    public ValidatableResponse getExchangeRatePBRequest(String date) {
        return getExchangeRatePBRequest(date, HttpStatus.SC_OK);
    }

    public ValidatableResponse getExchangeRatePBByCurrency() {
        Response actualResponse = given()
                .spec(requestSpecification)
                .when()
                .get(EndPointsPrivatBank.BASE_URL_CURRENCY)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_OK))
                .extract()
                .response();
        return actualResponse.then();
    }

    public void getExchangeRatePBByCurrency(String currency) {
        ExchangeRatesByCourseDTO[] listOfExchangeRate = getExchangeRatePBByCurrency().extract().response().as(ExchangeRatesByCourseDTO[].class);
        for (int i = 0; i < listOfExchangeRate.length; i++) {
            if (listOfExchangeRate[i].getCcy().equals(currency)) {
                TestData.EXCHANGE_RATES_BUY_API = Double.parseDouble(listOfExchangeRate[i].getBuy());
                TestData.EXCHANGE_RATES_SELL_API = Double.parseDouble(listOfExchangeRate[i].getSale());
            } else {
                logger.info("Currency: " + listOfExchangeRate[i].getCcy() + " Sale rate: " + listOfExchangeRate[i].getSale() + " Purchase rate: " + listOfExchangeRate[i].getBuy());
            }
        }
    }
}
