package apiPrivatbank;

import apiPrivatbank.dto.responsePrivatBank.ExchangeByCoursIdDTO;
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

public class ApiHelperPrivatBank {
    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();


    public ValidatableResponse getExchangeRateByDate(String paramDate) {
        return given()
                .spec(requestSpecification)
                .when()
                .queryParam("date", paramDate)
                .get(EndPointsPrivatBank.BASE_URL)
                .then()
                .spec(responseSpecification)
                .spec(responseSpecification.statusCode(HttpStatus.SC_OK));
    }

    public ValidatableResponse getExchangeRate() {
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPointsPrivatBank.EXCHANGE)
                .then()
                .spec(responseSpecification)
                .spec(responseSpecification.statusCode(HttpStatus.SC_OK));
    }

    public ValidatableResponse getExchangeRateByCoursId() {
        return given()
                .spec(requestSpecification)
                .when()
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", 5)
                .get(EndPointsPrivatBank.EXCHANGE)
                .then()
                .spec(responseSpecification)
                .spec(responseSpecification.statusCode(HttpStatus.SC_OK));
    }
    public void saveExchangeRateByCoursIdToTestDataForSelectedCurrency(String currency) {
        Response response = getExchangeRateByCoursId().extract().response();
        List<ExchangeByCoursIdDTO> exchangeByCoursIdDTO = response.jsonPath().getList("", ExchangeByCoursIdDTO.class);
        try {
            TestData.privatBankBuyCours = 0;
            TestData.privatBankSaleCours = 0;
            for (int i = 0; i < exchangeByCoursIdDTO.size(); i++){
                if (exchangeByCoursIdDTO.get(i).getCcy().equals(currency)){
                    TestData.privatBankBuyCours = Float.parseFloat(exchangeByCoursIdDTO.get(i).getBuy());
                    TestData.privatBankSaleCours = Float.parseFloat(exchangeByCoursIdDTO.get(i).getSale());
                    break;
                }
            }
            if (TestData.privatBankBuyCours == 0 || TestData.privatBankSaleCours == 0){
                logger.error("Invalid currency");
                Assert.fail("Invalid currency");
            }
        }
         catch (Exception e) {
            logger.error("Exception occurred");
            Assert.fail("Exception occurred");
        }

    }

}


