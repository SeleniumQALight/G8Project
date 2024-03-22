package api;

import api.dto.responseDto.FxRateDto;
import io.qameta.allure.restassured.AllureRestAssured;
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

import static io.restassured.RestAssured.*;

public class ApiHelperPrivatBank {
    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .addFilter(new AllureRestAssured())
            .log(LogDetail.ALL)
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public ValidatableResponse AllExchangeRates(String date) {
        return given()
                .spec(requestSpecification)
                .queryParam("date", date)
                .when()
                .get(PrivatBankEndPoint.BASE_URL)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_OK));
    }

    public List<FxRateDto> getCurrentFxRates() {
        try {
            Response response =  given()
                    .spec(requestSpecification)
                    .when()
                    .get(PrivatBankEndPoint.FX_URL)
                    .then()
                    .spec(responseSpecification.statusCode(HttpStatus.SC_OK))
                    .extract()
                    .response();

            List<FxRateDto> fxRateDtos = response.jsonPath().getList("", FxRateDto.class);
            return fxRateDtos;

        } catch (Exception e) {
            logger.error("Getting FX rates operation failed " + e);
            Assert.fail("Getting FX rates operation failed " + e);
        }

        return null;
    }
}

