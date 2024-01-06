package pages;


import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {
    private HeaderElement headerElement;

    @FindBy(xpath = ".//*[@class ='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//span[@class='text-white mr-2']")
    private WebElement successProfileName;

    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage checkIsRedirectOnHomePage() {
//        TODO check url
        Assert.assertTrue("Invalid page - not Home Page ", getHeader().isButtonSignOutVisible());
        return this;
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }


    // check is Profile Name visible
    public HomePage checkIsProfileNameVisible(String profileName) {
        Assert.assertEquals("Profile Name is not visible", profileName, successProfileName.getText());
        return this;
    }

    public HomePage openHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (this.getHeader().isButtonSignOutVisible()) {
            logger.info("User is already logged in");
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
            loginPage.enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
            loginPage.clickOnButtonSignIn();
            checkIsRedirectOnHomePage();
            logger.info("User was logged in");
        }
        return this;
    }

    // check is input Login visible
    public HomePage isInputLoginVisible() {
        try {
            Assert.assertFalse("Input Login is visible", inputLogin.isDisplayed());
        } catch (Exception e) {
            logger.info("Input Login is not visible");
        }
        return this;
    }

    // check is input Password visible
    public HomePage isInputPasswordVisible() {
        try {
            Assert.assertFalse("Input Password is visible", inputLogin.isDisplayed());
        } catch (Exception e) {
            logger.info("Input Password is not visible");
        }
        return this;
    }

}
