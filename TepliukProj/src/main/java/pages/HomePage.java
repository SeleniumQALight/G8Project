package pages;


import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage{

    private HeaderElement headerElement;

//    @FindBy(xpath = "//button[text()='Sign Out']")
//    private WebElement buttonSignOut;


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

//    public boolean isButtonSignOutVisible() {
//      //  WebElement buttonSignOut = webDriver.findElement(By.xpath("//button[text()='Sign Out']"));
//        return isElementDisplayed(buttonSignOut);
//    }

    public HomePage checkIsRedirectToHomePage() {
        //TODO check url
        Assert.assertTrue("Invalid page - not Home page", getHeader().isButtonSignOutVisible());
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
            loginPage.enterTextIntoInputLogin((TestData.VALID_LOGIN_UI));
            loginPage.enterTextIntoInputPassword((TestData.VALID_PASSWORD_UI));
            loginPage.clickOnButtonSignIn();
            checkIsRedirectToHomePage();
            logger.info("User is logged in");
        }
        return this;
    }
}
