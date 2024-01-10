package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//button[text()='Sign In']")
    // this element will crreated in PageFactory in CommonActionsWithElements.java
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;
    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputUsernameRegistration;

    @FindBy(xpath = ".//input[@id='username-register']/..//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private WebElement validationMessageForUserNameRegistrationField;
    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputPasswordRegistration;
    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputEmailRegistration;
    @FindBy(xpath = ".//input[@id='email-register']/following-sibling::div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private WebElement validationMessageForEmailRegistrationField;
    @FindBy(xpath = ".//input[@id='password-register']/following-sibling::div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private WebElement validationMessageForPasswordRegistrationField;
    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement buttonSignUp;


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

    public void openLoginPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not open Login Page");
            Assert.fail("Can not open Login Page");
        }
    }

    public void enterTextIntoInputLogin(String login) {
        // WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        enterTextIntoInput(inputLogin, login);
    }

    public void enterTextIntoInputPassword(String password) {
        //WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        enterTextIntoInput(inputPassword, password);
    }

    public void clickOnButtonSignIn() {
        //WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[text()='Sign In']"));
        clickOnElement(buttonSignIn);
    }

    public boolean isWarningMessageVisible() {
        try {
            WebElement warningMessage = webDriver.findElement(By.xpath(".//div[text()='Invalid username/password.']"));
            return isElementDisplayed(warningMessage);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isButtonSignInVisible() {
        //try {
        //WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[text()='Sign In']"));
        return isElementDisplayed(buttonSignIn);
        //} catch (Exception e) {
        //    return false;
        // }
    }

    //is button Sign In visible
    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    //is username field visible

    public void checkUsernameFieldNotVisible() {
        checkIsElementNotVisible(inputLogin);
    }

    public void checkUsernameFieltVisible() {
        checkIsElementVisible(inputLogin);
    }

    //is password field visible

    public void checkPasswordFieldNotVisible() {
        checkIsElementNotVisible(inputPassword);
    }

    public void checkPasswordFieldVisible() {
        checkIsElementVisible(inputPassword);
    }

    public void checkSignInButtonNotVisible() {
        checkIsElementNotVisible(buttonSignIn);
    }


    //enter text into input username registration
    public void enterTextIntoInputUsernameRegistration(String username) {
        enterTextIntoInput(inputUsernameRegistration, username);
    }

    public boolean isValidationMessageForUserNameFieldVisible() {
        return isElementDisplayed(validationMessageForUserNameRegistrationField);
    }

    public Object checkTextValidationMessageForUserNameRegistrationField(String message) {
        checkTextInElement(validationMessageForUserNameRegistrationField, message);
        return this;
    }

    public void enterTextIntoInputPasswordRegistration(String password) {
        enterTextIntoInput(inputPasswordRegistration, password);
    }

    public boolean isValidationMessageForPasswordFieldVisible() {
        return isElementDisplayed(validationMessageForPasswordRegistrationField);
    }

    public void checkTextValidationMessageForPasswordRegistrationField(String message) {
        checkTextInElement(validationMessageForPasswordRegistrationField, message);
    }

    public boolean isValidationMessageForEmailFieldVisible() {
        return isElementDisplayed(validationMessageForEmailRegistrationField);
    }

    public void enterTextIntoInputEmailRegistration(String email) {
        enterTextIntoInput(inputEmailRegistration, email);
    }

    public void checkTextValidationMessageForEmailRegistrationField(String message) {
        checkTextInElement(validationMessageForEmailRegistrationField, message);
    }

    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
    }
}
