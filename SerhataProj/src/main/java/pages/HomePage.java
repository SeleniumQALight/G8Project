package pages;

import data.TestData;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage{

    private HeaderElement headerElement;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    @Step
    public HomePage checkIsRedirectToHomePage() {
        checkUrl();
        Assert.assertTrue("Invalid page - not Home Page", getHeader().isButtonSignOutPresent());
        getHeader().checkIsHeaderForUserVisible();
        getLoginPage().checkIsLoginFieldIsNotVisible();
        return this;
    }


    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }

    @Step
    public HomePage openHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (this.getHeader().isButtonSignOutPresent()) {
            logger.info("User is already logged in");
        } else {
            loginPage.enterTextInToInputLogin(TestData.VALID_LOGIN_UI);
            loginPage.enterTextInToInputPassword(TestData.VALID_PASSWORD_UI);
            loginPage.clickOnButtonSignIn();
            checkIsRedirectToHomePage();
            logger.info("User was logged in");
        }
        return this;
    }

    @Step
    public LoginPage getLoginPage() {
        return new LoginPage(webDriver);
    }
}
