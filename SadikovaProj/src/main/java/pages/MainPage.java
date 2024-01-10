package pages;


import libs.Urls;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends ParentPage {

    /**
     * Login Form in header
     */
    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement signInButtonInHeader;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement passwordFieldInHeader;
    @FindBy(xpath = ".//div[text()='Invalid username/password.']")
    private WebElement warningMessageInHeader;
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement loginFieldInHeader;

    /**
     * Registration Form
     */
    @FindBy(xpath = "//input[@id='username-register']")
    private WebElement userNameField;
    @FindBy(xpath = "//input[@id='password-register']")
    private WebElement passwordField;
    @FindBy(xpath = "//input[@id='email-register']")
    private WebElement emailField;
    @FindBy(xpath = "//button[text()='Sign up for OurApp']")
    private WebElement signInForOurAppButton;
    @FindBy(xpath = "//div[contains(@class,'liveValidateMessage--visible')]")
    private List<WebElement> errorBlockList;

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openMainPage() {
        goToWebPage(Urls.HOME_PAGE_URL);
    }

    public void enterTextIntoInputLogin(String login) {
        enterTextIntoInput(loginFieldInHeader, login);
    }

    public void enterTextIntoInputPassword(String password) {
        enterTextIntoInput(passwordFieldInHeader, password);
    }

    public boolean isWarningMessageVisible() {
        return isElementDisplayed(warningMessageInHeader);
    }

    /**
     * VISIBLE
     */
    public MainPage checkLoginFieldIsVisible() {
        checkIsElementVisible(loginFieldInHeader);
        return this;
    }


    public MainPage checkPasswordFieldIsVisible() {
        checkIsElementVisible(passwordFieldInHeader);
        return this;
    }

    public MainPage checkSignInButtonIsVisible() {
        checkIsElementVisible(signInButtonInHeader);
        return this;
    }


    /**
     * NOT visible elements
     */

    public MainPage checkLoginFieldNotVisible() {
        checkElementIsNotDisplayed(loginFieldInHeader);
        return this;
    }

    public MainPage checkPasswordFieldNotVisible() {
        checkElementIsNotDisplayed(passwordFieldInHeader);
        return this;
    }

    public MainPage checkSignInButtonIsNotVisible() {
        checkElementIsNotDisplayed(signInButtonInHeader);
        return this;
    }

    public MainPage checkErrorMessage(int index, String expectedText) {
        checkTextInElement(errorBlockList.get(index), expectedText);
        return this;
    }

    /**
     * CLICKS
     */
    public void clickOnSignInForOurAppButton() {
        clickOnElement(signInForOurAppButton);
    }


    public void clickOnButtonSingIn() {
        clickOnElement(signInButtonInHeader);
    }

    public void clickOnButtonSignIn() {
        clickOnElement(signInButtonInHeader);
    }


    public MainPage fillForm(String name, String email, String pass) {
        enterTextIntoInput(userNameField, name);
        enterTextIntoInput(emailField, email);
        enterTextIntoInput(passwordField, pass);
        return this;

    }

    public MainPage loginToProfile(String login, String password) {
        openMainPage();
        enterTextIntoInputLogin(login);
        enterTextIntoInputPassword(password);
        clickOnButtonSingIn();
        return new MainPage(webDriver);
    }


}

