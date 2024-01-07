package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }



    public HomePage checkIsRedirectToHomePage() {
        //TODO check url
        Assert.assertTrue("Invalid page - not Home Page", headerElement().isButtonSignOutVisible());

        return this;
    }

    public HeaderElement headerElement(){
        return new HeaderElement(webDriver);
    }


    public HomePage openHomePageAndLoginIfNeeded(){
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if(this.headerElement().isButtonSignOutVisible()){
            logger.info("User is already logged in");
        }else {
            loginPage.loginToProfile(TestData.VALID_LOGIN_UI, TestData.VALID_PASSWORD);
            checkIsRedirectToHomePage();
            logger.info("User is logged in");
        }
        return this;
    }
}