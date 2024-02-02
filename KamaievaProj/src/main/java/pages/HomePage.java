package pages;

import data.TestData;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {
    private HeaderElement headerElement;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelatedUrl() {
        return "/";
    }
    @Step
    public HomePage checkIsRedirectToHomePage() {
        checkCurrentUrl();
        Assert.assertTrue("Home page is not opened", getHeader().isButtonSignOutVisible());
        return this;
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }
    @Step
    public HomePage openHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if(this.getHeader().isButtonSignOutVisible()) {
            logger.info("User is already logged in");
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
            loginPage.enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
            loginPage.clickOnButtonSignIn();
            checkIsRedirectToHomePage();
            logger.info("User was logged in");
        }
        return this;
    }
}
