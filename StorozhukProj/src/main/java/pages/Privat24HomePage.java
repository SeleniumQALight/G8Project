package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Privat24HomePage extends ParentPage {

    @FindBy(xpath = ".//*[@id='USD_buy']")
    private WebElement usdBuyExchangeRate;

    @FindBy(xpath = ".//*[@id='EUR_buy']")
    private WebElement eurBuyExchangeRate;

    @FindBy(xpath = ".//*[@id='USD_sell']")
    private WebElement usdSellExchangeRate;

    @FindBy(xpath = ".//*[@id='EUR_sell']")
    private WebElement eurSellExchangeRate;

    public Privat24HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public Privat24HomePage open() {
        try {
            webDriver.get(privat24Url);
            logger.info("Privat24 page was opened " + privat24Url);
        } catch (Exception e) {
            logger.error("Can not open Privat24 page");
            Assert.fail("Can not open Privat24 page");
        }
        return this;
    }

    public Double getBuyExchangeRate(String currencyCode)
    {
        String strFx;
        if (currencyCode.equals("EUR"))
            strFx = eurBuyExchangeRate.getText();
        else if (currencyCode.equals("USD"))
            strFx = usdBuyExchangeRate.getText();
        else {
            Assert.fail(String.format("Not supported currency code: %s", currencyCode));
            return 0.0;
        }

        return Double.parseDouble(strFx);
    }

    public Double getSellExchangeRate(String currencyCode)
    {
        String strFx;
        if (currencyCode.equals("EUR"))
            strFx = eurSellExchangeRate.getText();
        else if (currencyCode.equals("USD"))
            strFx = usdSellExchangeRate.getText();
        else {
            Assert.fail(String.format("Not supported currency code: %s", currencyCode));
            return 0.0;
        }

        return Double.parseDouble(strFx);
    }
}
