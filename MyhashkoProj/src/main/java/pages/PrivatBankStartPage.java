package pages;

import api.EndPointsPrivat;
import data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PrivatBankStartPage extends ParentPage{
    public PrivatBankStartPage(WebDriver webDriver) {
        super(webDriver);
    }
    String partOfLocatorBuy = ".//*[@id='%s_buy']";
    String partOfLocatorSale = ".//*[@id='%s_sell']";

    @Override
    protected String getRelativeUrl() {
            return "/";
    }

    public PrivatBankStartPage openPrivatBankStartPage() {
        webDriver.get(EndPointsPrivat.PRIVATE_START_PAGE_EXAM);
        webDriver.manage().window().maximize();

        return this;
    }

    public void getCurrencyRateWithUiInPrivatBank(String currency) {
        String locator_buy = String.format(partOfLocatorBuy, currency);
        String locator_sale = String.format(partOfLocatorSale, currency);

        WebElement buy = webDriver.findElement(By.xpath(locator_buy));
        WebElement sale = webDriver.findElement(By.xpath(locator_sale));

        TestData.ui_curs_buy = buy.getText();
        logger.info("ui curs buy for " + currency + " = " + TestData.ui_curs_buy);
        TestData.ui_curs_sale = sale.getText();
        logger.info("ui curs sale for " + currency + " = " + TestData.ui_curs_sale);
    }
}
