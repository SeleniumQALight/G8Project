package pages;

import data.TestData;
import io.qameta.allure.Step;
import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.elements.HeaderElement;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage{

    @FindBy(xpath = ".//button[contains(text(),'Sign In')]")
    private WebElement buttonSignIn;
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;
    @FindBy(xpath = ".//div[text()='Invalid username/password.']")
    private WebElement errorMessage;
    @FindBy(id = "username-register")
    private WebElement inputUserNameRegistration;
    @FindBy(id = "email-register")
    private WebElement inputEmailRegistration;
    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistration;
    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listErrorsMessages;
    private String listErrorsMessagesLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputUsernameAtRegistration;

    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputEmailAtRegistration;

    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputPasswordAtRegistration;

    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement buttonSignUp;

    @FindBy(xpath = ".//div[text()='Username must be at least 3 characters.']")
    private WebElement validationMessageForUserNameField;

    @FindBy(xpath = ".//div[text()='You must provide a valid email address.']")
    private WebElement validationMessageForEmailField;

    @FindBy(xpath = ".//div[text()='Password must be at least 12 characters.']")
    private WebElement validationMessageForPasswordField;
    @FindBy(xpath = "//div[contains(@class,'alert-danger') and not (contains(@class,'liveValidateMessage'))]")
    private WebElement errorMessageLoginForm;
    @FindBy(xpath = ".//div[@class='alert alert-danger text-center']")
    private WebElement alertMessageInCenter;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }
    @Step
    public LoginPage openLoginPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Login page was opened " + baseUrl);
            return this;
        } catch (Exception e) {
            logger.error("Can not open login page");
            Assert.fail("Can not open login page");
            return null;
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
    public void clickOnButtonSignUp() { clickOnElement(buttonSignUp); }

    public LoginPage checkIsInputLoginVisible() { checkIsElementVisible(inputLogin); return this; }

    public void checkIsInputPasswordVisible() { checkIsElementVisible(inputPassword); }

    public void enterTextIntoInputUsernameRegistration(String username) {
        enterTextIntoInput(inputUsernameAtRegistration, username);
    }

    public void enterTextIntoInputEmailRegistration(String email) {
        enterTextIntoInput(inputEmailAtRegistration, email);
    }
    @Step
    public LoginPage enterTextIntoInputPasswordRegistration(String password) {
        enterTextIntoInput(inputPasswordAtRegistration, password);
        return this;
    }
    @Step
    public LoginPage checkIsValidationMessageForUserNameFieldVisible() {
        checkIsElementVisible(validationMessageForUserNameField);
        return this;
    }
    @Step
    public LoginPage checkIsValidationMessageForEmailFieldVisible() {
        checkIsElementVisible(validationMessageForEmailField);
        return this;
    }
    @Step
    public LoginPage checkIsValidationMessageForPasswordFieldVisible() {
        checkIsElementVisible(validationMessageForPasswordField);
        return this;
    }
    @Step
    // input Login is not visible
    public void checkIsInputLoginNotVisible() {
        checkIsElementNotVisible(inputLogin);
    }
    @Step
    // input Password is not visible
    public void checkIsInputPasswordNotVisible() {
        checkIsElementNotVisible(inputPassword);
    }
    @Step
    // button Sign in is not visible
    public void checkIsButtonSignInNotVisible() {
        checkIsElementNotVisible(buttonSignIn);
    }
    @Step
    public HomePage openLoginPageFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }
    @Step
    public boolean isButtonSignInVisible() {
        try {
            return isElementDisplayed(buttonSignIn);
        } catch (Exception e) {
            logger.info("Element button SignIn is displayed -> false");
            return false;
        }
    }
    @Step
    public LoginPage enterTextIntoRegistrationUserNameField(String userName) {
        enterTextIntoInput(inputUserNameRegistration, userName);
        return this;
    }
    @Step
    public LoginPage enterTextIntoRegistrationEmailField(String email) {
        enterTextIntoInput(inputEmailRegistration, email);
        return this;

    }
    @Step
    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        enterTextIntoInput(inputPasswordRegistration, password);
        return this;
    }
    @Step
    public LoginPage checkErrorsMessages(String messages) {
        String[] expectedErrors = messages.split(";");

        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(
                By.xpath(listErrorsMessagesLocator),   expectedErrors.length));

        Util.waitABit(1);
        Assert.assertEquals("Number of messages", expectedErrors.length, listErrorsMessages.size());

        ArrayList<String> actualErrors = new ArrayList<>();
        for (WebElement element : listErrorsMessages) {
            actualErrors.add(element.getText());
        }
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrors.length; i++) {
            softAssertions.assertThat(expectedErrors[i])
                    .as("Error " + i)
                    .isIn(actualErrors);
        }
        softAssertions.assertAll();
        return this;
    }
    @Step
    public LoginPage enterInvalidTextInRegistrationFieldsAndClickButtonSignUp(String invalidText) {
        openLoginPage();
        enterTextIntoInputUsernameRegistration(invalidText);
        enterTextIntoInputEmailRegistration(invalidText);
        enterTextIntoInputPasswordRegistration(invalidText);
        clickOnButtonSignUp();
        return this;
    }
    @Step
    public void checkIsLoginFieldIsNotVisible() {
        checkIsInputLoginNotVisible();
        checkIsInputPasswordNotVisible();
        checkIsButtonSignInNotVisible();
        logger.info("Login field is not visible");
    }
    @Step
    public LoginPage checkIsRedirectToLoginPage() {
        checkUrl();
        getHeader().checkIsHeaderForGuestVisible();
        checkIsInputLoginVisible();
        checkIsInputPasswordVisible();
        checkIsButtonSignInVisible();
        logger.info("Header for guest is visible");
        return this;
    }
    @Step
    public LoginPage checkIsButtonSignInVisible() {
        checkIsElementVisible(buttonSignIn);
        return this;
    }
    @Step
    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }
    @Step
    public LoginPage fillLoginForm(String login, String password) {
        enterTextIntoInputLogin(login);
        enterTextIntoInputPassword(password);
        return new LoginPage(webDriver);
    }
    @Step
    public LoginPage checkErrorMessageInLoginForm(String text) {
        checkTextInElement(errorMessageLoginForm, text);
        return this;
    }

    public LoginPage checkTextInAlertInCenter(String message) {
        Assert.assertEquals("TEXT", message, alertMessageInCenter.getText());
        return this;
    }
}
