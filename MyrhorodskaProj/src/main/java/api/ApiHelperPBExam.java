package api;

import api.dto.pbResponseDto.ExchangeRateDtoPbExam;
import io.restassured.http.ContentType;
import api.PrivatBankEndPoints;

import java.text.DecimalFormat;

import static io.restassured.RestAssured.given;

public class ApiHelperPBExam {

    public ExchangeRateDtoPbExam[] getCurrencyRateTestExam() {
        ExchangeRateDtoPbExam[] responseExchangeRateDtoExam = given()
                .contentType(ContentType.JSON)
                .queryParam("json", "")
                .queryParam("exchange", "")
                .queryParam("coursid", "5")
                .log().all()
                .when()
                .get(PrivatBankEndPoints.CURRENCY_RATES_EXAM)
                .then()
                .statusCode(200)
                .log().all()
                .assertThat()
                .extract().body().as(ExchangeRateDtoPbExam[].class);
        return responseExchangeRateDtoExam;
    }

    public static DecimalFormat getDecimalFormat() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
        return decimalFormat;
    }
}