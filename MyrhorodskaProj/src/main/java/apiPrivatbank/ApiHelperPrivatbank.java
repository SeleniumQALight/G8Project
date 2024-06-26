package apiPrivatbank;

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

public class ApiHelperPrivatbank {
    Logger logger = Logger.getLogger(ApiHelperPrivatbank.class);
    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public ValidatableResponse getExchangeRateByDate(String date, int statusCode) {
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPointsPrivatbank.EXCHANGE_BY_DATE, date)
                .then()
                .spec(responseSpecification.statusCode(statusCode));
    }

    public ValidatableResponse getExchangeRateByDate(String date) {
        return getExchangeRateByDate(date, HttpStatus.SC_OK);
    }
}
