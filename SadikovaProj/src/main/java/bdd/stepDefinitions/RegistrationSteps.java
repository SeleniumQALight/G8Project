package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegistrationSteps extends MainSteps{

    public RegistrationSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @When("I enter {string} into input UserName in RegistrationForm")
    public void iEnterIntoInputUserNameInRegistrationForm(String userName) {
        pageProvider.mainPage().enterTextInUserNameField(userName);
    }

    @And("I enter {string} into input Email in Login page")
    public void iEnterIntoInputEmailInLoginPage(String email) {
        pageProvider.mainPage().enterTextInEmailField(email);
    }

    @And("I enter {string} into input Password in RegistrationForm")
    public void iEnterIntoInputPasswordInRegistrationForm(String password) {
        pageProvider.mainPage().enterTextInPasswordField(password);
    }

    @Then("I see alert {string}")
    public void iSeeAlert(String expectedMessages) {
        pageProvider.mainPage().checkErrorsMessage(expectedMessages);
    }
}
