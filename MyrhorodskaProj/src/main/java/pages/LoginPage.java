package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//опис методів для сторінки логін
public class LoginPage extends ParentPage{
    @FindBy(xpath = "//button[contains(text(),'Sign In')]") //all elements of this page described here //цей створиться PageFactory в CommonActionsWithElements
    private WebElement buttonSignIn; // all methods will be working with this element (buttonSignIn)

    @FindBy(xpath = ".//*[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//*[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//div[text()='Invalid username/password.']")
    private WebElement invalidMassage;

    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputUserNameReg;

    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputEmailReg;


    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputPasswordReg;

    @FindBy(xpath = ".//form[@id='registration-form']//button[@type='submit']")
    private WebElement buttonSignUp;

    @FindBy(xpath = ".//div[text()='Username must be at least 3 characters.']")
    private WebElement userNameValidationReg;

    @FindBy(xpath = ".//div[text()='You must provide a valid email address.']")
    private WebElement emailValidationReg;

    @FindBy(xpath = ".//div[text()='Password must be at least 12 characters.']")
    private WebElement passwordValidationReg;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativUrl() {
        return "/";
    }

    public void openLoginPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Login page was opened"+ baseUrl);
        } catch (Exception e) {
            logger.error("Can not open Login Page");
            Assert.fail("Can not open Login Page");
        }
    }
    public void checkIsRedirectToLoginPage() {
        checkUrl();
    }
    public void enterTextInToInputLogin(String login) {
      //  WebElement inputLogin = webDriver.findElement(By.xpath(".//*[@placeholder='Username']"));
        enterTextInToInput(inputLogin, login);

    }
    public void enterTextInToInputPassword(String password) {
       // WebElement inputPassword = webDriver.findElement(By.xpath(".//*[@placeholder='Password']"));
        enterTextInToInput(inputPassword, password);
    }
    public void clickOnButtonSingIn() {
    //    WebElement buttonSingIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
        clickOnElement(buttonSignIn);
    }
    //is button Sign In visible
    public boolean isButtonSignInVisible() {
      //  WebElement buttonSignIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
        return isElementDisplayed(buttonSignIn);
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCred(){
        openLoginPage();
        enterTextInToInputLogin(TestData.VALID_LOGIN_UI);
        enterTextInToInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSingIn();
        return new HomePage(webDriver);
    }

   // public boolean isButtonSignInVisible() {
   //   try {
       //     WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[text()='Sign In']"));
         //   return isElementDisplayed(buttonSignIn);
        //} catch (Exception e) {
          //  return false;
       // }
    //}
    public boolean isInvalidUserNamePasswordMessageVisible() {
        //WebElement invalidMassage = webDriver.findElement(By.xpath("//div[contains(text(),'Invalid username/password.')]"));
        return isElementDisplayed(invalidMassage);
    }
    public boolean isInputUsernameVisible() {
        return isElementDisplayed(inputLogin);
    }

    public void isInputUsernameNotVisible() {
        checkIsElementNotVisible(inputLogin);
    }

    public boolean isInputPasswordVisible() {
        return isElementDisplayed(inputPassword);
    }

    public void isInputPasswordNotVisible() {
        checkIsElementNotVisible(inputPassword);
    }

    public void isButtonSignInNotVisible() {
        checkIsElementNotVisible(buttonSignIn);
    }
    public LoginPage inputTextIntoRegistrationLogin(String login) {
        enterTextInToInput(inputUserNameReg, login);
        return this;
    }

    public LoginPage inputTextIntoRegistrationPassword(String password) {
        enterTextInToInput(inputPasswordReg, password);
        return this;
    }

    public LoginPage inputTextIntoRegistrationEmail(String email) {
        enterTextInToInput(inputEmailReg, email);
        return this;
    }

    public boolean isValidationMessageForRegistrationInputLoginDisplayed() {
        return isElementDisplayed(userNameValidationReg);
    }
    public LoginPage checkInputInRegistrationLogin(String text) {
        checkTextInElement(userNameValidationReg, text);
        return this;
    }
    public LoginPage checkInputInRegistrationEmail(String text) {
        checkTextInElement(emailValidationReg, text);
        return this;
    }
    public LoginPage isValidationMessageForRegistrationInputEmailDisplayed() {
        checkIsElementVisible(emailValidationReg);
        return this;
    }

    public LoginPage checkInputInRegistrationPassword(String text) {
        checkTextInElement(passwordValidationReg, text);
        return this;
    }
    public boolean isValidationMessageForRegistrationInputPasswordDisplayed() {
        return isElementDisplayed(passwordValidationReg);
    }


    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
    }

    public LoginPage checkIsRedirectToLoginPage() {
        Assert.assertTrue("Invalid page", isButtonSignInVisible());
        return this;
    }
}

