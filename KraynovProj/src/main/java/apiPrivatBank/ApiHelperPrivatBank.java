package apiPrivatBank;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import apiPrivatBank.responsePrivatBankDto.CurrencyRateDto;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import java.util.List;

public class ApiHelperPrivatBank {

    static Logger logger = Logger.getLogger(ApiHelperPrivatBank.class);

    public static Double buyRateApi;
    public static Double saleRateApi;


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
                .get(EndPointsForPrivatBank.BASE_URL)
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
                .get(EndPointsForPrivatBank.EXCHANGE)
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
