package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParrentPage {
    @FindBy(xpath = ".//button[contains(text(),'Sign In')]")
//цей елементствориться PageFactory в CommonActionsWithElements
    private WebElement buttonSignIn;

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//div[contains(text(),'Invalid username/password')]")
    private WebElement textInvalidLoginOrPassword;

    @FindBy(xpath = ".//input[@id='username-register']")
    WebElement registerLogin;

    @FindBy(xpath = ".//input[@id='password-register']")
    WebElement registerPassword;

    @FindBy(xpath = ".//input[@name='email']")
    WebElement registerEmail;

    @FindBy(xpath = ".//button[@type='submit']")
    WebElement buttonSignUp;

    @FindBy(xpath = ".//div[text()='Username must be at least 3 characters.']")
    WebElement errorMessageInRegisterUsernameField;

    @FindBy(xpath = ".//div[text()='You must provide a valid email address.']")
    WebElement errorMessageInRegisterEmailField;

    @FindBy(xpath = ".//div[text()='Password must be at least 12 characters.']")
    WebElement errorMessageInRegisterPasswordField;

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

    public void enterTextInToInputLogin(String login) {
        // WebElement inputLogin = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        enterTextInToInput(inputLogin, login);
    }

    public void enterTextInToInputPassword(String password) {
        //  WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        enterTextInToInput(inputPassword, password);
    }

    public void clickOnButtonSignIn() {
        // WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[contains(text(),'Sign In')]"));
        clickOnElement(buttonSignIn);
    }

    public void enterTextIntoRegisterLogin(String login) {

        enterTextInToInput(registerLogin, login);
    }


    public boolean isButtonSignInVisible() {
        try {
            //   WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[contains(text(),'Sign In')]"));
            return isElementDisplayed(buttonSignIn);
        } catch (Exception e) {
            logger.info("Element button SignIn is displayed -> false");
            return false;
        }
    }


    public boolean isTextInvalidLoginOrPasswordDisplayed() {

        return isElementDisplayed(textInvalidLoginOrPassword);

    }

    public boolean isInputLoginFieldVisible() {

        return isElementDisplayed(inputLogin);

    }

    //new methods:

    public LoginPage checkIsInputLoginFieldNotVisible() {
        checkIsElementNotVisible(inputLogin);
        return this;
    }

    public LoginPage checkIsInputPasswordFieldNotVisible() {
        checkIsElementNotVisible(inputPassword);
        return this;
    }

    public LoginPage checkIsButtonSignInIsNotVisible() {
        checkIsElementNotVisible(buttonSignIn);
        return this;
    }


    public LoginPage checkAllElementsInHeaderAreNotVisible() {
        checkIsInputLoginFieldNotVisible();
        checkIsButtonSignInIsNotVisible();
        checkIsInputPasswordFieldNotVisible();
        return this;

    }


    public boolean isInputPasswordFieldVisible() {

        return isElementDisplayed(inputPassword);

    }


    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextInToInputLogin(TestData.VALID_LOGIN_UI);
        enterTextInToInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);

    }

    public void enterTextIntoRegisterPassword(String password) {

        enterTextInToInput(registerPassword, password);
    }

    public void enterTextIntoRegisterEmail(String email) {
        enterTextInToInput(registerEmail, email);
    }

    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
    }

    public boolean isTextInErrorMessageInRegisterUsernameIsVisible() {
        return isElementDisplayed(errorMessageInRegisterUsernameField);
    }

    public boolean isTextInErrorMessageInRegisterEmailIsVisible() {
        return isElementDisplayed(errorMessageInRegisterEmailField);
    }

    public boolean isTextInErrorMessageInRegisterPasswordIsVisible() {
        return isElementDisplayed(errorMessageInRegisterPasswordField);
    }


}

