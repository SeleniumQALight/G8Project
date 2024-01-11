package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(xpath = "//input[@id = 'email-register']")
    private WebElement inputEmailRegister;

    @FindBy(xpath = "//input[@id = 'password-register']")
    private WebElement inputPasswordRegister;

    @FindBy(xpath = "//button[contains(text(), 'Sign up')]")
    private WebElement buttonSignUp;

    @FindBy(xpath = "//div[text() = 'Username must be at least 3 characters.']")
    private WebElement validationMessageUsernameRegister;

    @FindBy(xpath = "//div[text() = 'You must provide a valid email address.']")
    private WebElement validationMessageEmailRegister;

    @FindBy(xpath = "//div[text() = 'Password must be at least 12 characters.']")
    private WebElement validationMessagePasswordRegister;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelatedUrl() {
        return "/";
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
        enterTextIntoInput(inputLogin, login);
    }

    public void enterTextIntoInputPassword(String password) {
        enterTextIntoInput(inputPassword, password);
    }

    public void enterTextIntoInputUsernameRegister(String username) {
        enterTextIntoInput(inputUsernameRegister, username);
    }

    public void enterTextIntoInputEmailRegister(String email) {
        enterTextIntoInput(inputEmailRegister, email);
    }

    public void enterTextIntoInputPasswordRegister(String password) {
        enterTextIntoInput(inputPasswordRegister, password);
    }

    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
    }

    public boolean isInputLoginVisible() {
        return isElementDisplayed(inputLogin);
    }

    public boolean isInputPasswordVisible() {
        return isElementDisplayed(inputPassword);
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public boolean isButtonSignInVisible() {
        return isElementDisplayed(buttonSignIn);
    }

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

    public HomePage openLoginPageAndFillLoginFormWithValidCreate() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public LoginPage checkIsInputUsernameNotVisible() {
        checkIsElementNotVisible(inputLogin, "Input Username");
        return this;
    }

    public LoginPage checkIsInputPasswordNotVisible() {
        checkIsElementNotVisible(inputPassword, "Input Password");
        return this;
    }

    public LoginPage checkIsButtonSignInNotVisible() {
        checkIsElementNotVisible(buttonSignIn, "Button Sign In");
        return this;
    }

    public LoginPage checkIsRedirectToLoginPage() {
        checkCurrentUrl();
        Assert.assertTrue("Login page is not opened", isButtonSignInVisible());
        return this;
    }

    public boolean checkIsValidationMessageUsernameRegisterVisible() {
        return checkIsValidationMessageVisible(validationMessageUsernameRegister);
    }

    public boolean checkIsValidationMessageEmailRegisterVisible() {
        return checkIsValidationMessageVisible(validationMessageEmailRegister);
    }

    public boolean checkIsValidationMessagePasswordRegisterVisible() {
        return checkIsValidationMessageVisible(validationMessagePasswordRegister);
    }
}
