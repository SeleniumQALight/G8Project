package pages;

import data.TestData;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PrivatBankPage extends ParentPage {

    private final String PRIVAT_BANK_URL_UI = "https:/privatbank.ua/";

    private String currency_buy = "//tr/td[@id='%s_buy']";
    private String currency_sell = "//tr/td[@id='%s_sell']";

    public PrivatBankPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelatedUrl() {
        return this.PRIVAT_BANK_URL_UI;
    }

    @Step
    public void openPrivatBankPage() {
        try {
            webDriver.get(PRIVAT_BANK_URL_UI);
            logger.info("PrivatBank page was opened");
        } catch (Exception e) {
            logger.error("Can not open PrivatBank page " + e);
            Assert.fail("Can not open PrivatBank page " + e);
        }
    }

    @Step
    public void getBuyCurrency(String currency) {
        try {
            TestData.EXCHANGE_BUY_UI = Double.parseDouble(webDriver.findElement(By.xpath(String.format(currency_buy, currency))).getText());
        } catch (Exception e) {
            logger.error("Can not get buy currency " + e);
            Assert.fail("Can not get buy currency " + e);
        }
    }

    @Step
    public void getSellCurrency(String currency) {
        try {
            TestData.EXCHANGE_SELL_UI = Double.parseDouble(webDriver.findElement(By.xpath(String.format(currency_sell, currency))).getText());
        } catch (Exception e) {
            logger.error("Can not get sell currency " + e);
            Assert.fail("Can not get sell currency " + e);
        }
    }
}
