package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage{

    @FindBy(xpath = ".//button[contains(text(),'Sign In')]")
    private WebElement buttonSignIn;
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;
    @FindBy(xpath = ".//div[text()='Invalid username/password.']")
    private WebElement errorMessage;

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
        enterTextIntoInput(inputLogin, login);
    }

    public void enterTextIntoInputPassword(String password) {
        enterTextIntoInput(inputPassword, password);
    }


    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public boolean isErrorMessageVisible() {
        return isElementDisplayed(errorMessage);
    }

    public boolean clickOnButtonSignInVisible() {
        return isElementDisplayed(buttonSignIn);
    }

    public HomePage openLoginPageFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }
    public boolean isButtonSignInVisible() {
        try {
            return isElementDisplayed(buttonSignIn);
        } catch (Exception e) {
            logger.info("Element button SignIn is displayed -> false");
            return false;
        }
    }

}
