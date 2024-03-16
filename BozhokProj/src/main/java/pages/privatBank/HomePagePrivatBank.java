package pages.privatBank;

import apiPrivatBank.EndPointsPrivatBank;
import data.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionWithElements;

public class HomePagePrivatBank extends CommonActionWithElements {

    String locatorForCurrencyBuy = ".//td[@id='%s_buy']";
    String locatorForCurrencySell = ".//td[@id='%s_sell']";

    public HomePagePrivatBank(WebDriver webDriver) {
        super(webDriver);
    }

    public void openHomePage() {
        try {
            webDriver.get(EndPointsPrivatBank.BASE_URL_UI);
            logger.info("Home page was opened");
        } catch (Exception e) {
            logger.error("Can not open Home Page");
            Assert.fail("Can not open Home Page");
        }
    }

    public void getBuyExchangeRate(String currency) {
        try {
            WebElement currencyBuy = webDriver.findElement(By.xpath(String.format(locatorForCurrencyBuy, currency)));
            TestData.EXCHANGE_RATES_BUY_UI = Double.parseDouble(currencyBuy.getText());
            logger.info("Buy exchange rate was saved " + TestData.EXCHANGE_RATES_BUY_UI);
        } catch (Exception e) {
            logger.error("Can not get buy exchange rate " + e);
            Assert.fail("Can not get buy exchange rate " + e);
        }
    }

    public void getSellExchangeRate(String currency) {
        try {
            WebElement currencySell = webDriver.findElement(By.xpath(String.format(locatorForCurrencySell, currency)));
            TestData.EXCHANGE_RATES_SELL_UI = Double.parseDouble(currencySell.getText());
            logger.info("Sell exchange rate was saved " + TestData.EXCHANGE_RATES_SELL_UI);
        } catch (Exception e) {
            logger.error("Can not get sell exchange rate " + e);
            Assert.fail("Can not get sell exchange rate " + e);
        }
    }
}
