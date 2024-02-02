package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;


public class HomePage extends ParentPage {
    private HeaderElement headerElement;
    @FindBy(xpath = "//button[contains(text(),'Sign Out')]")
    private WebElement buttonSignOut;
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativUrl() {
        return "/";
    }

 @Step
    public HomePage checkIsredirectToHomePage() {
        checkUrl();
        Assert.assertTrue("Home page is not opened", getHeader().isButtonSignOutVisible());
        return this; //перевірка чи ми на сторінці
    }
    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }
    @Step
    public HomePage openHomePageAndLoginIfNeed() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (this.getHeader().isButtonSignOutVisible()) {
            logger.info("User is already logged in");
        } else {
            loginPage.enterTextInToInputLogin(VALID_LOGIN_UI);
            loginPage.enterTextInToInputPassword(VALID_PASSWORD_UI);
            loginPage.clickOnButtonSingIn();
           checkIsredirectToHomePage();
            logger.info("User is logged in");
        }
        return this;
    }
}