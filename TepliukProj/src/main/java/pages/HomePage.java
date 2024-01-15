package pages;


import libs.TestData;
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



    public HomePage checkIsRedirectToHomePage() {
        checkUrl();
        Assert.assertTrue("Invalid page - not Home page", getHeader().isButtonSignOutVisible());
        return this;
    }

    public LoginPage redirectOnLoginPage (){
        return new LoginPage(webDriver);
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }

    public HomePage openHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (this.getHeader().isButtonSignOutVisible()) {
            logger.info("User is already logged in");
        } else {
            loginPage.enterTextIntoInputLogin((TestData.VALID_LOGIN_UI));
            loginPage.enterTextIntoInputPassword((TestData.VALID_PASSWORD_UI));
            loginPage.clickOnButtonSignIn();
            checkIsRedirectToHomePage();
            logger.info("User is logged in");
        }
        return this;
    }




}
