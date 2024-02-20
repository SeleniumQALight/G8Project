package apiTest;

import apiPrivatBank.ApiHelperPrivatBank;
import apiPrivatBank.PBDto.ExchangeDTO;
import apiPrivatBank.PBDto.ExchangeRateDTO;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;


import java.util.List;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiTestPrivatBank {
    final String DATE = "01.12.2014";
    String[] CURRENCY = {"AUD", "CAD", "CZK", "DKK", "HUF", "ILS", "JPY", "LVL", "LTL", "NOK", "SKK", "SEK", "CHF", "GBP", "USD", "BYR", "EUR", "GEL", "PLZ"};
    String baseCurrency = "UAH";
    Logger logger = Logger.getLogger(getClass());

    ApiHelperPrivatBank apiHelperPrivatBank = new ApiHelperPrivatBank();

    @Test
    public void apiTestPrivatBankByDate() {
        Response actualResponse = apiHelperPrivatBank.getExchangeRatePBRequest(DATE, HttpStatus.SC_OK).extract().response();
        ExchangeRateDTO.builder()
                .date(DATE)
                .bank("PB")
                .baseCurrency(980)
                .baseCurrencyLit(baseCurrency)
                .exchangeRate(ExchangeDTO.builder().baseCurrency(baseCurrency).build())
                .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions
                .assertThat(actualResponse)
                .usingRecursiveComparison()
                .ignoringFields("saleRateNB", "purchaseRateNB", "saleRate", "purchaseRate")
                .isEqualTo(actualResponse);
        List<Map> actualResponseAsMap = actualResponse.jsonPath().getList("exchangeRate", Map.class);
        for (int i = 0; i < actualResponseAsMap.size(); i++) {
            softAssertions.assertThat(actualResponseAsMap.get(i).get("currency")).as("Iteam number " +i).isEqualTo(CURRENCY[i]);
        }
        softAssertions.assertAll();
    }

        @Test
    public void apiTestPrivatBankShema() {
        apiHelperPrivatBank.getExchangeRatePBRequest(DATE)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("responsePB.json"));
    }
}