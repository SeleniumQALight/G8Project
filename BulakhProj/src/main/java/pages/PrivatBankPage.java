package pages;

import data.TestData;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PrivatBankPage extends ParentPage {

    private final String PRIVAT_BANK_URL_UI = "https:/privatbank.ua/";

    public PrivatBankPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return this.PRIVAT_BANK_URL_UI;
    }

    @Step
    public void openPage() {
        try {
            webDriver.get(PRIVAT_BANK_URL_UI);
            logger.info("PrivatBank page was opened");
        } catch (Exception e) {
            logger.error("Can not open PrivatBank page" + e);
            Assert.fail("Can not open PrivatBank page" + e);
        }
    }

    @Step
    public void getBuyCourse(String currency) {
        try {
            WebElement WebElement = webDriver.findElement(By.xpath("//tr/td[@id='" + currency + "_buy']"));
            TestData.privatBankBuyCourseWebPage = Float.parseFloat(WebElement.getText());
        } catch (Exception e) {
            logger.error("Incorrect currency");
            Assert.fail("Incorrect currency");
        }
    }

    @Step
    public void getSellCourse(String currency) {
        try {
            WebElement WebElement = webDriver.findElement(By.xpath("//tr/td[@id='" + currency + "_sell']"));
            TestData.privatBankSaleCourseWebPage = Float.parseFloat(WebElement.getText());
        } catch (Exception e) {
            logger.error("Incorrect currency");
            Assert.fail("Incorrect currency");
        }


    }
}
