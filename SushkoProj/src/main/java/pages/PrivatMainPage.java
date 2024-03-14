package pages;

import data.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PrivatMainPage extends ParentPage{
    private final String PRIVAT_BASE_URL = "https://privatbank.ua/";
    private String buyExchangeRateLocator = ".//td[@id='%s_buy']";
    private String saleExchangeRateLocator = ".//td[@id='%s_sell']";

    public PrivatMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return PRIVAT_BASE_URL;
    }

    public PrivatMainPage openPrivatMainPage() {
        try{
            webDriver.get(getRelativeUrl());
            logger.info("Main privat page was opened " + getRelativeUrl());
        } catch (Exception e){
            logger.error("Can not open main privat page");
            Assert.fail("Can not open main privat page");
        }
        return this;
    }

    public PrivatMainPage getBuyExchangeRate(String currency) {
        try {
            WebElement value = webDriver.findElement(By.xpath(String.format(buyExchangeRateLocator, currency)));
            TestData.buyRatePrivatUI = Float.valueOf(value.getText());
            logger.info("Buy exchange rate for " + currency + " is " + TestData.buyRatePrivatUI);
        } catch (Exception e) {
            logger.error("Can not get buy exchange rate");
            Assert.fail("Can not get buy exchange rate");
        }
        return this;
    }

    public PrivatMainPage getSaleExchangeRate(String currency) {
        try {
            WebElement value = webDriver.findElement(By.xpath(String.format(saleExchangeRateLocator, currency)));
            TestData.saleRatePrivatUI =  Float.valueOf(value.getText());
            logger.info("Sale exchange rate for " + currency + " is " + TestData.saleRatePrivatUI);
        } catch (Exception e){
            logger.error("Can not get sale exchange rate");
            Assert.fail("Can not get sale exchange rate");
        }
        return this;
    }
}
