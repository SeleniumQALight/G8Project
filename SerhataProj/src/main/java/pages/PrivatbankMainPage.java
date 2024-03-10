package pages;

import data.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.qameta.allure.Step;

public class PrivatbankMainPage extends ParentPage {
    private final String PRIVAT_BANK_BASE_URL_UI = "https://privatbank.ua/";

    @FindBy(xpath = ".//td[@id='USD_buy']")
    private WebElement usdBuyRate;

    @FindBy(xpath = ".//td[@id='USD_sell']")
    private WebElement usdSaleRate;

    @FindBy(xpath = ".//td[@id='EUR_buy']")
    private WebElement eurBuyRate;

    @FindBy(xpath = ".//td[@id='EUR_sell']")
    private WebElement eurSaleRate;
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

    @Step
    public void storeRateForCurrency(String currencyName) {
        switch (currencyName.toUpperCase()) {
            case "USD":
                TestData.USD_BUY_RATE_UI = Double.valueOf(usdBuyRate.getText());
                TestData.USD_SALE_RATE_UI = Double.valueOf(usdSaleRate.getText());
                break;
            case "EUR":
                TestData.EUR_BUY_RATE_UI = Double.valueOf(eurBuyRate.getText());
                TestData.EUR_SALE_RATE_UI = Double.valueOf(eurSaleRate.getText());
                break;
            default:
                break;
        }
    }
}
