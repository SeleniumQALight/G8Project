package pages;


import libs.Urls;
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
        goToWebPage(Urls.HOME_PAGE_URL);
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

    /**
     * VISIBLE
     */
    public LoginPage loginFieldIsVisible() {
        checkIsElementVisible(loginField);
        return this;
    }


    public LoginPage passwordFieldIsVisible() {
        checkIsElementVisible(passwordField);
        return this;
    }

    public LoginPage signInButtonIsVisible() {
        checkIsElementVisible(signInButton);
        return this;
    }


    /**
     * NOT visible elements
     */

    public LoginPage loginFieldNotVisible() {
        checkIsNotElementDisplayed(loginField);
        return this;
    }

    public LoginPage passwordFieldNotVisible() {
        checkIsNotElementDisplayed(passwordField);
        return this;
    }

    public LoginPage signInButtonIsNotVisible() {
        checkIsNotElementDisplayed(signInButton);
        return this;
    }


    public void clickOnButtonSingIn() {
        clickOnElement(signInButton);
    }

    public HomePage loginToProfile(String login, String password) {
        openLoginPage();
        enterTextIntoInputLogin(login);
        enterTextIntoInputPassword(password);
        clickOnButtonSingIn();
        return new HomePage(webDriver);
    }

}
