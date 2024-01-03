package pages;

import libs.Urls;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RegistrationForm extends ParentPage {

    @FindBy(xpath = "//input[@id='username-register']")
    private WebElement userNameField;
    @FindBy(xpath = "//input[@id='password-register']")
    private WebElement passwordField;
    @FindBy(xpath = "//input[@id='email-register']")
    private WebElement emailField;
    @FindBy(xpath = "//button[text()='Sign up for OurApp']")
    private WebElement signInForOurAppButton;
    @FindBy(xpath = "//div[contains(@class,'liveValidateMessage--visible')]")
    private List<WebElement> errorBlockList;

    public void clickOnSignInForOurAppButton() {
        clickOnElement(signInForOurAppButton);
    }

    public RegistrationForm checkErrorMessage(int index, String expectedText) {
        checkTextInElement(errorBlockList.get(index), expectedText);
        return this;
    }

    public RegistrationForm fillForm(String name, String email, String pass) {
        enterTextIntoInput(userNameField, name);
        enterTextIntoInput(emailField, email);
        enterTextIntoInput(passwordField, pass);
        return this;
    }


    public RegistrationForm(WebDriver webDriver) {
        super(webDriver);
    }
}
