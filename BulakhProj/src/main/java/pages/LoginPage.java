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

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class LoginPage  extends ParentPage{

    @FindBy(xpath = "//button[contains(text(),'Sign In')]")
    private WebElement buttonSingIn; // цей елемент створиться PageFactory в батьківському класі CommonActionsWithElements

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//div[@class=\"alert alert-danger text-center\" and contains(text(),\"Invalid username/password.\")]")
    private WebElement invalidLoginMessage;

    @FindBy(xpath = "//input[@id='username-register']")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@id='email-register']")
    private WebElement inputEmail;

    @FindBy(xpath = "//input[@id='password-register']")
    private WebElement inputPassword1;

    @FindBy(xpath = "//div[contains(text(), 'Username must be at least 3 characters.')]")
    private WebElement validationMessageUsername;

    @FindBy(xpath = "//div[contains(text(), 'You must provide a valid email address.')]")
    private WebElement validationMessageEmail;

    @FindBy(xpath = "//div[contains(text(), 'Password must be at least 12 characters.')]")
    private WebElement validationMessagePassword;

    @FindBy(xpath = "//button[contains(text(),'Sign up for OurApp')]")
    private WebElement buttonSignUp1;

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement loginInput;


    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement passwordInput;

    @FindBy(id = "username-register") // xpath = ".//*[@id='username-register']"
    private WebElement inputUserNameRegistration;

    @FindBy(id = "email-register") // xpath = ".//*[@id='email-register']"
    private WebElement inputEmailRegistration;

    @FindBy(id = "password-register") // xpath = ".//*[@id='password-register']"
    private WebElement inputPasswordRegistration;

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrorMessages;

    @FindBy(xpath = ".//div[@class='alert alert-danger text-center']")
    private WebElement alertMessageCenter;


    private String listOfErrorMessagesLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";




    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }


    @Step
    public void openLoginPage() {
        try{
            webDriver.get(baseUrl);
            logger.info("Login page was opened " + baseUrl);
        }catch (Exception e){
            logger.error("Can not open login page");
            Assert.fail("Can not open login page");
        }
    }

    public void checkIsRedirectToLoginPage() {
        checkUrl();
    }
    @Step
    public void enterTextInToInputLogin(String login) {
//        WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        enterTextInToInput(inputLogin, login);
    }
    @Step
    public void enterTextInToInputPassword(String password) {
//        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        enterTextInToInput(inputPassword, password);
    }
    @Step
    public void clickOnButtonSingIn() {
//        WebElement buttonSingIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
        clickOnElement(buttonSingIn);
    }

    // is button Sing In visible
    @Step
    public boolean isButtonSingInVisible() {
//        WebElement buttonSingIn =
//                webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
        return isElementDisplayed(buttonSingIn);
    }

    // is input Login visible
    @Step
    public boolean isInputLoginVisible() {
        return isElementDisplayed(inputLogin);
    }

    // is input Password visible
    @Step
    public boolean isInputPasswordVisible() {
        return isElementDisplayed(inputPassword);
    }

    @Step
    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextInToInputLogin(TestData.VALID_LOGIN_UI);
        enterTextInToInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSingIn();
        return new HomePage(webDriver);
    }
    @Step
    public boolean isInvalidLoginMessageDisplayed(){
//        WebElement invalidLoginMessage = webDriver.findElement(By.xpath("//div[@class=\"alert alert-danger text-center\" and contains(text(),\"Invalid username/password.\")]"));
        return isElementDisplayed(invalidLoginMessage);
    }
    @Step
    public LoginPage fillRegistrationForm(String userName, String Email, String Password) {
        enterTextInToInput(inputUserName, userName);
        enterTextInToInput(inputEmail, Email);
        enterTextInToInput(inputPassword1, Password);
        return this;

    }
    @Step
    public LoginPage checkLoginInputIsNotVisible() {
        Assert.assertFalse("Login input is displayed", isElementDisplayed(loginInput));
        return this;
    }
    @Step
    public LoginPage checkPasswordInputIsNotVisible() {
        Assert.assertFalse("Password input is displayed", isElementDisplayed(passwordInput));
        return this;
    }
    @Step
    public void checkSignInButtonIsNotVisible() {
        Assert.assertFalse("Sign In button is displayed", isElementDisplayed(buttonSingIn));
    }
    @Step
    public LoginPage clickOnButtonSingUp() {
        clickOnElement(buttonSignUp1);
        return this;
    }
    @Step
    public LoginPage checkValidationMessageForUsername() {
        checkTextInElement(validationMessageUsername, "Username must be at least 3 characters.");
        return this;
    }
    @Step
    public LoginPage checkValidationMessageForEmail() {
        checkTextInElement(validationMessageEmail, "You must provide a valid email address.");
        return this;
    }
    @Step
    public void checkValidationMessageForPassword() {
        checkTextInElement(validationMessagePassword, "Password must be at least 12 characters.");
    }

    @Step
    public LoginPage enterTextIntoRegistrationUsernameField(String username) {
        enterTextInToInput(inputUserNameRegistration, username);
        return this;
    }

    @Step
    public LoginPage enterTextIntoRegistrationEmailField(String email) {
        enterTextInToInput(inputEmailRegistration, email);
        return this;
    }
    @Step
    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        enterTextInToInput(inputPasswordRegistration, password);
        return this;
    }

    @Step
    public LoginPage checkErrorMessages(String messages) {
        // error1;error2 -> [error1, error2]
        String[] expectedErrors = messages.split(";");
        webDriverWait05.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfErrorMessagesLocator), expectedErrors.length));

        Util.waitABit(1);
        Assert.assertEquals("Number of messages", expectedErrors.length, listOfErrorMessages.size());

        ArrayList<String> actualErrors = new ArrayList<>();
        for (WebElement element : listOfErrorMessages) {
            actualErrors.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrors.length; i++) {
            softAssertions.assertThat(expectedErrors[i])
                    .as("Error " + i)
                    .isIn(actualErrors);
        }


        softAssertions.assertAll(); // перевірка всіх асертів

        return this;
    }

    @Step
    public LoginPage checkErrorMessageInLoginForm(String text) {
        checkTextInElement(invalidLoginMessage, text);
        return this;

    }


    public LoginPage checkTextInAlertCenter(String message) {
        Assert.assertEquals("Massage in center of page ", message, alertMessageCenter.getText());
        return this;
    }
}