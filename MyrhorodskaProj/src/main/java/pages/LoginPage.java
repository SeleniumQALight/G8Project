package pages;


import io.qameta.allure.Step;
import libs.DB_Util_seleniumUser;
import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listErrorsMessages;

    @FindBy(xpath = ".//div[contains(text(),'Invalid username/password.')]")
    private WebElement invalidUsernameOrPasswordMessage;

    private String listErrorsMessagesLocator    = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy(id = "username-register") // xpath = ".//*[@id='username-register']"
    private WebElement inputUserNameRegistration;


    @FindBy(id = "email-register")
    private WebElement inputEmailRegistration;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistration;

    @FindBy(xpath = ".//div[@class='alert alert-danger text-center']")
    private WebElement alertMessageInCenter;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativUrl() {
        return "/";
    }
@Step
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
    @Step
    public void enterTextInToInputLogin(String login) {
      //  WebElement inputLogin = webDriver.findElement(By.xpath(".//*[@placeholder='Username']"));
        enterTextInToInput(inputLogin, login);

    }
   @Step
    public void enterTextInToInputPassword(String password) {
       // WebElement inputPassword = webDriver.findElement(By.xpath(".//*[@placeholder='Password']"));
        enterTextInToInput(inputPassword, password);
    }
    public void clickOnButtonSingIn() {
    //    WebElement buttonSingIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
        clickOnElement(buttonSignIn);
    }
    //is button Sign In visible
    @Step
    public boolean isButtonSignInVisible() {
      //  WebElement buttonSignIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
        return isElementDisplayed(buttonSignIn);
    }
    @Step
    public LoginPage checkIsInvalidUsernameOrPasswordMessageVisible() {
        checkIsElementVisible(invalidUsernameOrPasswordMessage);
        return this;
    }
    @Step
    public HomePage openLoginPageAndFillLoginFormWithValidCred(){
        openLoginPage();
        enterTextInToInputLogin(data.TestData.VALID_LOGIN_UI);
        enterTextInToInputPassword(data.TestData.VALID_PASSWORD_UI);
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
    @Step
    public boolean isInvalidUserNamePasswordMessageVisible() {
        //WebElement invalidMassage = webDriver.findElement(By.xpath("//div[contains(text(),'Invalid username/password.')]"));
        return isElementDisplayed(invalidMassage);
    }
    @Step
    public boolean isInputUsernameVisible() {
        return isElementDisplayed(inputLogin);
    }
    @Step
    public void isInputUsernameNotVisible() {
        checkIsElementNotVisible(inputLogin);
    }
    @Step
    public boolean isInputPasswordVisible() {
        return isElementDisplayed(inputPassword);
    }
    @Step
    public void isInputPasswordNotVisible() {
        checkIsElementNotVisible(inputPassword);
    }

    public void isButtonSignInNotVisible() {
        checkIsElementNotVisible(buttonSignIn);
    }

    @Step
    public LoginPage  inputTextIntoRegistrationLogin (String userName) {
        enterTextInToInput(inputUserNameRegistration, userName);
        return this;
    }
    @Step
    public LoginPage inputTextIntoRegistrationPassword(String password) {
        enterTextInToInput(inputPasswordReg, password);
        return this;
    }
    @Step
    public LoginPage inputTextIntoRegistrationEmail(String email) {
        enterTextInToInput(inputEmailReg, email);
        return this;
    }
    @Step
    public boolean isValidationMessageForRegistrationInputLoginDisplayed() {
        return isElementDisplayed(userNameValidationReg);
    }
    @Step
    public LoginPage checkInputInRegistrationLogin(String text) {
        checkTextInElement(userNameValidationReg, text);
        return this;
    }
    @Step
    public LoginPage checkInputInRegistrationEmail(String text) {
        checkTextInElement(emailValidationReg, text);
        return this;
    }
    @Step
    public boolean isValidationMessageForRegistrationInputEmailDisplayed() {
        return isElementDisplayed(emailValidationReg);
    }

    @Step
    public LoginPage checkInputInRegistrationPassword(String text) {
        checkTextInElement(passwordValidationReg, text);
        return this;
    }
    @Step
    public boolean isValidationMessageForRegistrationInputPasswordDisplayed() {
        return isElementDisplayed(passwordValidationReg);
    }

    @Step
    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
    }

  //  public LoginPage checkIsRedirectToLoginPage() {
  //      Assert.assertTrue("Invalid page", isButtonSignInVisible());
  //      return this;
   // }

    public LoginPage checkErrorMessages(String massages) {
        //error1;error2 -> [error1, error2]
        String[] expectedErrors = massages.split(";");
        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(
                By.xpath(listErrorsMessagesLocator), expectedErrors.length));

        Util.waitABit(1);
        Assert.assertEquals("Number of messages", expectedErrors.length, listErrorsMessages.size());

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

        softAssertions.assertAll(); //check all assertion

        return this;
    }


    public LoginPage checkErrorsMessages (String messages) {
        // error1;error2 -> [error1, error2]
        String[] expectedErrors = messages.split(";");
        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(
                By.xpath(listErrorsMessagesLocator), expectedErrors.length));


        Util.waitABit(1);
        Assert.assertEquals("Number of messages ", expectedErrors.length,
                listErrorsMessages.size());

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

        softAssertions.assertAll(); // check all assertion

        return this;
    }
    @Step
    public HomePage openLoginPageAndFillLoginFormWithPasswordFromDB() throws SQLException, ClassNotFoundException {
        openLoginPage();
        enterTextInToInputLogin("newqaauto");
        DB_Util_seleniumUser dbUtilSeleniumUsers  = new DB_Util_seleniumUser();
        enterTextInToInputPassword(dbUtilSeleniumUsers.getPassForLogin("newqaauto"));
        clickOnButtonSingIn();
        return new HomePage(webDriver);
    }

    public LoginPage checkTextInAlertInCenter(String message) {
        Assert.assertEquals("Message in center if page",message,alertMessageInCenter.getText());
        return this;
    }
}


