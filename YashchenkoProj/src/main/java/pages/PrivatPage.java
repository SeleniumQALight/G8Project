package pages;

import org.junit.Assert;
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

    @FindBy(xpath = "//td[@id='EUR_buy']")
    private WebElement euroBuy;

    @FindBy(xpath = "//td[@id='EUR_sell']")
    private WebElement euroSell;

    @FindBy(xpath = "//td[@id='USD_buy']")
    private WebElement usdBuy;

    @FindBy(xpath = "//td[@id='USD_sell']")
    private WebElement usdSell;

    public PrivatPage openPrivatHomePage() {
        try {
            webDriver.get("https://privatbank.ua/");
            logger.info("https://privatbank.ua/ was opened ");
        } catch (Exception e) {
            logger.error("Can not open https://privatbank.ua/");
            Assert.fail("Can not open https://privatbank.ua/");
        }
        return this;
    }

    public double parseDoubleAndRoundResult(String stringValue) {
        double value = Double.parseDouble(stringValue);
        return Math.round(value * 10.00000) / 10.00000;
    }

    public PrivatPage checkIsRedirectedToPrivatHomePage() {
        Assert.assertEquals("URL is not equal", "https://privatbank.ua/", webDriver.getCurrentUrl());
        checkIsElementVisible(currencyExchangeRatesBlock);
        return this;
    }

    public HashMap<String, Double> getCurrentCurrencyRatesOnUiAsHashMap(String currency){
        uiRates = new HashMap<>();
        currency = currency.toUpperCase();
            if(currency.equals("USD")){
                uiRates.put("buy", parseDoubleAndRoundResult(usdBuy.getText()));
                uiRates.put("sale", parseDoubleAndRoundResult(usdSell.getText()));
            } else if (currency.equals("EUR")) {
                uiRates.put("buy", parseDoubleAndRoundResult(euroBuy.getText()));
                uiRates.put("sale", parseDoubleAndRoundResult(euroSell.getText()));
            } else {
                logger.info("Other currencies except USD/EUR are forbidden ");
            }
        return uiRates;
    }
}
