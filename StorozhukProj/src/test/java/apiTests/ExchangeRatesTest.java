package apiTests;

import api.ApiHelperPrivatBank;
import api.dto.responseDto.BaseExchangePrivatBankDto;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class ExchangeRatesTest {

    private String date = "22.03.2022";
    final String[] Currencies = {"AUD", "AZN", "BYN", "CAD", "CHF", "CNY", "CZK", "DKK", "EUR", "GBP", "GEL", "HUF", "ILS", "JPY", "KZT", "MDL", "NOK", "PLN", "SEK", "SGD", "TMT", "TRY", "UAH", "USD", "UZS"};
    final String BaseCurrency = "UAH";

    @Test
    public void createPostByApi() {
        ApiHelperPrivatBank api = new ApiHelperPrivatBank();
        Response response = api.AllExchangeRates(date).extract().response();

        BaseExchangePrivatBankDto responseDto = response.as(BaseExchangePrivatBankDto.class);
        SoftAssertions softAssertions = new SoftAssertions();


        BaseExchangePrivatBankDto expectedDto = BaseExchangePrivatBankDto.builder()
                .date(date)
                .bank("PB")
                .baseCurrency(980)
                .baseCurrencyLit(BaseCurrency)
                .build();

        softAssertions
                .assertThat(responseDto)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate")
                .isEqualTo(expectedDto);

        for (int i = 0; i < responseDto.getExchangeRate().length; i++) {
            softAssertions.assertThat(responseDto.getExchangeRate()[i].getBaseCurrency()).contains(BaseCurrency);
            softAssertions.assertThat(responseDto.getExchangeRate()[i].getCurrency()).isIn(Currencies);
        }

        softAssertions.assertAll();
    }


}
