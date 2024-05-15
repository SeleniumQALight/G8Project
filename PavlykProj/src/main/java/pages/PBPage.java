package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PBPage extends ParentPage {

    public static Double buyRateUI;
    public static Double saleRateUI;

    @FindBy(xpath = ".//article[contains(@class,'block_content')][contains(@class,'courses')]")
    WebElement exchangeRatesBlock;

    private String exchangeRateBuy = ".//td[@id='%s_buy']";
    private String exchangeRateSale = "//td[@id='%s_sell']";

    public PBPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public PBPage openPBPage() {
            try {
                webDriver.get(BASE_PB_URL);
                logger.info("Home PB page was opened " + BASE_PB_URL);
                return this;
            } catch (Exception e) {
                logger.error("Can not open home PB page");
                Assert.fail("Can not open home PB page");
                return null;
            }
    }

    public PBPage checkIsExchangeRatesBlockVisible() {
        webDriverWait05.until(ExpectedConditions.visibilityOf(exchangeRatesBlock));;
        checkIsElementVisible(exchangeRatesBlock);
        return this;
    }

    public double getExchangeRateBuy(String currency) {
        String exchangeRateBuyLocator = String.format(exchangeRateBuy, currency);
        WebElement element = webDriver.findElement(By.xpath(exchangeRateBuyLocator));
        buyRateUI = Double.valueOf(element.getText());
        logger.info("Currency buying rate for " + currency + " is: " + buyRateUI + "[IU]");
        return buyRateUI;
    }

    public double getExchangeRateSale(String currency) {
        String exchangeRateSaleLocator = String.format(exchangeRateSale, currency);
        WebElement element = webDriver.findElement(By.xpath(exchangeRateSaleLocator));
        saleRateUI = Double.valueOf(element.getText());
        logger.info("Currency selling rate for " + currency + " is: " + saleRateUI + "[IU]");
        return saleRateUI;
    }
}