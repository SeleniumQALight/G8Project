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

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    public WebElement buttonSignIn;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement buttonSignUp;

    @FindBy(id = "username-register")
    private WebElement inputUsernameRegister;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegister;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegister;

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listErrorMessages;
    private String listErrorMessagesLocator
            = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";


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
            logger.error("Can not open login page");
            Assert.fail("Can not open login page");
        }
    }

    @Step
    public void enterTextIntoInputLogin(String login) {
        enterTextIntoInput(inputUsername, login);
    }

    @Step
    public void enterTextIntoInputPass(String pass) {
        enterTextIntoInput(inputPassword, pass);
    }

    @Step
    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    @Step
    public LoginPage clickOnButtonSignup() {
        clickOnElement(buttonSignUp);
        return this;
    }
    @Step
    public void refreshPage() {
        refreshPages();
    }
    @Step
    public boolean isMessageFailLogin() {
        return isElementDisplayed(divFailSignIn);
    }
    @Step
    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPass(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }
    @Step
    public HomePage redirectToHomePage() {
        return new HomePage(webDriver);
    }
    @Step
    public HeaderElement redirectToHeaderElement() {
        return new HeaderElement(webDriver);
    }
    @Step
    public LoginPage enterIntoUsernameRegistration(String username) {
        enterTextIntoInput(inputUsernameRegistration, username);
        return this;
    }
    @Step
    public LoginPage enterIntoEmailRegistration(String email) {
        enterTextIntoInput(inputEmailRegistration, email);
        return this;
    }
    @Step
    public LoginPage enterIntoPasswordRegistration(String pass) {
        enterTextIntoInput(inputPasswordRegistration, pass);
        return this;
    }

    //----------------------------------------------------
    @Step
    public LoginPage checkIsInputUsernameUnvisible() {
        checkIsElementUnvisible(inputUsername, "inputUsername");
        return this;
    }

    @Step
    public LoginPage checkIsInputPasswordUnvisible() {
        checkIsElementUnvisible(inputPassword, "inputPassword");
        return this;
    }
//--------------------------------------------------------
@Step
    public LoginPage checkIsRedirectOnLoginPage() {
        checkIsElementVisible(buttonSignIn, "buttonSignIn");
        return this;
    }
    @Step
    public LoginPage checkIsInputUsernameVisible() {
        checkIsElementVisible(inputUsername, "inputUsername");
        return this;
    }
    @Step
    public LoginPage checkIsInputPasswordVisible() {
        checkIsElementVisible(inputPassword, "inputPassword");
        return this;
    }
    @Step
    public LoginPage checkIsButtonSignInVisible() {
        checkIsElementVisible(buttonSignIn, "buttonSignIn");
        return this;
    }
    @Step
    public LoginPage checkIsButtonSignInUnvisible() {
        checkIsElementUnvisible(buttonSignIn, "buttonSignIn");
        return new LoginPage(webDriver);
    }

    @Step
    public LoginPage checkIsWarningUsernameRegistrationVisible() {
        checkIsElementVisible(divWarningUsernameRegistration, "divWarningUsernameRegistration");
        return this;
    }

    public LoginPage checkIsWarningEmailRegistrationVisible() {
        checkIsElementVisible(divWarningEmailRegistration, "divWarningEmailRegistration");
        return this;
    }

    public LoginPage checkIsWarningPasswordRegistrationVisible() {
        checkIsElementVisible(divWarningPasswordRegistration, "divWarningPasswordRegistration");
        return this;
    }

    public void checkIsRedirectLoginPage() {
        checkUrl();
    }

    //is button SignIn

    public LoginPage enterTextIntoRegistrationUsernameField(String value) {
        enterTextIntoInput(inputUsernameRegister, value);
        return this;
    }

    public LoginPage enterTextIntoRegistrationEmailField(String value) {
        enterTextIntoInput(inputEmailRegister, value);
        return this;
    }

    public LoginPage enterTextIntoRegistrationPasswordField(String value) {
        enterTextIntoInput(inputPasswordRegister, value);
        return this;
    }


    public LoginPage checkErrorMessage(String massages) {

        // eror1; error2; error3 -> [error1, error2, error3]

        String[] expectedErrors = massages.split(";");
        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorMessagesLocator), expectedErrors.length));

        Util.waitABit(1);
        Assert.assertEquals("Number of messages", expectedErrors.length, listErrorMessages.size());

        ArrayList<String> actualErrors = new ArrayList<>();
        for (WebElement element : listErrorMessages) {
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
}
