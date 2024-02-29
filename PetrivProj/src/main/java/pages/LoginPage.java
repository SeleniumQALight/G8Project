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

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//button[contains(text(),'Sign In')]") // цей елемент створиться PageFactory в CommonActionsWithElements
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputUserNameRegistration;

    @FindBy(id = "email-register") // інший варіант пошуку елемента (одразу по ід)
    private WebElement inputEmailRegistration;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistration;

    @FindBy(xpath = ".//form[@id='registration-form']//button[@type='submit']")
    private WebElement buttonSignUp;

    @FindBy(xpath = ".//input[@id='username-register']/following-sibling::div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private WebElement validationMessageForUserNameRegister;

    @FindBy(xpath = ".//input[@id='email-register']/following-sibling::div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private WebElement validationMessageForEmailRegister;

    @FindBy(xpath = ".//input[@id='password-register']/following-sibling::div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private WebElement validationMessageForPasswordRegister;
    @FindBy(xpath = ".//div[contains(text(),'Invalid username/password.')]")
    private WebElement invalidUsernameOrPasswordMessage;

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listErrorMessages;
    private String listErrorMessagesLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy(xpath = ".//div[@class='alert alert-danger text-center']")
    private WebElement alertMessageInCenter;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }

    @Step
    public void openLoginPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Login page was opened " + baseUrl);
        } catch (Exception e) {
            logger.error("Can not open login page");
            Assert.fail("Can not open login page");
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
    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    // is button Sign In visible
    @Step
    public boolean isButtonSignInVisible() {
        return isElementDisplayed(buttonSignIn);
    }

    @Step
    public boolean isInputUserNameVisible() {
        return isElementDisplayed(inputLogin);
    }
    @Step
    public boolean isInputPasswordVisible() {
        return isElementDisplayed(inputPassword);
    }
    @Step
    public void isInputUserNameNotVisible() {
        checkIsElementNotVisible(inputLogin);
    }
    @Step
    public void isInputPasswordNotVisible() {
        checkIsElementNotVisible(inputPassword);
    }
    @Step
    public void isButtonSignInNotVisible() {
        checkIsElementNotVisible(buttonSignIn);
    }
    @Step
    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }
    @Step
    public LoginPage checkIsRedirectToLoginPage() {
        checkUrl();
        Assert.assertTrue("Invalid page - not Login Page", isButtonSignInVisible());
        return this;
    }
    @Step
    public LoginPage enterTextIntoRegistrationUserNameField(String username) {
        enterTextIntoInput(inputUserNameRegistration, username);
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
    public void clickOnButtonSignUp()  {
        clickOnElement(buttonSignUp);
    }
    @Step
    public boolean isValidationMessageForUserNameRegisterInputDisplayed() {
        return isElementDisplayed(validationMessageForUserNameRegister);
    }
    @Step
    public LoginPage checkTextInValidationMessageForUserNameRegisterInput(String text) {
        checkTextInElement(validationMessageForUserNameRegister, text);
        return this;
    }
    @Step
    public boolean isValidationMessageForEmailRegisterInputDisplayed() {
        return isElementDisplayed(validationMessageForEmailRegister);
    }
    @Step
    public LoginPage checkTextInValidationMessageForEmailRegisterInput(String text) {
        checkTextInElement(validationMessageForEmailRegister, text);
        return this;
    }
    @Step
    public boolean isValidationMessageForPasswordRegisterInputDisplayed() {
        return isElementDisplayed(validationMessageForPasswordRegister);
    }
    @Step
    public LoginPage checkTextInValidationMessageForPasswordRegisterInput(String text) {
        checkTextInElement(validationMessageForPasswordRegister, text);
        return this;
    }
    @Step
    public LoginPage checkErrorMessages(String messages) {
        // error1;error2 -> [error1, error2]
        String[] expectedErrors = messages.split(";");
        webDriverWait05.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorMessagesLocator), expectedErrors.length));

        Util.waitABit(1);
        Assert.assertEquals("Number of messages", expectedErrors.length, listErrorMessages.size());

        ArrayList<String> actualErrors = new ArrayList<>();
        for (WebElement element: listErrorMessages) {
            actualErrors.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrors.length; i++) {
            softAssertions.assertThat(expectedErrors[i])
                    .as("Error " + i)
                    .isIn(actualErrors);
        }

        softAssertions.assertAll(); // check all assertions

        return this;
    }
    @Step
    public LoginPage checkIsInvalidUsernameOrPasswordMessageVisible() {
        checkIsElementVisible(invalidUsernameOrPasswordMessage);
        return this;
    }

    @Step
    public LoginPage checkTextInAlertInCenter(String message) {
        checkTextInElement(alertMessageInCenter, message);
        // Assert.assertEquals("Message in center of page ", message, alertMessageInCenter.getText());
        return this;
    }
}
