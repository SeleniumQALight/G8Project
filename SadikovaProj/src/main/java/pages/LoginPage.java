package pages;

import org.bouncycastle.jcajce.provider.asymmetric.X509;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {

@FindBy(xpath = "//button[text()='Sign In']")
private WebElement signInButton;
@FindBy(xpath = ".//input[@placeholder='Password']")
private WebElement passwordField;
@FindBy(xpath = ".//div[text()='Invalid username/password.']")
private WebElement warningMessage;
@FindBy(xpath = ".//input[@placeholder='Username']")
private WebElement loginField;
@FindBy(xpath = ".//input[@placeholder='Username']")
private WebElement userNameField;
//@FindBy(xpath = "")
//private WebElement userNameField;



    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://aqa-complexapp.onrender.com");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not open login page");
            Assert.fail("Can not open login page");
        }
    }

    public void enterTextIntoInputLogin(String login) {
        enterTextIntoInput(loginField, login);
    }

    public void enterTextIntoInputPassword(String password) {
        enterTextIntoInput(passwordField, password);
    }

    public void clickOnButtonSignIn() {
        clickOnElement(signInButton);
    }

    public boolean isWarningMessageVisible() {
        try {
            return isElementDisplayed(warningMessage);
        } catch (Exception e) {
            logger.error("Element is displayed -> false");
            return false;
        }
    }

    public boolean isButtonSignInVisible() {
        try {
            return isElementDisplayed(signInButton);
        } catch (Exception e) {
            logger.error("Element is displayed -> false");
            return false;
        }
    }

    public void clickOnButtonSingIn() {
        clickOnElement(signInButton);
    }

    public void loginToProfile(String login, String password){
        openLoginPage();
        enterTextIntoInputLogin(login);
        enterTextIntoInputPassword(password);
        clickOnButtonSingIn();
    }
}
