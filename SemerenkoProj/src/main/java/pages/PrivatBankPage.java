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

    public Double getCurrencySaleValue(String currency) {
        WebElement currencyRate = getElement(locatorCurrSellValue, currency);
        return Double.parseDouble(currencyRate.getText());
    }

    public Double getCurrencyBuyValue(String currency) {
        WebElement currencyRate = getElement(locatorCurrBuyValue, currency);
        return Double.parseDouble(currencyRate.getText());
    }



}
