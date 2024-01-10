package pages;

import libs.TestData;
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
    @FindBy(xpath = ".//button[contains(text(),'Sign In')]")
    // цей елемент створиться PageFactory в CommonActionsWithElements
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(id = "username-register")   // xpath = ".//*[@id='username-register']"
    private WebElement inputUserNameRegistration;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistration;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistration;

    @FindBy(xpath = ".//button[contains(text(),'Sign up for OurApp')]")
    private WebElement buttonSignUpForOurApp;


//    @FindBy(xpath = ".//div[text()='Username must be at least 3 characters.']")
//    private WebElement errorMessageUsername;
//
//    @FindBy(xpath = ".//div[text()='You must provide a valid email address.']")
//    private WebElement errorMessageEmail;
//
//    @FindBy(xpath = ".//div[text()='Password must be at least 12 characters.']")
//    private WebElement errorMessagePassword;


    @FindBy(xpath = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listErrorsMessages;
    private String listErrorsMessagesLocator
            = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public void openLoginPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Login page was opened " + baseUrl);
        } catch (Exception e) {
            logger.error("Can not open Login Page");
            Assert.fail("Can not open Login Page");
        }
    }

    public void checkIsRedirectToLoginPage() {
        checkUrl();
    }


    public void enterTextIntoInputLogin(String login) {
        //WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        enterTextIntoInput(inputLogin, login);
    }

    public void enterTextIntoInputPassword(String password) {
        //WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        enterTextIntoInput(inputPassword, password);
    }

    public void enterTextRegUsernameInput(String username) {
            enterTextIntoInput(regUsername, username);
    }

    public void enterTextRegEmailInput(String email) {
            enterTextIntoInput(regEmail, email);
    }

    public void enterTextRegPasswordInput(String password) {
             enterTextIntoInput(regPassword, password);
    }

    public void clickOnButtonSignIn() {
        //  WebElement buttonSignIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
        clickOnElement(buttonSignIn);
    }

    public void clickOnButtonSignUpForOurApp() {
        clickOnElement(buttonSignUpForOurApp);
    }


    // is button Sign In visible
    public boolean isButtonSignInVisible() {
        //WebElement buttonSignIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
        return isElementDisplayed(buttonSignIn);
    }

    public boolean isInputLoginVisible() {
        return isElementDisplayed(inputLogin);
    }

    public boolean isInputPasswordVisible() {
        return isElementDisplayed(inputPassword);
    }

    public boolean isErrorMessageVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//div[text() = 'Invalid username/password.']")).isDisplayed();
            logger.info(state + " is alert visible");
            return state;
        } catch (Exception e) {
            logger.info("Alert is not displayed");
            return false;
        }
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public LoginPage enterTextIntoRegistrationUserNameField(String UserName) {
        enterTextIntoInput(inputUserNameRegistration, UserName);
        return this;
    }


    public LoginPage enterTextIntoRegistrationEmailField(String Email) {
        enterTextIntoInput(inputEmailRegistration, Email);
        return this;
    }


    public LoginPage enterTextIntoRegistrationPasswordField(String Password) {
        enterTextIntoInput(inputPasswordRegistration, Password);
        return this;
    }


    public LoginPage checkErrorsMessage(String messages) {
        String[] expectedErrors = messages.split(";");



        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(
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

        softAssertions.assertAll(); // check all   assertions

        return this;
    }

    public LoginPage openLoginPageAndFillLoginFormWithInvalidCred() {
        openLoginPage();
        enterTextRegUsernameInput(TestData.INVALID_USERNAME_UI);
        enterTextRegEmailInput(TestData.INVALID_EMAIL_UI);
        enterTextRegPasswordInput(TestData.INVALID_PASSWORD_UI);
        clickOnButtonSignUpForOurApp();
        return new LoginPage(webDriver);
    }


    public LoginPage checkIsErrorMessageDisplayed() {
        checkIsElementVisible(errorMessageUsername);
        checkIsElementVisible(errorMessageEmail);
        checkIsElementVisible(errorMessagePassword);
        return this;
    }


}