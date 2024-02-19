package api;

import api.dto.responseDto.ExchangeRatePbDto;
import api.dto.responseDto.RateArchPbDto;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;


public class ApiHelperPb {
    Logger logger = Logger.getLogger(getClass());
    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public ValidatableResponse getRateByDate(String date) {
        return given()
                .spec(requestSpecification)
                .queryParam("date", date)
                .when()
                .get(EndPointsPb.EXCH_ARCH)
                .then()
                .spec(responseSpecification);

    }

    public RateArchPbDto getExpectedDto(String date, List<String> listOfCurrency) {
        List<ExchangeRatePbDto> exchangeRateList = new ArrayList<>();
        for (int i = 0; i < listOfCurrency.size(); i++) {

            exchangeRateList.add(
                    ExchangeRatePbDto.builder()
                            .baseCurrency("UAH")
                            .currency(listOfCurrency.get(i))
                            .build()
            );
        }
        return RateArchPbDto.builder()
                .date(date)
                .bank("PB")
                .baseCurrency(980)
                .baseCurrencyLit("UAH")
                .exchangeRate(exchangeRateList)
                .build();
    }
}
