package pages;


import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import libs.TestData;
import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends ParentPage{
    @FindBy(xpath = ".//button[contains(text(),'Sign In')]") // цей елемент створить PageFactory в CommonActionsWhithElements
    private WebElement buttonSignIn;
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;
    @FindBy(xpath = "//div[contains(text(),'Invalid username/password.')]")
    private WebElement errorMessage;
    @FindBy(xpath = ".//button[contains(text(),'Sign up for OurApp')]")
    private WebElement buttonSignUp;
    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputUsernameRegister;
    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputEmailRegister;
    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputPasswordRegister;
    @FindBy(xpath = ".//*[text()='Username must be at least 3 characters.']")
    private WebElement errorMessageForUsernameInput;
    @FindBy(xpath = ".//*[text()='You must provide a valid email address.']")
    private WebElement errorMessageForEmailInput;
    @FindBy(xpath = ".//*[text()='Password must be at least 12 characters.']")
    private WebElement errorMessageForPasswordInput;
    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listErrorsMessages;
    private String listErrorsMessagesLocator    = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "";
    }

    public void openLoginPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Login page was opened " + baseUrl);
        } catch (Exception e) {
            logger.error("Can not open login Page");
            Assert.fail("Can not open login Page");
        }
    }

    public void checkIsRedirectToLoginPage() {
        checkUrl();
    }

    public void enterTextIntoInputLogin(String login) {
//        WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        enterTextIntoInput(inputLogin, login);
    }

    public void enterTextIntoInputPassword(String password) {
//        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        enterTextIntoInput(inputPassword, password);
    }

    public void clickOnButtonSignIn() {
//        WebElement buttonSignIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
        clickOnElement(buttonSignIn);
    }

    public boolean isButtonSignInVisble() {
//           WebElement buttonSignIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
           return isElementDisplayed(buttonSignIn);
    }

    public boolean isErrorMessageVisible() {
//            WebElement errorMessage = webDriver.findElement(By.xpath("//div[contains(text(),'Invalid username/password.')]"));
            return isElementDisplayed(errorMessage);
        }

    public HomePage openLoginPageAndFillLoginFormWhithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public boolean isInputLoginVisible() {
        return isElementDisplayed(inputLogin);
    }
    public boolean isInputPasswordVisible() {
        return isElementDisplayed(inputPassword);
    }

    public void enterTextIntoInputUsernameRegister(String UserName) {
        enterTextIntoInput(inputUsernameRegister, UserName);
    }

    public void enterTextIntoInputEmailRegister(String Email) {
        enterTextIntoInput(inputEmailRegister, Email);
    }

    public void enterTextIntoInputPasswordRegister(String Password) {
        enterTextIntoInput(inputPasswordRegister, Password);
    }

    public void clickOnButtonSignUpRegister() {
        clickOnElement(buttonSignUp);
    }

    public boolean isErrorMessageVisibleForUsernameInput() {
        return isElementDisplayed(errorMessageForUsernameInput);
    }

    public boolean isErrorMessageVisibleForEmailInput() {
        return isElementDisplayed(errorMessageForEmailInput);
    }

    public boolean isErrorMessageVisibleForPasswordInput() {
        return isElementDisplayed(errorMessageForPasswordInput);
    }

    public boolean isButtonSignUpVisible() {
        return isElementDisplayed(buttonSignUp);
    }

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
}

