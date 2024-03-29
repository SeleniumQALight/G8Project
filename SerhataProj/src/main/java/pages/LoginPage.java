package pages;

import data.TestData;
import io.qameta.allure.Step;
import libs.DB_Util_seleniumUsers;
import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.elements.HeaderElement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//button[contains(text(),'Sign In')]")//цей елемент створиться PageFactory в CommonActionsWithElements
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//input[@id='username-register']")//id = "username-register"
    private WebElement inputUserNameForSignUp;

    @FindBy(xpath = "//input[@id='email-register']")
    private WebElement inputEmailForSignUp;

    @FindBy(xpath = "//input[@id='password-register']")
    private WebElement inputPasswordForSignUp;

    @FindBy(xpath = "//button [@type='submit']")
    private WebElement buttonSignUp;
    @FindBy(xpath = ".//div[text()='Username must be at least 3 characters.']")
    private WebElement validationMessageForUserNameField;

    @FindBy(xpath = ".//div[text()='You must provide a valid email address.']")
    private WebElement validationMessageForEmailField;

    @FindBy(xpath = ".//div[text()='Password must be at least 12 characters.']")
    private WebElement validationMessageForPasswordField;

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listErrorsMessages;

    private String listErrorsMessagesLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    private String listLoginErrorsMessagesLocator = ".//*[@class='alert alert-danger text-center']";

    @FindBy(xpath = ".//*[@class='alert alert-danger text-center']")
    private List<WebElement> listLoginErrorsMessages;

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
    public void enterTextInToInputLogin(String login) {
        enterTextInToInput(inputLogin, login);
    }
    @Step
    public void enterTextInToInputPassword(String password) {
        enterTextInToInput(inputPassword, password);
    }
    @Step
    public void clickOnButtonSignIn() {
       // WebElement buttonSignIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
        clickOnElement(buttonSignIn);
    }
    @Step
    public boolean isButtonSignInPresent() {
        try {
            boolean state = buttonSignIn.isDisplayed();
            logger.info(state + " is input Password visible");
            return state;
        } catch (Exception e){
            logger.info("Input Password is not displayed");
            return false;
        }
    }
    @Step
    public boolean isErrorMessagePresent() {
        try {
            boolean state = webDriver.findElement(By.xpath("//div[@class='alert alert-danger text-center']")).isDisplayed();
            logger.info(state + " is error message visible");
            return state;
        } catch (Exception e){
            logger.info("Error message is not displayed");
            return false;
        }
    }
    @Step
    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextInToInputLogin(TestData.VALID_LOGIN_UI);
        enterTextInToInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }
    @Step
    public LoginPage checkIsRedirectToLoginPage() {
        checkUrl();
        Assert.assertTrue("Invalid page - not Login Page", isElementDisplayed(buttonSignIn));
        Assert.assertTrue("Invalid page - not Login Page", isElementDisplayed(inputLogin));
        Assert.assertTrue("Invalid page - not Login Page", isElementDisplayed(inputPassword));
        return this;
    }
    @Step
    public void checkIsLoginFieldIsNotVisible() {
        checkIsElementNotVisible(inputLogin);
        checkIsElementNotVisible(inputPassword);
        checkIsElementNotVisible(buttonSignIn);
        logger.info("Login fields are not visible");
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }
    @Step
    public LoginPage enterTextInToInputUserName(String login) {
        enterTextInToInput(inputUserNameForSignUp, login);
        return this;
    }
    @Step
    public LoginPage enterTextInToInputEmail(String email) {
        enterTextInToInput(inputEmailForSignUp, email);
        return this;
    }
    @Step
    public LoginPage enterTextInToInputPasswordForSignUp(String password) {
        enterTextInToInput(inputPasswordForSignUp, password);
        return this;
    }
    @Step
    public LoginPage clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
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
    @Step
    public LoginPage checkIsLoginErrorVisible() {
        checkIsElementVisible(listLoginErrorsMessages.get(0));
        return this;
    }
    @Step
    public LoginPage checkErrorMessages(String messages) {
        // error1; error2 -> [error1, error2]
        String[] expectedErrors = messages.split(";");

        webDriverWait05.until(ExpectedConditions.numberOfElementsToBe(
                By.xpath(listErrorsMessagesLocator), expectedErrors.length));

        Util.waitABit(1);
        Assert.assertEquals("Number of messages ", expectedErrors.length,
                listErrorsMessages.size()); // for checking all errors which are on the page

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
        DB_Util_seleniumUsers dbUtilSeleniumUsers  = new DB_Util_seleniumUsers();
        enterTextInToInputPassword(dbUtilSeleniumUsers.getPasswordForLogin("newqaauto"));
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public LoginPage checkTextInAlertInCenter(String message) {
        Assert.assertEquals("Message in center of page", message, alertMessageInCenter.getText());
        return this;
    }
}
