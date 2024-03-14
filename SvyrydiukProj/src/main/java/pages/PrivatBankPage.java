package pages;

import data.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.catchException;

public class PrivatBankPage extends ParentPage {

    public PrivatBankPage(WebDriver webDriver) {
        super(webDriver);
    }


    @Override
    String getRelativeUrl() {
        return "https://privatbank.ua/";
    }

    public void getBuyRate(String currency) {
        try {
            WebElement WebElement = webDriver.findElement(By.xpath("//tr/td[@id='" + currency + "_buy']"));
            TestData.privatBankBuyCoursUI = Float.parseFloat(WebElement.getText());
        } catch (Exception e) {
                logger.error("Invalid currency");
                Assert.fail("Invalid currency");
        }
    }

    public void getSellRate(String currency) {
        try {
            WebElement WebElement = webDriver.findElement(By.xpath("//tr/td[@id='" + currency + "_sell']"));
            TestData.privatBankSaleCoursUI = Float.parseFloat(WebElement.getText());
        } catch (Exception e) {
                logger.error("Invalid currency");
                Assert.fail("Invalid currency");
        }
    }

    public void openPage() {
        try {
            webDriver.get(getRelativeUrl());
        } catch (Exception e) {
            logger.error("Can not work with browser");
            Assert.fail("Can not work with browser");
        }
    }
}

