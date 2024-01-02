package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends ParentPage {

    @FindBy(xpath = "//input[@id='username-register']")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@id='email-register']")
    private WebElement inputEmail;

    @FindBy(xpath = "//input[@id='password-register']")
    private WebElement inputPassword;

    @FindBy(xpath = "//div[contains(text(), 'Username must be at least 3 characters.')]")
    private WebElement validationMessageUsername;

    @FindBy(xpath = "//div[contains(text(), 'You must provide a valid email address.')]")
    private WebElement validationMessageEmail;

    @FindBy(xpath = "//div[contains(text(), 'Password must be at least 12 characters.')]")
    private WebElement validationMessagePassword;

    @FindBy(xpath = "//button[contains(text(),'Sign up for OurApp')]")
    private WebElement buttonSignUp;

    public RegistrationPage(WebDriver webDriver) {
        super(webDriver);
    }


    public RegistrationPage fillRegistrationForm(String userName, String Email, String Password) {
        enterTextInToInput(inputUserName, userName);
        enterTextInToInput(inputEmail, Email);
        enterTextInToInput(inputPassword, Password);
        return this;

    }

    public RegistrationPage clickOnButtonSingUp() {
        clickOnElement(buttonSignUp);
        return this;
    }

    public RegistrationPage checkErrorsMessages(String text) {
        checkTextInElement(validationMessageUsername, "Username must be at least 3 characters.");
        checkTextInElement(validationMessageEmail, "You must provide a valid email address.");
        checkTextInElement(validationMessagePassword, "Password must be at least 12 characters.");
        return this;
    }
}

