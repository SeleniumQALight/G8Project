package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

import static libs.TestData.DEFAULT_VALID_LOGIN_UI;
import static libs.TestData.DEFAULT_VALID_PASSWORD_UI;

public class HomePage extends ParentPage {
    private HeaderElement headerElement;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }



    public HomePage checkIsRedirectedToHomePage() {
        // TODO check url
        Assert.assertEquals("Invalid page"
                , "https://aqa-complexapp.onrender.com/"
                , webDriver.getCurrentUrl());
        Assert.assertTrue("Invalid page - not Home Page"
                , getHeader().isButtonSignOutVisible());
        return this;
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }

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
