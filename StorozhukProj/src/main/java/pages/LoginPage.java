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
    @FindBy(xpath = ".//button[contains(text(),'Sign In')]")
    //цей елемент буде створенний PageFactory в CommonActionsWithElements
    private WebElement buttonSingIn;
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;
    @FindBy(id = "username-register")
    private WebElement inputUserNameRegistration;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistration;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistration;

    @FindBy (xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listErrorsMessages;

    private String listErrorsMessagesLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = "//div[text()='Invalid username/password.']")
    private WebElement validationMessage;
    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement buttonSingUp;
    @FindBy(xpath = ".//div[text()='Username must be at least 3 characters.']")
    private WebElement validationMessageForUserNameRegister;

    @FindBy(xpath = ".//div[text()='You must provide a valid email address.']")
    private WebElement validationMessageForEmailRegister;

    @FindBy(xpath = ".//div[text()='Password must be at least 12 characters.']")
    private WebElement validationMessageForPasswordRegister;
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
    public void openLoginPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Login page was opened " + baseUrl);
        } catch (Exception e) {
            logger.error("Can not open Login Page");
            Assert.fail("Can not open Login Page");
        }
    }
    @Step
    public String getPageHandler() {
        return webDriver.getWindowHandle();
    }
    @Step
    public void enterTextIntoInput(String login) {
        enterTextIntoInput(inputLogin, login); // WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
    }
    @Step
    public void enterTextIntoInputPassword(String password) {
        enterTextIntoInput(inputPassword, password); // WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
    }
    @Step
    public void clickOnButtonSignIn() {
        clickOnElement(buttonSingIn); // WebElement buttonSignIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
    }

    // is button Sign In visible
    @Step
    public boolean isButtonSignInVisible() {
        return isElementDisplayed(buttonSingIn);
    }
    @Step
    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInput(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }
    @Step
    public LoginPage enterTextIntoRegistrationUserNameField(String userName) {
        enterTextIntoInput(inputUserNameRegistration, userName);
        return this;
    }
    @Step
    public LoginPage enterTextIntoRegistrationEmailField(String email){
        enterTextIntoInput(inputEmailRegistration, email);
        return this;
    }
    @Step
    public LoginPage enterTextIntoRegistrationPasswordField(String password){
        enterTextIntoInput(inputPasswordRegistration, password);
        return this;
    }
    @Step
    public LoginPage checkErrorMessages(String messages) {
        //error1;error2 -> [error1, error2]
        String[] expectedErrors = messages.split(";");
        webDriverWait05.until(ExpectedConditions.numberOfElementsToBe(
                By.xpath(listErrorsMessagesLocator), expectedErrors.length));

        Util.waitABit(1);
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
    public boolean isAlertTextVisible() {
        return isElementDisplayed(validationMessage);
    }
    @Step
    public boolean isInputUserNameNotVisible() {
        return isElementDisplayed(inputLogin);
    }
    @Step
    public boolean isInputPasswordNotVisible() {
        return isElementDisplayed(inputPassword);
    }
    @Step
    public boolean isButtonSignInNotVisible() {
        return isElementDisplayed(buttonSingIn);
    }
    @Step
    public void checkIsRedirectToLoginPage() { // перевірка чи ми на сторінці логін
        Assert.assertEquals("Invalid page", "https://aqa-complexapp.onrender.com/", webDriver.getCurrentUrl());
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
    public void clickOnButtonSignUp() {
        clickOnElement(buttonSingUp);
    }
    @Step
    public boolean isValidationMessageForUserNameRegisterInputDisplayed() {
        return isElementDisplayed(validationMessageForUserNameRegister);
    }
    @Step
    public boolean isValidationMessageForEmailRegisterInputDisplayed() {
        return isElementDisplayed(validationMessageForEmailRegister);
    }

    public boolean isValidationMessageForPasswordRegisterInputDisplayed() {
        return isElementDisplayed(validationMessageForPasswordRegister);
    }
    @Step
    public void checkTextInValidationMessageForUserNameRegisterInput(String username){
        Assert.assertEquals("Username Text is invalid ", username, validationMessageForUserNameRegister.getText());
    }
    @Step
    public void checkTextInValidationMessageForEmailRegisterInput(String email) {
        Assert.assertEquals("Email Text is invalid ", email, validationMessageForEmailRegister.getText());
    }
    @Step
    public void checkTextInValidationMessageForPasswordRegisterInput(String password) {
        Assert.assertEquals("Password Text is invalid ", password, validationMessageForPasswordRegister.getText());
    }
    @Step
    public LoginPage checkIsFieldLoginEmpty() {
        Assert.assertEquals("Field Login is not empty", "", inputLogin.getText());
        return this;
    }
    @Step
    public LoginPage checkIsFieldPasswordEmpty() {
        Assert.assertEquals("Field Password is not empty", "", inputPassword.getText());
        return this;
    }
    @Step
    public LoginPage checkIsButtonSignInVisible() {
        Assert.assertTrue("Button SignIn is not visible", isButtonSignInVisible());
        return this;
    }

    public LoginPage checkTextInAlertInCenter(String message) {
    //    checkTextInElement(alertMessageInCenter.getText());
        Assert.assertEquals("Message in center of page ",message, alertMessageInCenter.getText());
        return this;
    }
}
