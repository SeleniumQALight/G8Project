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

    @FindBy(xpath = "//button[contains(text(),'Sign In')]")
    //this element will be created by PageFactory in CommonActionsWithElements
    private WebElement buttonSignIn;

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//input[@id = 'username-register']")
    private WebElement inputUsernameRegister;

    @FindBy(id = "email-register")// short variant of xpath

    private WebElement inputEmailRegister;

    @FindBy(xpath = "//input[@id = 'password-register']")
    private WebElement inputPasswordRegister;

    @FindBy(xpath = "//button[contains(text(), 'Sign up')]")
    private WebElement buttonSignUp;

    @FindBy(xpath = "//div[text() = 'Invalid username/password.']")
    private WebElement validationMessagesInvalidUsernamePassword;

    @FindBy(xpath = "//div[text() = 'Username must be at least 3 characters.']")
    private WebElement validationMessageUsernameRegister;

    @FindBy(xpath = "//div[text() = 'You must provide a valid email address.']")
    private WebElement validationMessageEmailRegister;

    @FindBy(xpath = "//div[text() = 'Password must be at least 12 characters.']")
    private WebElement validationMessagePasswordRegister;

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listErrorsMessages;

    private String listErrorsMessagesLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelatedUrl() {
        return "/";
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
    public LoginPage enterTextIntoInputLogin(String login) {
        enterTextIntoInput(inputLogin, login);
        return this;
    }
    @Step
    public LoginPage enterTextIntoInputPassword(String password) {
        enterTextIntoInput(inputPassword, password);
        return this;
    }
    @Step
    public void enterTextIntoInputUsernameRegister(String username) {
        enterTextIntoInput(inputUsernameRegister, username);
    }
    @Step
    public void enterTextIntoInputEmailRegister(String email) {
        enterTextIntoInput(inputEmailRegister, email);
    }
    @Step
    public void enterTextIntoInputPasswordRegister(String password) {
        enterTextIntoInput(inputPasswordRegister, password);
    }
    @Step
    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
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
    public HomePage clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
        return new HomePage(webDriver);
    }
    @Step
    public boolean isButtonSignInVisible() {
        return isElementDisplayed(buttonSignIn);
    }
    @Step
    public boolean isAlertInvalidUsernamePasswordVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//div[text() = 'Invalid username/password.']")).isDisplayed();
            logger.info(state + " is alert visible");
            return state;
        } catch (Exception e) {
            logger.info("Alert is not displayed");
            return false;
        }
    }

    @Step
    public HomePage openLoginPageAndFillLoginFormWithValidCreate() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }
    @Step
    public LoginPage checkIsInputUsernameNotVisible() {
        checkIsElementNotVisible(inputLogin, "Input Username");
        return this;
    }
    @Step
    public LoginPage checkIsInputPasswordNotVisible() {
        checkIsElementNotVisible(inputPassword, "Input Password");
        return this;
    }
    @Step
    public LoginPage checkIsButtonSignInNotVisible() {
        checkIsElementNotVisible(buttonSignIn, "Button Sign In");
        return this;
    }
    @Step
    public LoginPage checkIsRedirectToLoginPage() {
        checkCurrentUrl();
        Assert.assertTrue("Login page is not opened", isButtonSignInVisible());
        return this;
    }
    @Step
    public boolean checkIsValidationMessageUsernameRegisterVisible() {
        return checkIsValidationMessageVisible(validationMessageUsernameRegister);
    }
    @Step
    public boolean checkIsValidationMessageEmailRegisterVisible() {
        return checkIsValidationMessageVisible(validationMessageEmailRegister);
    }
    @Step
    public boolean checkIsValidationMessagePasswordRegisterVisible() {
        return checkIsValidationMessageVisible(validationMessagePasswordRegister);
    }
    @Step
    public void checkIsAlertInvalidUsernamePasswordVisible() {
        checkIsElementVisible(validationMessagesInvalidUsernamePassword);
    }
    @Step
    public LoginPage checkErrorsMessages(String message) {
        // error1; error2; -> [error1, error2]
        String[] expectedErrors = message.split(";");

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

        softAssertions.assertAll();

        return this;
    }
}
