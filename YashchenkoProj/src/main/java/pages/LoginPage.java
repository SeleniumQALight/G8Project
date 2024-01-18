package pages;

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

import static data.TestData.DEFAULT_VALID_LOGIN_UI;
import static data.TestData.DEFAULT_VALID_PASSWORD_UI;

public class LoginPage extends ParentPage {

    //Find elements by xpath

    @FindBy(xpath = "//button[contains(text(),'Sign In')]") //This element will be initialized in PageFactory
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = "//div[contains(text(),'Invalid username/password.')]")
    private WebElement invalidAlert;

    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputEmail;

    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement registrationInputLogin;

    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement registrationInputPassword;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement buttonSignUp;

    @FindBy(xpath = ".//div[text()='Username must be at least 3 characters.']")
    private WebElement userNameRegValidation;

    @FindBy(xpath = ".//div[text()='You must provide a valid email address.']")
    private WebElement emailRegValidation;

    @FindBy(xpath = ".//div[text()='Password must be at least 12 characters.']")
    private WebElement passwordRegValidation;

    @FindBy(xpath = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listErrorMessages;

    private String listErrorMessagesLocator = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    //Constructor
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public void checkIsRedirectedToLoginPage() {
        checkCurrentUrl();
        checkIsButtonSignInVisible();
    }

    public void openLoginPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Login page was opened " + baseUrl);
        } catch (Exception e) {
            logger.error("Can not open login page");
            Assert.fail("Can not open login page");
        }

    }

    public void enterTextIntoInputLogin(String login) {
//        WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@placeholder='Username']")); //For example
        enterTextIntoInput(inputLogin, login);
    }

    public void enterTextIntoInputPassword(String password) {
        enterTextIntoInput(inputPassword, password);
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public boolean isButtonSignInVisible() {
        return isElementDisplayed(buttonSignIn);
    }

    public LoginPage checkIsButtonSignInVisible() {
        checkIsElementVisible(buttonSignIn);
        return this;
    }

    public LoginPage checkIsButtonSignInNotVisible() {
        checkIsElementInvisible(buttonSignIn);
        return this;
    }

    public LoginPage checkIsInputPasswordVisible() {
        checkIsElementVisible(inputPassword);
        return this;
    }

    public LoginPage checkIsPasswordInputNotVisible() {
        checkIsElementInvisible(inputPassword);
        return this;
    }

    public LoginPage checkIsInputLoginVisible() {
        checkIsElementVisible(inputLogin);
        return this;
    }

    public LoginPage checkIsLoginInputNotVisible() {
        checkIsElementInvisible(inputLogin);
        return this;
    }

    public boolean isInvalidUserNamePasswordAlertVisible() {
        return isElementDisplayed(invalidAlert);
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCreds() {
        openLoginPage();
        enterTextIntoInputLogin(DEFAULT_VALID_LOGIN_UI);
        enterTextIntoInputPassword(DEFAULT_VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }


    public LoginPage enterTextIntoRegistrationInputLogin(String login) {
        enterTextIntoInput(registrationInputLogin, login);
        return this;
    }

    public LoginPage enterTextIntoRegistrationInputPassword(String password) {
        enterTextIntoInput(registrationInputPassword, password);
        return this;
    }

    public LoginPage enterTextIntoRegistrationInputEmail(String email) {
        enterTextIntoInput(inputEmail, email);
        return this;
    }

    public LoginPage checkTextInRegistrationInputLogin(String text) {
        checkTextInElement(userNameRegValidation, text);
        return this;
    }

    public LoginPage checkTextInRegistrationInputEmail(String text) {
        checkTextInElement(emailRegValidation, text);
        return this;
    }

    public LoginPage checkTextInRegistrationInputPassword(String text) {
        checkTextInElement(passwordRegValidation, text);
        return this;
    }

    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
    }

    public LoginPage checkIsValidationMessageForRegistrationInputLoginVisible() {
        checkIsElementVisible(userNameRegValidation);
        return this;
    }

    public LoginPage checkIsValidationMessageForRegistrationInputEmailVisible() {
        checkIsElementVisible(emailRegValidation);
        return this;
    }

    public LoginPage checkIsValidationMessageForRegistrationInputPasswordVisible() {
        checkIsElementVisible(passwordRegValidation);
        return this;
    }

    public LoginPage checkErrorMessages(String messages){
        String[] expectedErrors = messages.split(";");
        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(
                By.xpath(listErrorMessagesLocator), expectedErrors.length));

        Util.waitABit(1);
        Assert.assertEquals("Number of messages", expectedErrors.length, listErrorMessages.size());
        ArrayList<String> actualErrors = new ArrayList<>();
        for (WebElement element : listErrorMessages) {
            actualErrors.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrors.length; i++) {
            softAssertions.assertThat(actualErrors.get(i))
                    .as("Error " + i)
                    .isIn(actualErrors);
        }
        softAssertions.assertAll();
        return this;
    }
}
