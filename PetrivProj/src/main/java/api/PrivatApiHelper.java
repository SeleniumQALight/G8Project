package api;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class PrivatApiHelper extends ApiHelper{
    public ValidatableResponse getAllExchangeRatesRequest(String date) {
        return given()
                .spec(requestSpecification)
                .queryParam("date", date)
                .when()
                .get(PrivateEndPoints.BASE_URL)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_OK));
    }
}
