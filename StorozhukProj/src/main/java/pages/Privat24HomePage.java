package pages;

import data.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Privat24HomePage extends ParentPage {

    private String fxPattern = ".//*[@id='%s_%s']";

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

    public Double getFxRate(String ccy, String action)
    {
        try {
            return Double.parseDouble(webDriver.findElement(By.xpath(String.format(fxPattern, ccy, action))).getText());
        }
        catch (Exception e) {
            Assert.fail(String.format("Failed to get FX rate for %s currency and %s action", ccy, action));
        }

        return 0.0;
    }
}
