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

    @FindBy(xpath = ".//button[contains(text(),'Sign In')]")
    private WebElement buttonSignIn; // этот элемент создется PageFactory в CommonActionsWithElements

    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//div[text()='Invalid username/password.']")
    private WebElement warningMessage;

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

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listErrorsMessages;

    @FindBy(xpath = ".//div[contains(@class,'alert-danger') and not (contains(@class,'liveValidateMessage'))]")
    private WebElement errorMessageLoginForm;

    private String listErrorsMessagesLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";


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

    @Step
    public LoginPage enterTextIntoInputLogin(String login) {
//        WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        enterTextIntoInput(inputLogin, login);
        return this;
    }

    @Step
    public LoginPage checkIsInputLoginVisible() {
        checkIsElementVisible(inputLogin);
        return this;
    }

    @Step
    public LoginPage enterTextIntoInputPassword(String password) {
//        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        enterTextIntoInput(inputPassword, password);
        return this;
    }

    @Step
    public void checkIsInputPasswordVisible() {
        checkIsElementVisible(inputPassword);
    }

    public LoginPage enterTextIntoInputUsernameRegistration(String username) {
        enterTextIntoInput(inputUsernameAtRegistration, username);
        return this;
    }

    @Step
    public LoginPage enterTextIntoInputEmailRegistration(String email) {
        enterTextIntoInput(inputEmailAtRegistration, email);
        return this;
    }

    @Step
    public LoginPage enterTextIntoInputPasswordRegistration(String password) {
        enterTextIntoInput(inputPasswordAtRegistration, password);
        return this;
    }

    @Step
    public void clickOnButtonSignIn() {
//        WebElement signInButton = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
        clickOnElement(buttonSignIn);
    }

    @Step
    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
    }

    @Step
    public boolean isWarningMessageVisible() {
//        WebElement warningMessage = webDriver.findElement(By.xpath(".//div[text()='Invalid username/password.']"));
        return isElementDisplayed(warningMessage);

    }

    // is button Sign in visible
    @Step
    public boolean isButtonSignInVisible() {
//        WebElement buttonSignIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
        return isElementDisplayed(buttonSignIn);
    }

    @Step
    public LoginPage checkIsButtonSignInVisible() {
        checkIsElementVisible(buttonSignIn);
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

    // input Login is not visible
    @Step
    public void checkIsInputLoginNotVisible() {
        checkIsElementNotVisible(inputLogin);
    }

    // input Password is not visible
    @Step
    public void checkIsInputPasswordNotVisible() {
        checkIsElementNotVisible(inputPassword);
    }

    // button Sign in is not visible
    @Step
    public void checkIsButtonSignInNotVisible() {
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
    public LoginPage checkIsRedirectToHomePage() {
        checkUrl();
        getHeader().checkIsHeaderForGuestVisible();
        checkIsInputLoginVisible();
        checkIsInputPasswordVisible();
        checkIsButtonSignInVisible();
        logger.info("Header for guest is visible");
        return this;
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }

    @Step
    public LoginPage checkErrorsMessages(String messages) {
        //error1;error2 -> [error1, error2]
        String[] expectedErrors = messages.split(";");
        webDriverWait05.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorsMessagesLocator), expectedErrors.length));

        Util.waitABit(1);
        Assert.assertEquals("Number of messages", expectedErrors.length, listErrorsMessages.size());

        webDriverWait05.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorsMessagesLocator), expectedErrors.length));

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

        softAssertions.assertAll(); // проверка всех ошибок
        return null;
    }

    @Step
    public LoginPage checkErrorMessageInLoginForm(String text) {
        checkTextInElement(errorMessageLoginForm, text);
        return this;
    }
}
