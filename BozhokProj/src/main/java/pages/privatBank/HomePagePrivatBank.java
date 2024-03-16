package pages.privatBank;

import apiPrivatBank.EndPointsPrivatBank;
import data.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionWithElements;

public class HomePagePrivatBank extends CommonActionWithElements {

    @FindBy(xpath = ".//td[@id='EUR_buy']")
    private WebElement currencyBuy;

    @FindBy(xpath = ".//td[@id='EUR_sell']")
    private WebElement currencySell;

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

    public void getBuyExchangeRate() {
        try {
            TestData.EXCHANGE_RATES_BUY_UI = Double.parseDouble(currencyBuy.getText());
        } catch (Exception e) {
            logger.error("Can not get buy exchange rate " + e);
            Assert.fail("Can not get buy exchange rate " + e);
        }
    }

    public void getSellExchangeRate() {
        try {
            TestData.EXCHANGE_RATES_SELL_UI = Double.parseDouble(currencySell.getText());
        } catch (Exception e) {
            logger.error("Can not get sell exchange rate " + e);
            Assert.fail("Can not get sell exchange rate " + e);
        }
    }
}
