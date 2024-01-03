package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class LoginPage extends ParentPage {

    @FindBy(xpath = "//button[contains(text(),'Sign In')]")
    //this element will be created by PageFactory in CommonActionsWithElements
    private WebElement buttonSignIn;

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://aqa-complexapp.onrender.com/");
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

    public boolean isInputLoginVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//input[@placeholder='Username']")).isDisplayed();
            logger.info("Input 'Username' is displayed -> " + state);
            return state;
        } catch (Exception e) {
            logger.info("Input 'Username' is not displayed");
            return false;
        }
    }

    public boolean isInputPasswordVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//input[@placeholder='Password']")).isDisplayed();
            logger.info("Input 'Password' is displayed -> " + state);
            return state;
        } catch (Exception e) {
            logger.info("Input 'Password' is not displayed");
            return false;
        }
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public boolean isButtonSignInVisible() {
        return isElementDisplayed(buttonSignIn);
    }

    public boolean isAlertInvalidUsernamePasswordVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//div[text() = 'Invalid username/password.']")).isDisplayed();
            logger.info(state + " is alert visible");
            return state;
        } catch (Exception e) {
            logger.info("Alert is not displayed");
            return false;
        }
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCreate() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public LoginPage isInputUsernameNotVisible() {
        checkIsElementNotVisible(inputLogin, "Input Username");
        return this;
    }

    public LoginPage isInputPasswordNotVisible() {
        checkIsElementNotVisible(inputPassword, "Input Password");
        return this;
    }

    public LoginPage isButtonSignInNotVisible() {
        checkIsElementNotVisible(buttonSignIn, "Button Sign In");
        return this;
    }

    public LoginPage checkIsRedirectToLoginPage() {
        Assert.assertTrue("Login page is not opened", isButtonSignInVisible());
        return this;
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }
}
