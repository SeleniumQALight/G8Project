package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegistrationFormSteps extends MainSteps{
    public RegistrationFormSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @When("I enter {string} into input UserName in Registration form")
    public void iEnterUserNameIntoInputUserNameInRegistrationForm(String userName) {
        pageProvider.loginPage().getRegistrationForm().enterTextIntoInputUsername(userName);
    }

    @When("I enter {string} into input Email in Registration form")
    public void iEnterEmailIntoInputEmailInRegistrationForm(String email) {
        pageProvider.loginPage().getRegistrationForm().enterTextIntoInputEmail(email);
    }

    @And("I enter {string} into input Password in Registration form")
    public void iEnterPasswordIntoInputPasswordInRegistrationForm(String password) {
        pageProvider.loginPage().getRegistrationForm().enterTextIntoInputPassword(password);
    }

    @And("I click on button Sign Up in Registration form")
    public void iClickOnButtonSignUpInRegistrationForm() {
        pageProvider.loginPage().getRegistrationForm().clickOnButtonSingUp();
    }

    @Then("I see alert message with text {string} in Registration form")
    public void iSeeAlertMessageWithTextExpectedMessagesInRegistrationForm(String message) {
        pageProvider.loginPage().getRegistrationForm().checkErrorMessages(message);
    }
}
