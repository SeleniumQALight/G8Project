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
import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {
    @FindBy(xpath = " .//button[text()='Sign In']") // цей елемент створюється в CommonActionWithElements
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement buttonSignUpForOurApp;

    @FindBy(xpath = "//*[text()='Username must be at least 3 characters.']")
    private WebElement usernameErrorMessage;

    @FindBy(xpath = "//*[text()='You must provide a valid email address.']")
    private WebElement emailErrorMessage;

    @FindBy(xpath = "//*[text()='Password must be at least 12 characters.']")
    private WebElement passwordErrorMessage;

    @FindBy(id = "username-register")
    private WebElement inputUserNameRegistration;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistration;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistration;

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrorsMessages;

    @FindBy(xpath = ".//*[@class='alert alert-danger text-center']")
    private WebElement messageInvalidUsernamePassword;

    private String listOfErrorsMessagesLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy(xpath = ".//*[@class='alert alert-danger text-center']")
    private WebElement alertMessageInCenter;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    @Step
    public void openLoginPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Login page was opened" + baseUrl);
        } catch (Exception e) {
            logger.error("Can not open Login Page");
            Assert.fail("Can not open Login Page");
        }
    }

    @Step
    public void enterTextIntoInputLogin(String login) {
        enterTextIntoInput(inputLogin, login);
    }

    @Step
    public void enterTextIntoInputPassword(String password) {
        enterTextIntoInput(inputPassword, password);
    }

    @Step
    public boolean isInputLoginVisible() {
        return isElementDisplayed(inputLogin);
    }

    @Step
    public boolean isInputPasswordVisible() {
        return isElementDisplayed(inputPassword);
    }

    @Step
    public void clickOnButtonSignIn() {
//        WebElement buttonSignIn = webDriver.findElement(
//                By.xpath("//button[contains(text(),'Sign In')]"));
        clickOnElement(buttonSignIn);
    }

    @Step
    public LoginPage checkIsButtonSignInVisible() {
        checkIsElementVisible(buttonSignIn);
        return this;
    }

    // is button Sign In visible
    @Step
    public LoginPage checkIsButtonSignInNotVisible() {
        checkIsElementNotVisible(buttonSignIn);
        return this;
    }

    @Step
    public boolean isMessageInvalidUsernamePasswordInVisible() {
        try {
            // TODO
            boolean state = webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center']"))
                    .isDisplayed();
            logger.info(state + " is message visible");
            return state;
        } catch (Exception e) {
            logger.info("Message is not displayed");
            return false;
        }
    }

    @Step
    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    // check enty of short data in the registration form
    @Step
    public void fillOutTheLoginFormRegistration() {
        openLoginPage();
        enterTextRegistrationUserNameField(TestData.SHORT_VALUE);
        enterTextRegistrationEmailField(TestData.SHORT_VALUE);
        enterTextRegistrationPasswordField(TestData.SHORT_VALUE);
        clickOnButtonSignUpForOurApp();
    }

    @Step
    public void clickOnButtonSignUpForOurApp() {
        clickOnElement(buttonSignUpForOurApp);
    }

    // check massage about short data in username in the registration form
    @Step
    public LoginPage checkIsMessageAboutShortUsernameInRegistrationFormVisible(String text) {
        checkTextInElement(usernameErrorMessage, text);
        logger.info("Message about short username in the registration form is visible");
        return this;
    }

    // check massage about short data in Email in the registration form
    @Step
    public LoginPage checkIsMessageAboutShortEmailInRegistrationFormVisible(String text) {
        checkTextInElement(emailErrorMessage, text);
        logger.info("Message about short Email in the registration form is visible");
        return this;
    }

    // check massage about short data in password in the registration form
    @Step
    public LoginPage checkIsMessageAboutShortPasswordInRegistrationFormVisible(String text) {
        checkTextInElement(passwordErrorMessage, text);
        logger.info("Message about short password in the registration form is visible");
        return this;
    }

    @Step
    public LoginPage checkIsRedirectOnLoginPage() {
        checkUrl();
        isButtonSignInVisible();
        return this;
    }

    @Step
    public boolean isButtonSignInVisible() {
        return isElementDisplayed(buttonSignIn);
    }

    @Step
    public LoginPage checkIsInputPasswordNotVisible() {
        checkIsElementNotVisible(inputPassword);
        return this;
    }

    @Step
    public LoginPage checkIsInputLoginNotVisible() {
        checkIsElementNotVisible(inputLogin);
        return this;
    }

    @Step
    public LoginPage enterTextRegistrationUserNameField(String userName) {
        enterTextIntoInput(inputUserNameRegistration, userName);
        return this;
    }

    @Step
    public LoginPage enterTextRegistrationEmailField(String email) {
        enterTextIntoInput(inputEmailRegistration, email);
        return this;
    }

    @Step
    public LoginPage enterTextRegistrationPasswordField(String password) {
        enterTextIntoInput(inputPasswordRegistration, password);
        return this;
    }

    @Step
    public LoginPage checkErrorsMessages(String massages) {
        //error1;error2 -> [error1, error2]
        String[] expectedErrors = massages.split(";");
        webDriverWaite05.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfErrorsMessagesLocator), expectedErrors.length));

        Util.waitABit(1);
        Assert.assertEquals("Number of messages", expectedErrors.length, listOfErrorsMessages.size());


        ArrayList<String> actualErrors = new ArrayList<>();
        for (WebElement element : listOfErrorsMessages) {
            actualErrors.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrors.length; i++) {
            softAssertions.assertThat(expectedErrors[i])
                    .as("Error " + i)
                    .isIn(actualErrors);
        }

        softAssertions.assertAll(); //check all assertion
        return this;
    }

    @Step
    public LoginPage checkErrorsMessagesInLogin(String text) {
       checkTextInElement(messageInvalidUsernamePassword, text);
        return this;
    }

    public LoginPage checkTextInAlertInCenter(String message) {
        checkTextInElement(alertMessageInCenter, message);
//        Assert.assertEquals("Message in center of page", message, alertMessageInCenter.getText());
        return this;
    }

    public LoginPage checkNumberOfErrorsMessagesInRegistrationForm(int numberOfErrors) {
        Assert.assertEquals("Number of messages", numberOfErrors, listOfErrorsMessages.size());
        return this;
    }

    public LoginPage checkTextInAlertLoginInRegistrationForm(String errorMessageInLogin) {
        checkTextInElement(usernameErrorMessage, errorMessageInLogin);
        return this;
    }

    public LoginPage checkTextInAlertEmailInRegistrationForm(String errorMessageInEmail) {
        checkTextInElement(emailErrorMessage, errorMessageInEmail);
        return this;
    }

    public LoginPage checkTextInAlertPasswordInRegistrationForm(String errorMessageInPassword) {
        checkTextInElement(passwordErrorMessage, errorMessageInPassword);
        return this;
    }
}
