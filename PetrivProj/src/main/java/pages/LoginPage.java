package pages;

import libs.TestData;
import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.elements.HeaderElement;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//button[contains(text(),'Sign In')]") // цей елемент створиться PageFactory в CommonActionsWithElements
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputUserNameRegistration;

    @FindBy(id = "email-register") // інший варіант пошуку елемента (одразу по ід)
    private WebElement inputEmailRegistration;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistration;

    @FindBy(xpath = ".//form[@id='registration-form']//button[@type='submit']")
    private WebElement buttonSignUp;

    @FindBy(xpath = ".//input[@id='username-register']/following-sibling::div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private WebElement validationMessageForUserNameRegister;

    @FindBy(xpath = ".//input[@id='email-register']/following-sibling::div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private WebElement validationMessageForEmailRegister;

    @FindBy(xpath = ".//input[@id='password-register']/following-sibling::div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private WebElement validationMessageForPasswordRegister;

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listErrorMessages;
    private String listErrorMessagesLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
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

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    // is button Sign In visible
    public boolean isButtonSignInVisible() {
        return isElementDisplayed(buttonSignIn);
    }

    public boolean isInputUserNameVisible() {
        return isElementDisplayed(inputLogin);
    }

    public boolean isInputPasswordVisible() {
        return isElementDisplayed(inputPassword);
    }

    public void isInputUserNameNotVisible() {
        checkIsElementNotVisible(inputLogin);
    }

    public void isInputPasswordNotVisible() {
        checkIsElementNotVisible(inputPassword);
    }

    public void isButtonSignInNotVisible() {
        checkIsElementNotVisible(buttonSignIn);
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public LoginPage checkIsRedirectToLoginPage() {
        checkUrl();
        Assert.assertTrue("Invalid page - not Login Page", isButtonSignInVisible());
        return this;
    }

    public LoginPage enterTextIntoRegistrationUserNameField(String username) {
        enterTextIntoInput(inputUserNameRegistration, username);
        return this;
    }

    public LoginPage enterTextIntoRegistrationEmailField(String email) {
        enterTextIntoInput(inputEmailRegistration, email);
        return this;
    }

    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        enterTextIntoInput(inputPasswordRegistration, password);
        return this;
    }

    public void clickOnButtonSignUp()  {
        clickOnElement(buttonSignUp);
    }

    public boolean isValidationMessageForUserNameRegisterInputDisplayed() {
        return isElementDisplayed(validationMessageForUserNameRegister);
    }

    public LoginPage checkTextInValidationMessageForUserNameRegisterInput(String text) {
        checkTextInElement(validationMessageForUserNameRegister, text);
        return this;
    }

    public boolean isValidationMessageForEmailRegisterInputDisplayed() {
        return isElementDisplayed(validationMessageForEmailRegister);
    }

    public LoginPage checkTextInValidationMessageForEmailRegisterInput(String text) {
        checkTextInElement(validationMessageForEmailRegister, text);
        return this;
    }

    public boolean isValidationMessageForPasswordRegisterInputDisplayed() {
        return isElementDisplayed(validationMessageForPasswordRegister);
    }

    public LoginPage checkTextInValidationMessageForPasswordRegisterInput(String text) {
        checkTextInElement(validationMessageForPasswordRegister, text);
        return this;
    }

    public LoginPage checkErrorMessages(String messages) {
        // error1;error2 -> [error1, error2]
        String[] expectedErrors = messages.split(";");
        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorMessagesLocator), expectedErrors.length));

        Util.waitABit(1);
        Assert.assertEquals("Number of messages", expectedErrors.length, listErrorMessages.size());

        ArrayList<String> actualErrors = new ArrayList<>();
        for (WebElement element: listErrorMessages) {
            actualErrors.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrors.length; i++) {
            softAssertions.assertThat(expectedErrors[i])
                    .as("Error " + i)
                    .isIn(actualErrors);
        }

        softAssertions.assertAll(); // check all assertions

        return this;
    }
}
