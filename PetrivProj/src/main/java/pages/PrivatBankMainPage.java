package pages;

import data.TestData;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PrivatBankMainPage extends ParentPage {

    private final String PRIVAT_BANK_BASE_URL_UI = "https://privatbank.ua/";

    private String buyRateLocator = ".//td[@id='%s_buy']";
    private String saleRateLocator = ".//td[@id='%s_sell']";

    private WebElement getBuyRate(String currencyName) {
        return webDriver.findElement(By.xpath(String.format(buyRateLocator, currencyName)));
    }

    private WebElement getSaleRate(String currencyName) {
        return webDriver.findElement(By.xpath(String.format(saleRateLocator, currencyName)));
    }

    public PrivatBankMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return PRIVAT_BANK_BASE_URL_UI;
    }

    @Step
    public void openMainPage() {
        try {
            webDriver.get(getRelativeUrl());
            logger.info("Main page was opened " + PRIVAT_BANK_BASE_URL_UI);
        } catch (Exception e) {
            logger.error("Can not open Main page");
            Assert.fail("Can not open Main page");
        }
    }

    @Step
    public void storeRateForCurrency(String currencyName) {
        TestData.BUY_RATE_UI = Double.valueOf(getBuyRate(currencyName).getText());
        TestData.SALE_RATE_UI = Double.valueOf(getSaleRate(currencyName).getText());
    }
}
