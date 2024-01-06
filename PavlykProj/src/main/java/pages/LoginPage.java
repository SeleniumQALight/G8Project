package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

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

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage openLoginPage() {
        try {
            webDriver.get("https://aqa-complexapp.onrender.com");
            logger.info("Login page was opened");
            return this;
        } catch (Exception e) {
            logger.error("Can not open login page");
            Assert.fail("Can not open login page");
            return null;
        }
    }

    public void enterTextIntoInputLogin(String login) {
//        WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        enterTextIntoInput(inputLogin, login);
    }

    public LoginPage checkIsInputLoginVisible() {
        checkIsElementVisible(inputLogin);
        return this;
    }

    public void enterTextIntoInputPassword(String password) {
//        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        enterTextIntoInput(inputPassword, password);
    }

    public void checkIsInputPasswordVisible() {
        checkIsElementVisible(inputPassword);
    }

    public void enterTextIntoInputUsernameRegistration(String username) {
        enterTextIntoInput(inputUsernameAtRegistration, username);
    }

    public void enterTextIntoInputEmailRegistration(String email) {
        enterTextIntoInput(inputEmailAtRegistration, email);
    }

    public LoginPage enterTextIntoInputPasswordRegistration(String password) {
        enterTextIntoInput(inputPasswordAtRegistration, password);
        return this;
    }

    public void clickOnButtonSignIn() {
//        WebElement signInButton = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
        clickOnElement(buttonSignIn);
    }

    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
    }

    public boolean checkIsWarningMessageVisible() {
//        WebElement warningMessage = webDriver.findElement(By.xpath(".//div[text()='Invalid username/password.']"));
        return isElementDisplayed(warningMessage);

    }

    // is button Sign in visible
    public boolean checkIsButtonSignInVisible() {
//        WebElement buttonSignIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
        return isElementDisplayed(buttonSignIn);
    }

    public LoginPage checkIsValidationMessageForUserNameFieldVisible() {
        checkIsElementVisible(validationMessageForUserNameField);
        return this;
    }

    public LoginPage checkIsValidationMessageForEmailFieldVisible() {
        checkIsElementVisible(validationMessageForEmailField);
        return this;
    }

    public LoginPage checkIsValidationMessageForPasswordFieldVisible() {
        checkIsElementVisible(validationMessageForPasswordField);
        return this;
    }

    // input Login is not visible
    public void checkIsInputLoginNotVisible() {
        checkIsElementNotVisible(inputLogin);
    }

    // input Password is not visible
    public void checkIsInputPasswordNotVisible() {
        checkIsElementNotVisible(inputPassword);
    }

    // button Sign in is not visible
    public void checkIsButtonSignInNotVisible() {
        checkIsElementNotVisible(buttonSignIn);
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public LoginPage enterInvalidTextInRegistrationFieldsAndClickButtonSignUp(String invalidText) {
        openLoginPage();
        enterTextIntoInputUsernameRegistration(invalidText);
        enterTextIntoInputEmailRegistration(invalidText);
        enterTextIntoInputPasswordRegistration(invalidText);
        clickOnButtonSignUp();
        return this;
    }

    public void checkIsLoginFieldIsNotVisible() {
        checkIsInputLoginNotVisible();
        checkIsInputPasswordNotVisible();
        checkIsButtonSignInNotVisible();
        logger.info("Login field is not visible");
    }

    public LoginPage checkIsRedirectToHomePage() {
        //TODO check url
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
}
