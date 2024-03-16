package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;

import static data.TestData.uiRates;

public class PrivatPage extends CommonActionsWithElements {
    public PrivatPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//h4[@class='eclipse-text col-6']")
    private WebElement currencyExchangeRatesBlock;

    private String currencyBuy = "//td[@id='%s_buy']";
    private String currencySell = "//td[@id='%s_sell']";

    private String privatHomePageURL = "https://privatbank.ua/";

    public PrivatPage openPrivatHomePage() {
        try {
            webDriver.get(privatHomePageURL);
            logger.info(privatHomePageURL + " was opened ");
        } catch (Exception e) {
            logger.error("Can not open " + privatHomePageURL);
            Assert.fail("Can not open " + privatHomePageURL);
        }
        return this;
    }

    public double parseDoubleAndRoundResult(String stringValue) {
        double value = Double.parseDouble(stringValue);
        return Math.round(value * 10.00000) / 10.00000;
    }

    public PrivatPage checkIsRedirectedToPrivatHomePage() {
        Assert.assertEquals("URL is not equal", privatHomePageURL, webDriver.getCurrentUrl());
        checkIsElementVisible(currencyExchangeRatesBlock);
        return this;
    }

    public HashMap<String, Double> getCurrentCurrencyRatesOnUiAsHashMap(String currency){
        uiRates = new HashMap<>();
        currency = currency.toUpperCase();
        uiRates.put("buy", parseDoubleAndRoundResult(
                webDriver.findElement(By.xpath(String.format(currencyBuy, currency))).getText()));
        uiRates.put("sale", parseDoubleAndRoundResult(
                webDriver.findElement(By.xpath(String.format(currencySell, currency))).getText()));
        return uiRates;
    }
}
