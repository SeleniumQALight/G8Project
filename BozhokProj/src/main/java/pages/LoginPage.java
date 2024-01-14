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

import java.util.ArrayList;
import java.util.List;

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

    @FindBy(xpath = "//*[@id='registration-form']/div[1]/div")
    private WebElement successUsernameMessage;

    @FindBy(xpath = "//*[@id='registration-form']/div[2]/div")
    private WebElement successEmailMassage;

    @FindBy(xpath = "//*[@id='registration-form']/div[3]/div")
    private WebElement successPasswordMassage;

    @FindBy(xpath = "//a[@data-original-title='Search']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//span[@data-original-title='Chat']")
    private WebElement buttonChat;

    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//*[@class ='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(id = "username-register")
    private WebElement inputUserNameRegistration;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistration;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistration;

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfErrorsMessages;

    private String listOfErrorsMessagesLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public void openLoginPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Login page was opened" + baseUrl);
        } catch (Exception e) {
            logger.error("Can not open Login Page");
            Assert.fail("Can not open Login Page");
        }
    }

//    public  void checkIsRedirectToLoginPage() {
//        Assert.assertEquals("Invalid page", baseUrl + getRelativeUrl(), webDriver.getCurrentUrl());
//    }

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

    private void clickOnButtonSignUpForOurApp() {
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

    public LoginPage checkIsRedirectOnLoginPage() {
        // TODO check url
        // TODO Check element
        return this;
    }


    public LoginPage isButtonSearchVisible() {
        try {
            Assert.assertFalse("Button Search is visible", buttonSearch.isDisplayed());
        } catch (Exception e) {
            logger.info("Button Search is not visible");
        }
        return this;
    }

    public LoginPage isButtonChatVisible() {
        try {
            Assert.assertFalse("Button Chat is visible", buttonChat.isDisplayed());
        } catch (Exception e) {
            logger.info("Button Chat is not visible");
        }
        return this;
    }

    public LoginPage isButtonAvatarVisible() {
        try {
            Assert.assertFalse("Button Avatar is visible", buttonMyProfile.isDisplayed());
        } catch (Exception e) {
            logger.info("Button Avatar is not visible");
        }
        return this;
    }

    public LoginPage isButtonCreatePostVisible() {
        try {
            Assert.assertFalse("Button Create Post is visible", buttonCreatePost.isDisplayed());
        } catch (Exception e) {
            logger.info("Button Create Post is not visible");
        }
        return this;
    }

    public boolean isInputPasswordVisible() {
        return isElementDisplayed(inputPassword);
    }

    public boolean isInputLoginVisible() {
        return isElementDisplayed(inputLogin);
    }

    public LoginPage enterTextRegistrationUserNameField(String userName) {
        enterTextIntoInput(inputUserNameRegistration, userName);
        return this;
    }

    public LoginPage enterTextRegistrationEmailField(String email) {
        enterTextIntoInput(inputEmailRegistration, email);
        return this;
    }

    public LoginPage enterTextRegistrationPasswordField(String password) {
        enterTextIntoInput(inputPasswordRegistration, password);
        return this;
    }

    public LoginPage checkErrorsMessages(String massages) {
        //error1;error2 -> [error1, error2]
        String[] expectedErrors = massages.split(";");
        webDriverWaite10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfErrorsMessagesLocator), expectedErrors.length));

        Util.waitABit(1);
        Assert.assertEquals("Number of messages", expectedErrors.length, listOfErrorsMessages.size());


        ArrayList<String> actualErrors = new ArrayList<>();
        for (WebElement element : listOfErrorsMessages) {
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
