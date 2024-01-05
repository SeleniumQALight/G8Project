package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    @FindBy(xpath = " .//button[text()='Sign In']") // цей елемент створюється в CommonActionWithElements
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputLoginRegister;

    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputEmailRegister;

    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputPasswordRegister;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement buttonSignUpForOurApp;

    @FindBy(xpath ="//*[@id='registration-form']/div[1]/div")
    private WebElement successUsernameMessage;

    @FindBy(xpath ="//*[@id='registration-form']/div[2]/div")
    private WebElement successEmailMassage;

    @FindBy(xpath ="//*[@id='registration-form']/div[3]/div")
    private WebElement successPasswordMassage;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://aqa-complexapp.onrender.com/");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not open Login Page");
            Assert.fail("Can not open Login Page");
        }
    }

    public void enterTextIntoInputLogin(String login) {
        enterTextIntoInput(inputLogin, login);
    }

    public void enterTextIntoInputPassword(String password) {
        enterTextIntoInput(inputPassword, password);
    }

    // input Username Login Register
   public void enterTextIntoInputUsernameLoginRegister(String usernameLoginRegister) {
        enterTextIntoInput(inputLoginRegister, usernameLoginRegister);
    }

    // input Email Login Register
    public void enterTextIntoInputEmailRegister(String emailLoginRegister) {
        enterTextIntoInput(inputEmailRegister, emailLoginRegister);
    }
    // input Password Login Register
    public void enterTextIntoInputPasswordRegister(String passwordLoginRegister) {
        enterTextIntoInput(inputPasswordRegister, passwordLoginRegister);
    }

    public void clickOnButtonSignIn() {
//        WebElement buttonSignIn = webDriver.findElement(
//                By.xpath("//button[contains(text(),'Sign In')]"));
        clickOnElement(buttonSignIn);
    }

    // click on button Sign Up For Our App
    private void clickOnButtonSignUpForOurApp() {
//        WebElement buttonSignUpForOurApp = webDriver.findElement(
//                By.xpath("//button[contains(text(),'Sign Up For Our App')]"));
        clickOnElement(buttonSignUpForOurApp);
    }

    // is button Sign In visible
    public boolean isButtonSignInVisible() {
        return isElementDisplayed(buttonSignIn);
    }

    public boolean isMessageInvalidUsernamePasswordInVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center']"))
                    .isDisplayed();
            logger.info(state + " is message visible");
            return state;
        } catch (Exception e) {
            logger.info("Message is not displayed");
            return false;
        }
    }

    public boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info(state + " is button visible");
            return state;
        } catch (Exception e) {
            logger.info("Button Sign Out is not displayed");
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

    // check enty of short data in the registration form
    public void fillOutTheLoginFormRegistration() {
        openLoginPage();
        enterTextIntoInputUsernameLoginRegister(TestData.SHORT_LOGIN_UI);
        enterTextIntoInputEmailRegister(TestData.SHORT_EMAIL);
        enterTextIntoInputPasswordRegister(TestData.SHORT_PASSWORD_UI);
        clickOnButtonSignUpForOurApp();
        checkIsMessageAboutShortUsernameInRegistrationFormVisible("Username must be at least 3 characters.");
        checkIsMessageAboutShortEmailInRegistrationFormVisible("You must provide a valid email address.");
        checkIsMessageAboutShortPasswordInRegistrationFormVisible("Password must be at least 12 characters.");
    }

    // check massage about short data in username in the registration form
    public LoginPage checkIsMessageAboutShortUsernameInRegistrationFormVisible(String text) {
        checkTextInElement(successUsernameMessage, text);
        logger.info("Message about short username in the registration form is visible");
        return this;
    }

    // check massage about short data in Email in the registration form
    public LoginPage checkIsMessageAboutShortEmailInRegistrationFormVisible(String text) {
        checkTextInElement(successEmailMassage, text);
        logger.info("Message about short Email in the registration form is visible");
        return this;
    }

    // check massage about short data in password in the registration form
    public LoginPage checkIsMessageAboutShortPasswordInRegistrationFormVisible(String text) {
        checkTextInElement(successPasswordMassage, text);
        logger.info("Message about short password in the registration form is visible");
        return this;
    }
}
