package api.privatBank;

import api.privatBank.privatDto.CurrentRatesDTO;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;

import java.util.HashMap;

import static data.TestData.apiRates;
import static io.restassured.RestAssured.given;

public class PrivatBankApiHelper {

    public double parseDoubleAndRoundResult(String stringValue) {
        double value = Double.parseDouble(stringValue);
        return Math.round(value * 10.00000) / 10.00000;
    }
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

    public HashMap<String, Double> getCurrentRatesAsHashMapForSpecificCurrency(String currency){
        CurrentRatesDTO[] currentRatesDTOS =
                given()
                .contentType(ContentType.JSON)
                .queryParam("coursid", 5)
                .queryParam("json")
                .queryParam("exchange")
                .log().all()
                .when()
                .get(PrivatEndPoints.PRIVAT_GET_CURRENT_RATES)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                        .extract().response().body().as(CurrentRatesDTO[].class);
        apiRates = new HashMap<>();
        currency = currency.toUpperCase();
        for (int i = 0; i < currentRatesDTOS.length; i++) {
            if (currentRatesDTOS[i].getCcy().equals(currency)){
                apiRates.put("buy", parseDoubleAndRoundResult(currentRatesDTOS[i].getBuy()));
                apiRates.put("sale", parseDoubleAndRoundResult(currentRatesDTOS[i].getSale()));
                break;
            }
        }
        return apiRates;
    }
}
