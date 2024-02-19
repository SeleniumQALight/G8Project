package apiTests;

import api.ApiHelperPb;
import api.dto.responseDto.RateArchPbDto;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiTestPb {
    private String rateDate = "22.03.2022";
    Logger logger = Logger.getLogger(getClass());
    ApiHelperPb apiHelperPb = new ApiHelperPb();

    @Test
    public void getCurrencyRateByDateAndValidateByDto() {
        RateArchPbDto actualResponseDto =
                apiHelperPb.getRateByDate(rateDate)
                        .extract()
                        .response()
                        .as(RateArchPbDto.class);

//Не хотілося руками вбивати , витягнув купу валют з респонсу
        List<String> listOfCurrency =
                apiHelperPb.getRateByDate(rateDate).extract().response().jsonPath().getList("exchangeRate.currency", String.class);


        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualResponseDto)
                .usingRecursiveComparison()
                .ignoringFields(
                        "exchangeRate.saleRateNB",
                        "exchangeRate.purchaseRateNB",
                        "exchangeRate.saleRate",
                        "exchangeRate.purchaseRate"
                )
                .isEqualTo(apiHelperPb.getExpectedDto(rateDate, listOfCurrency));
        softAssertions.assertAll();
    }
}
