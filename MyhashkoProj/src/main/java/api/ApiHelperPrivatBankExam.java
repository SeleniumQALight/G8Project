package api;

import api.dto.responseDto.PrivatDto;
import data.TestData;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;


public class ApiHelperPrivatBankExam {

    public PrivatDto[] getCurrencyRateWhithApiInPrivatBank(String currency) {
        PrivatDto[] response = given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", "5")
                .log().all()
                .when()
                .get(EndPointsPrivat.PRIVATE_EXAM)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .extract().as(PrivatDto[].class);
        for (int i = 0; i < response.length; i++) {
            if (response[i].getCcy().equalsIgnoreCase(currency)) {
                TestData.api_curs_buy = "";
                TestData.api_curs_sale = "";
                TestData.api_curs_buy = response[i].getBuy();
                Logger.getLogger(getClass()).info("api curs buy for " + currency + " = " + TestData.api_curs_buy);
                TestData.api_curs_sale = response[i].getSale();
                Logger.getLogger(getClass()).info("api curs sale for " + currency + " = " + TestData.api_curs_sale);
            }
        }
        return response;
    }

}
