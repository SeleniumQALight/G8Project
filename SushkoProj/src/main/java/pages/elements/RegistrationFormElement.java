package pages.elements;

import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.CommonActionsWithElements;

import java.util.ArrayList;
import java.util.List;

public class RegistrationFormElement extends CommonActionsWithElements {
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

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listErrorMessages;

    private String listErrorMessagesLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    public RegistrationFormElement(WebDriver webDriver) {
        super(webDriver);
    }

    public RegistrationFormElement enterTextIntoInputUsername(String username) {
        enterTextIntoInput(inputUsername, username);
        return this;
    }

    public RegistrationFormElement enterTextIntoInputEmail(String email) {
        enterTextIntoInput(inputEmail, email);
        return this;
    }

    public RegistrationFormElement enterTextIntoInputPassword(String password) {
        enterTextIntoInput(inputPassword, password);
        return this;
    }

    public void clickOnButtonSingUp() {
        clickOnElement(buttonSignUp);
    }

    public void clickOnButtonSingUpWithEnterButton() {
        pressTheEnterKey(buttonSignUp);
    }

    public RegistrationFormElement fillRegistrationFormWithInvalidCreds() {
        enterTextIntoInputUsername("sv");
        enterTextIntoInputEmail("sv");
        enterTextIntoInputPassword("sv");
        clickOnButtonSingUp();
        return this;
    }

    public RegistrationFormElement checkIsValidationMessageDisplayedForUsernameField() {
        checkIsElementVisible(divValidationMessageForUsername);
        return this;
    }

    public RegistrationFormElement checkIsValidationMessageDisplayedForEmailField() {
        checkIsElementVisible(divValidationMessageForEmail);
        return this;
    }

    public RegistrationFormElement checkIsValidationMessageDisplayedForPasswordField() {
        checkIsElementVisible(divValidationMessageForPassword);
        return this;
    }

    public RegistrationFormElement checkErrorMessages(String messages) {
        String[] expectedErrors = messages.split(";");
        webDriverWait05.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorMessagesLocator), expectedErrors.length));

        Util.waitABit(1);
        Assert.assertEquals("Number of messages ", expectedErrors.length, listErrorMessages.size());

        ArrayList<String> actualErrors  = new ArrayList<>();
        for (WebElement element : this.listErrorMessages){
            actualErrors.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrors.length; i++){
            softAssertions.assertThat(expectedErrors[i])
                    .as("Error " + i)
                    .isIn(actualErrors);
        }
        softAssertions.assertAll(); //check all assertion


        return this;
    }

    public RegistrationFormElement tabToUserNameField(){
        tabToElementWithActions(inputUsername);
        return this;
    }

    public RegistrationFormElement tabToEmailField(){
        tabToElementWithActions(inputEmail);
        return this;
    }

    public RegistrationFormElement tabToPasswordField(){
        tabToElementWithActions(inputPassword);
        return this;
    }
}