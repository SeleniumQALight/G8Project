package pages;


import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

public class HomePage extends ParrentPage {
    private HeaderElement headerElement;


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }


    public HomePage checkIsRedirectToHomePage() {
        // TODO check url
        Assert.assertTrue("Invalid page - not Home Page"
                , getHeader().isButtonSignOutVisible());
        return this;
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
            loginPage.enterTextInToInputLogin(TestData.VALID_LOGIN_UI);
            loginPage.enterTextInToInputPassword(TestData.VALID_PASSWORD_UI);
            loginPage.clickOnButtonSignIn();
            checkIsRedirectToHomePage();
            logger.info("User is logged in");
        }
        return this;
    }
}
