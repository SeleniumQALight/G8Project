package privatBankTest;

import api.ApiHelperPb;
import baseTest.BaseTest;
import data.TestData;
import org.junit.Test;

public class checkCurrencyRateTest extends BaseTest {
    ApiHelperPb apiHelperPb = new ApiHelperPb();

    @Test
    public void compareCurrencyRates() {
        apiHelperPb.getCurrencyRatesAndSaveInMap("usd");
        pageProvider.privatBankPage().openPrivatBankPage();
        //pageProvider.privatBankPage().checkCurrencyRateCompareUiAndApi("usd", apiHelperPb.getCurrencyRatesAndSaveInMap("usd"));
        pageProvider.privatBankPage().checkCurrencyRateCompareUiAndApi("usd", TestData.currencyRatesMap);
    }
}
