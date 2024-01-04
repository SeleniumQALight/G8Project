package pages.MainPage;


import libs.Urls;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.HomePage;
import pages.ParentPage;

public class LoginForm extends ParentPage {

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement signInButton;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement passwordField;
    @FindBy(xpath = ".//div[text()='Invalid username/password.']")
    private WebElement warningMessage;
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement loginField;


    public LoginForm(WebDriver webDriver) {
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
    public LoginForm checkLoginFieldIsVisible() {
        checkIsElementVisible(loginField);
        return this;
    }


    public LoginForm checkPasswordFieldIsVisible() {
        checkIsElementVisible(passwordField);
        return this;
    }

    public LoginForm checkSignInButtonIsVisible() {
        checkIsElementVisible(signInButton);
        return this;
    }


    /**
     * NOT visible elements
     */

    public LoginForm checkLoginFieldNotVisible() {
        checkElementIsNotDisplayed(loginField);
        return this;
    }

    public LoginForm checkPasswordFieldNotVisible() {
        checkElementIsNotDisplayed(passwordField);
        return this;
    }

    public LoginForm checkSignInButtonIsNotVisible() {
        checkElementIsNotDisplayed(signInButton);
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
