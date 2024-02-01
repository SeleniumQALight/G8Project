package pages;

import data.TestData;
import io.qameta.allure.Step;
import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//button[text()='Sign In']")
    // this element will crreated in PageFactory in CommonActionsWithElements.java
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;
    @FindBy(id = "username-register")// xpath = ".//input[@id='username-register']"
    private WebElement inputUsernameRegistration;

    @FindBy(xpath = ".//input[@id='username-register']/..//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private WebElement validationMessageForUserNameRegistrationField;
    @FindBy(id = "password-register")//xpath = ".//input[@id='password-register']"
    private WebElement inputPasswordRegistration;
    @FindBy(id = "email-register")//xpath = ".//input[@id='email-register']"
    private WebElement inputEmailRegistration;
    @FindBy(xpath = ".//input[@id='email-register']/following-sibling::div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private WebElement validationMessageForEmailRegistrationField;
    @FindBy(xpath = ".//input[@id='password-register']/following-sibling::div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private WebElement validationMessageForPasswordRegistrationField;
    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement buttonSignUp;

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listErrorsMessages;
    private String listErrorsMessagesLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public void checkIsRedirectToLoginPage() {
        checkUrl();
    }

    @Step
    public void openLoginPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not open Login Page");
            Assert.fail("Can not open Login Page");
        }
    }

    @Step
    public void enterTextIntoInputLogin(String login) {
        // WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        enterTextIntoInput(inputLogin, login);
    }

    @Step
    public void enterTextIntoInputPassword(String password) {
        //WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        enterTextIntoInput(inputPassword, password);
    }

    @Step
    public void clickOnButtonSignIn() {
        //WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[text()='Sign In']"));
        clickOnElement(buttonSignIn);
    }

    @Step
    public boolean isWarningMessageVisible() {
        try {
            WebElement warningMessage = webDriver.findElement(By.xpath(".//div[text()='Invalid username/password.']"));
            return isElementDisplayed(warningMessage);
        } catch (Exception e) {
            return false;
        }
    }

    @Step
    public boolean isButtonSignInVisible() {
        //try {
        //WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[text()='Sign In']"));
        return isElementDisplayed(buttonSignIn);
        //} catch (Exception e) {
        //    return false;
        // }
    }

    //is button Sign In visible
    @Step
    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    //is username field visible
    @Step

    public void checkUsernameFieldNotVisible() {
        checkIsElementNotVisible(inputLogin);
    }
    @Step
    public void checkUsernameFieldVisible() {
        checkIsElementVisible(inputLogin);
    }

    //is password field visible
    @Step
    public void checkPasswordFieldNotVisible() {
        checkIsElementNotVisible(inputPassword);
    }
    @Step
    public void checkPasswordFieldVisible() {
        checkIsElementVisible(inputPassword);
    }
    @Step
    public void checkSignInButtonNotVisible() {
        checkIsElementNotVisible(buttonSignIn);
    }

    @Step
    //enter text into input username registration
    public LoginPage enterTextIntoInputUsernameRegistration(String username) {
        enterTextIntoInput(inputUsernameRegistration, username);
        return this;
    }
    @Step
    public boolean isValidationMessageForUserNameFieldVisible() {
        return isElementDisplayed(validationMessageForUserNameRegistrationField);
    }
    @Step
    public LoginPage checkTextValidationMessageForUserNameRegistrationField(String message) {
        checkTextInElement(validationMessageForUserNameRegistrationField, message);
        return this;
    }
    @Step
    public LoginPage enterTextIntoInputPasswordRegistration(String password) {
        enterTextIntoInput(inputPasswordRegistration, password);
        return this;
    }
    @Step
    public boolean isValidationMessageForPasswordFieldVisible() {
        return isElementDisplayed(validationMessageForPasswordRegistrationField);
    }
    @Step
    public void checkTextValidationMessageForPasswordRegistrationField(String message) {
        checkTextInElement(validationMessageForPasswordRegistrationField, message);
    }
    @Step
    public boolean isValidationMessageForEmailFieldVisible() {
        return isElementDisplayed(validationMessageForEmailRegistrationField);
    }
    @Step
    public LoginPage enterTextIntoInputEmailRegistration(String email) {
        enterTextIntoInput(inputEmailRegistration, email);
        return this;
    }
    @Step
    public void checkTextValidationMessageForEmailRegistrationField(String message) {
        checkTextInElement(validationMessageForEmailRegistrationField, message);
    }
    @Step
    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
    }
    @Step
    public LoginPage checkErrorsMessages(String messages) {
        //String[] errors = messages.split(";"); error1;error2 -> [error1, error2]
        String[] expectedErrors = messages.split(";");

        webDriverWait05.until(ExpectedConditions.numberOfElementsToBe(
                By.xpath(listErrorsMessagesLocator), expectedErrors.length));

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

        softAssertions.assertAll(); // check all assertion


        return this;
    }

}
