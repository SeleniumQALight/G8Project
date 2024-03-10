package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PrivatBankPage extends ParentPage {
    public PrivatBankPage(WebDriver webDriver) {
        super(webDriver);
    }

    private String url = "https://privatbank.ua/";
    private String locatorCurrSellValue = ".//td[@id='%s_sell']";
    private String locatorCurrBuyValue = ".//td[@id='%s_buy']";


    @Override
    protected String getRelativeUrl() {
        return null;
    }

    public void openPrivatBankPage() {
        try {
            webDriver.get(url);
            logger.info("Page was open " + url);
        } catch (Exception e) {
            logger.error("Page " + url + " notopen");
            Assert.fail("Page \" + url + \" notopen");
        }
    }

    public String getCurrencySaleValue(String currecyName) {
        WebElement currencyRate = getElement(locatorCurrSellValue, currecyName);
        return currencyRate.getText();
    }

    public String getCurrencyBuyValue(String currencyName) {
        WebElement currencyRate = getElement(locatorCurrBuyValue, currencyName);
        return currencyRate.getText();
    }

}
