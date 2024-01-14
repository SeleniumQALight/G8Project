package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//button[contains(text(),'Sign In')]")
    //цей елемент буде створенний PageFactory в CommonActionsWithElements
    private WebElement buttonSingIn;
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;
    @FindBy(xpath = "//div[text()='Invalid username/password.']")
    private WebElement validationMessage;
    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputUserName;

    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement buttonSingUp;
    @FindBy(xpath = ".//div[text()='Username must be at least 3 characters.']")
    private WebElement validationMessageForUserNameRegister;

    @FindBy(xpath = ".//div[text()='You must provide a valid email address.']")
    private WebElement validationMessageForEmailRegister;

    @FindBy(xpath = ".//div[text()='Password must be at least 12 characters.']")
    private WebElement validationMessageForPasswordRegister;


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

    public void enterTextIntoInput(String login) {
        enterTextIntoInput(inputLogin, login); // WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
    }

    public void enterTextIntoInputPassword(String password) {
        enterTextIntoInput(inputPassword, password); // WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSingIn); // WebElement buttonSignIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
    }

    // is button Sign In visible
    public boolean isButtonSignInVisible() {
        return isElementDisplayed(buttonSingIn);
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInput(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public boolean isAlertTextVisible() {
        return isElementDisplayed(validationMessage);
    }

    public boolean isInputUserNameNotVisible() {
        return isElementDisplayed(inputLogin);
    }

    public boolean isInputPasswordNotVisible() {
        return isElementDisplayed(inputPassword);
    }

    public boolean isButtonSignInNotVisible() {
        return isElementDisplayed(buttonSingIn);
    }

    public void checkIsRedirectToLoginPage() { // перевірка чи ми на сторінці логін
        Assert.assertEquals("Invalid page", "https://aqa-complexapp.onrender.com/", webDriver.getCurrentUrl());
    }

    public boolean isInputUserNameVisible() {
        return isElementDisplayed(inputLogin);
    }

    public boolean isInputPasswordVisible() {
        return isElementDisplayed(inputPassword);
    }

    public void enterTextIntoInputUserNameRegister(String username) {
        enterTextIntoInput(inputUserName, username);
    }

    public void enterTextIntoInputEmailRegister(String email) {
        enterTextIntoInput(inputLogin, email);
    }

    public void enterTextIntoInputPasswordRegister(String password) {
        enterTextIntoInput(inputPassword, password);
    }

    public void clickOnButtonSignUp() {
        clickOnElement(buttonSingUp);
    }

    public boolean isValidationMessageForUserNameRegisterInputDisplayed() {
        return isElementDisplayed(validationMessageForUserNameRegister);
    }
    public boolean isValidationMessageForEmailRegisterInputDisplayed() {
        return isElementDisplayed(validationMessageForEmailRegister);
    }

    public boolean isValidationMessageForPasswordRegisterInputDisplayed() {
        return isElementDisplayed(validationMessageForPasswordRegister);
    }

    public void checkTextInValidationMessageForUserNameRegisterInput(String username){
        Assert.assertEquals("Username Text is invalid ", username, validationMessageForUserNameRegister.getText());
    }

    public void checkTextInValidationMessageForEmailRegisterInput(String email) {
        Assert.assertEquals("Email Text is invalid ", email, validationMessageForEmailRegister.getText());
    }

    public void checkTextInValidationMessageForPasswordRegisterInput(String password) {
        Assert.assertEquals("Password Text is invalid ", password, validationMessageForPasswordRegister.getText());
    }
}
