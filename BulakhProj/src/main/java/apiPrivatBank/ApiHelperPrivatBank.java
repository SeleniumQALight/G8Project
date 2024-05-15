package apiPrivatBank;


import apiPrivatBank.dto.responsePrivatBankDTO.ExchangeByCoursIdDTO;
import data.TestData;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.List;

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

    public ValidatableResponse getExchangeRate(){
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPointsPrivat.EXCHANGE_RATE)
                .then()
                .spec(responseSpecification)
                .spec(responseSpecification.statusCode(HttpStatus.SC_OK));
    }

    public ValidatableResponse getExchangeRateByCours(){
        return given()
                .spec(requestSpecification)
                .when()
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", 5)
                .get(EndPointsPrivat.EXCHANGE_RATE)
                .then()
                .spec(responseSpecification)
                .spec(responseSpecification.statusCode(HttpStatus.SC_OK));
    }

    public void recordExchangeRateForCourseIdInTestDataWithSelectedCurrency(String currency) {
        Response response = getExchangeRateByCours().extract().response();
        List<ExchangeByCoursIdDTO> exchangeRateByIdDTO = response.jsonPath().getList("", ExchangeByCoursIdDTO.class);
        try {
            TestData.privatBankBuyCourse = 0;
            TestData.privatBankSaleCourse = 0;
            for (int i = 0; i < exchangeRateByIdDTO.size(); i++) {
                if (exchangeRateByIdDTO.get(i).getCcy().equals(currency)) {
                    TestData.privatBankBuyCourse = Float.parseFloat(exchangeRateByIdDTO.get(i).getBuy());
                    TestData.privatBankSaleCourse = Float.parseFloat(exchangeRateByIdDTO.get(i).getSale());
                    break;
            }

        }
            if (TestData.privatBankBuyCourse == 0 || TestData.privatBankSaleCourse == 0) {
                logger.error("Currency not found");
                Assert.fail("Currency not found");
            }

    }
        catch (Exception e) {
            logger.error("Error with parsing");
            Assert.fail("Error with parsing");
        }
    }


}
