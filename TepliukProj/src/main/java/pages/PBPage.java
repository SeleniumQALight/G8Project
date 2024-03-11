package pages;

import data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PBPage extends ParentPage {


    public PBPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//*[@class='eclipse-text col-6']")
    WebElement titleExchangeRates;


    private String exchangeRateBuyOnTheWeb = ".//*[@id='%s_buy']";

     private String exchangeRateSaleOnTheWeb = "//*[@id='%s_sell']";

    @Override
    protected String getRelativeUrl() {
        return null;
    }
    public void openPBpage() { openPage(BASE_PB_URL);}

    public PBPage checkIsTitleExchangeRatesVisible() {
       checkIsElementVisible(titleExchangeRates);
        return this;
    }



    public String getExchangeRateBuyOnTheWeb1(String currency) {
        String exchangeRateBuyLocator = String.format(exchangeRateBuyOnTheWeb, currency);
        WebElement element = webDriver.findElement(By.xpath(exchangeRateBuyLocator));
         TestData.exchangeRateBuy = element.getText();
        logger.info("currency buying rate (WEBSITE) for " + currency + " is: " + TestData.exchangeRateBuy);
        return TestData.exchangeRateBuy;
    }



    public String getExchangeRateSaleOnTheWeb(String currency) {
        String exchangeRateSaleLocator = String.format(exchangeRateSaleOnTheWeb, currency);
        WebElement element = webDriver.findElement(By.xpath(exchangeRateSaleLocator));
        TestData.exchangeRateSale = element.getText();
        logger.info("currency selling (WEBSITE) rate for " + currency + " is: " + TestData.exchangeRateSale);
        return TestData.exchangeRateSale;
    }

}