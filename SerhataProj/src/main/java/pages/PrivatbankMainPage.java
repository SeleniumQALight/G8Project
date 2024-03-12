package pages;

import data.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public class PrivatbankMainPage extends ParentPage {
    private final String PRIVAT_BANK_BASE_URL_UI = "https://privatbank.ua/";
    private final String USD = "USD";
    private final String EUR = "EUR";
    private String buyLocator = ".//td[@id='%s_buy']";
    private String saleLocator = ".//td[@id='%s_sell']";

    public WebElement getCurrencyElement(String currency, String transactionType) {
        return webDriver.findElement(By.xpath(".//td[@id='" + currency + "_" + transactionType + "']"));
    }

    public PrivatbankMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return null;
    }

    @Step
    public void openMainPage() {
        try {
            webDriver.get(PRIVAT_BANK_BASE_URL_UI);
            logger.info("Main page was opened " + PRIVAT_BANK_BASE_URL_UI);
        } catch (Exception e) {
            logger.error("Can not open Privatbank Main page");
            Assert.fail("Can not open Privatbank Main page");
        }
    }

    private WebElement getBuyRate(String currencyName) {
        return webDriver.findElement(By.xpath(String.format(buyLocator, currencyName)));
    }

    private WebElement getSaleRate(String currencyName) {
        return webDriver.findElement(By.xpath(String.format(saleLocator, currencyName)));
    }

    @Step
    public void storeRateForCurrency(String currencyName) {
        TestData.BUY_RATE_UI = Double.valueOf(getBuyRate(currencyName).getText());
        TestData.SALE_RATE_UI = Double.valueOf(getSaleRate(currencyName).getText());
    }
}
