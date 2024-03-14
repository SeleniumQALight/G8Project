import api.ApiHelperPBExam;
import api.dto.pbResponseDto.ExchangeRateDtoPbExam;
import io.cucumber.java.en.Given;
import data.TestData;
import org.apache.log4j.Logger;

import java.text.DecimalFormat;

public class ApiStepsPb {
    ApiHelperPBExam apiHelperPrivatBank = new ApiHelperPBExam();
    Logger logger = Logger.getLogger(getClass());

    @Given("I save exchange rate for buy and sale for {string} from the PrivatBank API response")
    public void i_save_exchange_rate_for_buy_and_sale_for_from_the_privat_bank_api_response(String currencyName){
        ExchangeRateDtoPbExam[] responseExchangeRateDtoExam = apiHelperPrivatBank.getCurrencyRateTestExam();
        for (ExchangeRateDtoPbExam exchangeRateDtoExam : responseExchangeRateDtoExam) {
            if (exchangeRateDtoExam.getCcy().equalsIgnoreCase(currencyName)) {
                TestData.rateBuyAPI = exchangeRateDtoExam.getBuy();
                TestData.rateSaleAPI = exchangeRateDtoExam.getSale();

                DecimalFormat decimalFormat = ApiHelperPBExam.getDecimalFormat();
                TestData.rateBuyAPI = decimalFormat.format(Double.parseDouble(TestData.rateBuyAPI));
                TestData.rateSaleAPI = decimalFormat.format(Double.parseDouble(TestData.rateSaleAPI));

                logger.info("rate Buy from API = " + TestData.rateBuyAPI);
                logger.info("rate Sale from API = " + TestData.rateSaleAPI);
            }
        }
    }
}
