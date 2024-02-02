package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

import static data.TestData.DEFAULT_VALID_LOGIN_UI;
import static data.TestData.DEFAULT_VALID_PASSWORD_UI;

public class HomePage extends ParentPage {
    private HeaderElement headerElement;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    @Step
    public HomePage checkIsRedirectedToHomePage() {
        checkCurrentUrl();
        Assert.assertTrue("Invalid page - not Home Page"
                , getHeader().isButtonSignOutVisible());
        return this;
    }
    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }
    @Step
    public HomePage openHomePageAndLogInIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (this.getHeader().isButtonSignOutVisible()) {
            logger.info("User is already logged in");
        } else {
            loginPage.enterTextIntoInputLogin(DEFAULT_VALID_LOGIN_UI);
            loginPage.enterTextIntoInputPassword(DEFAULT_VALID_PASSWORD_UI);
            loginPage.clickOnButtonSignIn();
            checkIsRedirectedToHomePage();
            logger.info("User was logged in");
        }
        return this;
    }
}
