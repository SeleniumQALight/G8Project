package api;

import api.dto.responseDto.ResponseDtoPB;
import data.TestData;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;

public class ApiHelperPB {
    Logger logger = Logger.getLogger(getClass());

    //метод отримання курсів валют з API PB для порівняння
    public static ResponseDtoPB[] getCurseViaApi(String currency) {
        Logger logger = Logger.getLogger(ApiHelperPB.class);
        ResponseDtoPB[] responseDtoPB = given()
                .contentType(ContentType.JSON)
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", 5)
                .when()
                .get(EndPointsPB.URL_PB)
                .then()
                .statusCode(200)
                .extract().as(ResponseDtoPB[].class);
        for (int i = 0; i < responseDtoPB.length; i++) {
            if (responseDtoPB[i].getCcy().equalsIgnoreCase(currency)) {
                TestData.cursViaApiBui = responseDtoPB[i].getBuy();
                TestData.cursViaApiSale = responseDtoPB[i].getSale();

                logger.info("currency buying rate (API) for " + currency + " is: " + TestData.cursViaApiBui);
                logger.info("currency selling rate (API) for " + currency + " is: " + TestData.cursViaApiSale);
            }
        }
        return responseDtoPB;

    }

}