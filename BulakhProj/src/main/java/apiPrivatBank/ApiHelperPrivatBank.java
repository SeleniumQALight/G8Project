package apiPrivatBank;


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
import static org.apache.hc.core5.http.HttpStatus.SC_OK;


public class ApiHelperPrivatBank {

    Logger logger = Logger.getLogger(ApiHelperPrivatBank.class);

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(SC_OK)
            .build();


    public ValidatableResponse getExchangesRatesByDate(String date) {
        return given()
                .spec(requestSpecification)
                .when()
                .queryParam("date", date)
                .get(EndPointsPrivat.BASE_URL)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_OK));
    }
}
