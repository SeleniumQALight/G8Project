package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends ParentPage{
    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputUsername;

    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputEmail;

    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement buttonSignUp;

    @FindBy(xpath = ".//div[text() = 'Username must be at least 3 characters.']")
    private WebElement divValidationMessageForUsername;

    @FindBy(xpath = ".//div[text() = 'You must provide a valid email address.']")
    private WebElement divValidationMessageForEmail;

    @FindBy(xpath = ".//div[text() = 'Password must be at least 12 characters.']")
    private WebElement divValidationMessageForPassword;

    public RegistrationPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void enterTextIntoInputUsername(String username) {
        enterTextIntoInput(inputUsername, username);
    }

    public void enterTextIntoInputEmail(String email) {
        enterTextIntoInput(inputEmail, email);
    }

    public void enterTextIntoInputPassword(String password) {
        enterTextIntoInput(inputPassword, password);
    }

    public void clickOnButtonSingUp() {
        clickOnElement(buttonSignUp);
    }

    public RegistrationPage fillRegistrationFormWithInvalidCreds() {
        enterTextIntoInputUsername("sv");
        enterTextIntoInputEmail("sv");
        enterTextIntoInputPassword("sv");
        clickOnButtonSingUp();
        return this;
    }

    public RegistrationPage checkIsValidationMessageDisplayedForUsernameField() {
        checkIsElementVisible(divValidationMessageForUsername);
        return this;
    }

    public RegistrationPage checkIsValidationMessageDisplayedForEmailField() {
        checkIsElementVisible(divValidationMessageForEmail);
        return this;
    }

    public RegistrationPage checkIsValidationMessageDisplayedForPasswordField() {
        checkIsElementVisible(divValidationMessageForPassword);
        return this;
    }
}