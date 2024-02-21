package api.privatBank;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class PrivatBankApiHelper {

    public ValidatableResponse getValidatableResponseWithExchangeRatesArchive(String date){
        return given()
                        .contentType(ContentType.JSON)
                        .queryParam("date", date)
                        .log().all()
                        .when()
                        .get(PrivatEndPoints.PRIVAT_GET_EXCHANGE_RATES)
                        .then()
                        .log().all()
                        .statusCode(HttpStatus.SC_OK);
    }
}
