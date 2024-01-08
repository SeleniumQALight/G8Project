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

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }

    public HomePage checkIsButtonCreateNewPostVisible() {
        getHeader().checkIsElementButtonCreateNewPostVisible();
        return this;
    }

    public HomePage checkIsButtonSignOutVisible() {
        getHeader().checkIsElementButtonSignOutVisible();
        return this;
    }

    public HomePage checkIsLinkMyProfileVisible() {
        getHeader().checkIsElementLinkMyProfileVisible();
        return this;
    }

    public HomePage checkIsSpanUserNameVisible() {
        getHeader().checkIsElementSpanUserNameVisible();
        return this;
    }

    public HomePage checkIsButtonChatVisible(){
        getHeader().checkIsElementButtonChatVisible();
        return this;
    }

    public HomePage checkIsInputUsernameUnvisible() {
        getHeader().checkIsElementInputUsernameUnvisible();
        return this;
    }

    public HomePage checkIsInputPasswordUnvisible() {
        getHeader().checkIsElementInputPasswordUnvisible();
        return this;
    }

    public HomePage checkIsButtonSignInUnvisible(){
        getHeader().checkIsElementButtonSignInUnvisible();
        return this;
    }

    public HomePage checkIsRedirectToHomePage() {
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
