package apiPrivatBank;

import apiPrivatBank.dto.responsePrivatBankDTO.CurrencyRateDto;
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

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;

public class ApiHelperPrivatBank {

    public static Double buyRateApi;
    public static Double saleRateApi;

    static Logger logger = Logger.getLogger(ApiHelperPrivatBank.class);

    static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(SC_OK)
            .build();


    public ValidatableResponse getExchangeRateByDate(String date) {
        return given()
                .spec(requestSpecification)
                .when()
                .queryParam("date", date)
                .get(EndPointsPrivatBank.BASE_URL)
                .then()
                .spec(responseSpecification)
                .spec(responseSpecification.statusCode(SC_OK));
    }

    public static ValidatableResponse getExchangeRate() {
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

    public static CurrencyRateDto[] saveExchangeRate(String currency) {
        CurrencyRateDto[] currencyRateDto = getExchangeRate().extract().as(CurrencyRateDto[].class);
        for (int i = 0; i < currencyRateDto.length; i++) {
            if (currencyRateDto[i].getCcy().equalsIgnoreCase(currency)) {
                buyRateApi = Double.valueOf(currencyRateDto[i].getBuy());
                saleRateApi = Double.valueOf(currencyRateDto[i].getSale());
                logger.info("Currency buying rate for " + currency + " is: " + buyRateApi + "[API]");
                logger.info("Currency selling rate for " + currency + " is: " + saleRateApi + "[API]");
            }
        }
        return currencyRateDto;
    }
}
