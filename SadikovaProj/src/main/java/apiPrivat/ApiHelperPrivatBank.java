package apiPrivat;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class ApiHelperPrivatBank {

    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(SC_OK)
            .build();


    public ValidatableResponse getExchangeRateByDate(String date) {
        return given()
                .spec(requestSpecification)
                .queryParam("date", date)
                .when()
                .get(EndPointsPrivatBank.EXCHANGE_RATES)
                .then()
                .spec(responseSpecification)
                .spec(responseSpecification.statusCode(SC_OK));
    }

}
