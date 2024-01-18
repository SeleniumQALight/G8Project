package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {
    private HeaderElement headerElement;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }

    public LoginPage redirectOnLoginPage (){
        return new LoginPage(webDriver);
    }

    public HomePage checkIsButtonCreateNewPostVisible() {
        getHeader().checkIsButtonCreateNewPostVisible();
        return this;
    }

    public HomePage checkIsButtonSignOutVisible() {
        getHeader().checkIsButtonSignOutVisible();
        return this;
    }

    public HomePage checkIsLinkMyProfileVisible() {
        getHeader().checkIsButtonMyProfileVisible();
        return this;
    }

    public HomePage checkIsSpanUserNameVisible() {
        getHeader().checkIsSpanUserNameVisible();
        return this;
    }

    public HomePage checkIsButtonChatVisible(){
        getHeader().checkIsButtonChatVisible();
        return this;
    }

//    public HomePage checkIsInputUsernameUnvisible() {
//        getHeader().checkIsElementInputUsernameUnvisible();
//        return this;
//    }
//
//    public HomePage checkIsInputPasswordUnvisible() {
//        getHeader().checkIsElementInputPasswordUnvisible();
//        return this;
//    }
//
//    public HomePage checkIsButtonSignInUnvisible(){
//        getHeader().checkIsElementButtonSignInUnvisible();
//        return this;
//    }



    public HomePage checkIsRedirectToHomePage() {
        getHeader().checkIsButtonSignOutVisible();
        checkUrl();
        Assert.assertTrue("Invalid page - not Home Page", getHeader().isButtonSignOutVisible());
        return this;
    }

    public HomePage openHomePageLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (this.getHeader().isButtonSignOutVisible()) {
            logger.info("User is already logged in");
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
            loginPage.enterTextIntoInputPass(TestData.VALID_PASSWORD_UI);
            loginPage.clickOnButtonSignIn();
            checkIsRedirectToHomePage();
            logger.info("User is logged in");
        }
        return this;


    }
}
