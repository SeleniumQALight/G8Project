package pages;


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
        return isElementDisplayed(warningMessage);
    }

    public boolean isButtonSignInVisible() {
        return isElementDisplayed(signInButton);
    }

    public void clickOnButtonSingIn() {
        clickOnElement(signInButton);
    }

        public HomePage loginToProfile(String login, String password) {
        enterTextIntoInputLogin(login);
        enterTextIntoInputPassword(password);
        clickOnButtonSingIn();
        return new HomePage(webDriver);
    }

    public HomePage goToPageAndLoginToProfile(String login, String password) {
        openLoginPage();
        loginToProfile(login, password);
        return new HomePage(webDriver);
    }




}
