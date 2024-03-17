package api;

import data.TestData;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
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
            .addFilter(new AllureRestAssured())
            .log(LogDetail.ALL)
            .build();
    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public ValidatableResponse getExchangeRate() {
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndpointsPrivatBank.EXCHANGE_RATE)
                .then()
                .spec(responseSpecification);
    }
    //save exchange rate by currency for future comparison (usd, eur) to testData

    public void saveExchangeRateByCurrency(String currency) {
        ValidatableResponse response = given()
                .spec(requestSpecification)
                .when()
                .get(EndpointsPrivatBank.EXCHANGE_RATE)
                .then()
                .spec(responseSpecification);
        response.log().all();
        response.extract().response().jsonPath().setRoot("exchangeRate");
        TestData.EXCHANGE_BUY_API = response.extract().response().jsonPath().get("find {it.currency == '" + currency + "'}.purchaseRate");
        TestData.EXCHANGE_SELL_API = response.extract().response().jsonPath().get("find {it.currency == '" + currency + "'}.saleRate");
    }


}
