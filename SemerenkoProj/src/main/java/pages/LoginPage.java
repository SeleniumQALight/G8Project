package pages;

import libs.TestData;
import org.apache.commons.logging.Log;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputUsername;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;
    @FindBy(xpath = ".//div[@class='alert alert-danger text-center' and contains(text(),'Invalid username/password.')]")
    private WebElement divFailSignIn;
    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputUsernameRegistration;
    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputEmailRegistration;
    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputPasswordRegistration;
    @FindBy(xpath = ".//div[contains(text(), 'Username must be at least 3 characters.')]")
    private WebElement divWarningUsernameRegistration;
    @FindBy(xpath = ".//div[contains(text(), 'You must provide a valid email address.')]")
    private WebElement divWarningEmailRegistration;
    @FindBy(xpath = ".//div[contains(text(), 'Password must be at least 12 characters.')]")
    private WebElement divWarningPasswordRegistration;
    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement buttonSignUp;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement headerElement;

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
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
        enterTextIntoInput(inputUsername, login);
    }

    public void enterTextIntoInputPass(String pass) {
        enterTextIntoInput(inputPassword, pass);
    }

    public void clickOnButtonSignIn() {
        clickOnElement(getHeader().buttonSignIn);
    }

    public void clickOnButtonSingUp(){
        clickOnElement(buttonSignUp);
    }

    public LoginPage clickOnButtonSignup(){
        clickOnElement(buttonSignUp);
        return this;
    }

    public boolean isMessageFailLogin() {
        return isElementDisplayed(divFailSignIn);
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPass(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public LoginPage enterIntoUsernameRegistration(String username) {
        enterTextIntoInput(inputUsernameRegistration, username);
        return this;
    }

    public LoginPage enterIntoEmailRegistration(String email){
        enterTextIntoInput(inputEmailRegistration, email);
        return this;
    }

    public LoginPage enterIntoPasswordRegistration(String pass){
        enterTextIntoInput(inputPasswordRegistration, pass);
        return this;
    }

    public LoginPage checkIsRedirectOnLoginPage() {
        getHeader().checkIsElementButtonSignInVisible();
        return this;
    }

    public LoginPage checkIsInputUsernameVisible() {
        getHeader().checkIsElementInputUsernameVisible();
        return this;
    }

    public LoginPage checkIsInputPasswordVisible() {
        getHeader().checkIsElementInputPasswordVisible();
        return this;
    }

    public LoginPage checkIsButtonSignInVisible() {
        getHeader().checkIsElementButtonSignInVisible();
        return this;
    }

    public LoginPage checkIsWarningUsernameRegistrationVisible(){
        checkIsElementVisible(divWarningUsernameRegistration, "divWarningUsernameRegistration");
        return this;
    }

    public LoginPage checkIsWarningEmailRegistrationVisible(){
        checkIsElementVisible(divWarningEmailRegistration, "divWarningEmailRegistration");
        return this;
    }

    public LoginPage checkIsWarningPasswordRegistrationVisible(){
        checkIsElementVisible(divWarningPasswordRegistration, "divWarningPasswordRegistration");
        return this;
    }

    public LoginPage checkIsButtonSearchUnvisible() {
        getHeader().checkIsElementButtonSearchUnvisible();
        return this;
    }

    public LoginPage checkIsButtonChatUnvisible() {
        getHeader().checkIsElementButtonChatUnvisible();
        return this;
    }

    public LoginPage checkIsButtonProfileUnvisible() {
        getHeader().checkIsElementLinkMyProfileUnvisible();
        return this;
    }

    public LoginPage checkIsButtonCreatePostUnvisible() {
        getHeader().checkIsElementButtonCreateNewPostUnvisible();
        return this;
    }

    public LoginPage checkIsButtonSignOutUnvisible() {
        getHeader().checkIsElementButtonSignOutUnvisible();
        return this;
    }


}
