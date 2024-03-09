package apiPrivatbank;

import apiPrivatbank.dto.responseDto.ExchangeRateByIdDTO;
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

public class ApiPrivatbankHelper {

    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public ValidatableResponse getExchangeRateByDate(String date, int statusCode) {
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPointsPrivatbank.EXCHANGE_BY_DATE, date)
                .then()
                .spec(responseSpecification.statusCode(statusCode));
    }

    public ValidatableResponse getExchangeRateByDate(String date) {
        return getExchangeRateByDate(date, HttpStatus.SC_OK);
    }

    public ValidatableResponse getExchangeRateByCurrency() {
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPointsPrivatbank.EXCHANGE_BY_CURRENCY)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_OK));
    }

    public void saveExchangeRateByCurrency(String currency) {
        Response response = getExchangeRateByCurrency().extract().response();
        List<ExchangeRateByIdDTO> exchangeRateByIdDTO = response.jsonPath().getList("", ExchangeRateByIdDTO.class);
        try {
            for (int i = 0; i < exchangeRateByIdDTO.size(); i++) {
                if(exchangeRateByIdDTO.get(i).getCcy().equals(currency)) {
                    TestData.EXCHANGE_BUY_API = Double.parseDouble(exchangeRateByIdDTO.get(i).getBuy());
                    TestData.EXCHANGE_SELL_API = Double.parseDouble(exchangeRateByIdDTO.get(i).getSale());
                }
            }
        } catch (Exception e) {
            logger.error("Can not save exchange rate by currency " + e);
            Assert.fail("Can not save exchange rate by currency " + e);
        }
    }
}
