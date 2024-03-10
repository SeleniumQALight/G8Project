package privatBankTest;

import baseTest.BaseTest;
import org.junit.Test;

public class checkCurrencyRateTest extends BaseTest {
    @Test
    public void getCurrencyRates() {
        pageProvider.privatBankPage().openPrivatBankPage();
        System.out.println("USD buy : " + pageProvider.privatBankPage().getCurrencyBuyValue("USD"));
        System.out.println("USD sell : " + pageProvider.privatBankPage().getCurrencySaleValue("USD"));
        System.out.println("EUR buy : " + pageProvider.privatBankPage().getCurrencyBuyValue("EUR"));
        System.out.println("EUR sell : " + pageProvider.privatBankPage().getCurrencySaleValue("EUR"));
    }
}
