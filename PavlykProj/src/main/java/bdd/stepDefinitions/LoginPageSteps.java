package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageSteps extends MainSteps{

    final String LONG_PASSWORD = "tr_x30";

    public LoginPageSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I open Login page")
    public void i_open_login_page() {
        pageProvider.loginPage().openLoginPage();

    }

    @When("I login With valid cred")
    public void iLoginWithValidCred() {
        pageProvider.loginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();

    }

    @When("I enter {string} into input Login in Login page")
    public void iEnterLoginIntoInputLoginInLoginPage(String login) {
        pageProvider.loginPage().enterTextIntoInputLogin(login);
    }

    @When("I enter {string} into input Password in Login page")
    public void iEnterPasswordIntoInputPasswordInLoginPage(String password) {
        pageProvider.loginPage().enterTextIntoInputPassword(password);
    }

    @When("I click on button SinIn in Login page")
    public void iClickOnButtonSinInInLoginPage() {
        pageProvider.loginPage().clickOnButtonSignIn();
    }

    @Then("I see alert message with text {string}")
    public void iSeeAlertMessageWithTextInvalidUsernamePassword(String message) {
        pageProvider.loginPage().checkTextInAlertInCenter(message);
    }

    @When("I enter {string} into input Username in Registration form in Login page")
    public void iEnterIntoInputUsernameInRegistrationFormInLoginPage(String userName) {
        pageProvider.loginPage().enterTextIntoInputUsernameRegistration(userName);
    }

    @When("I enter {string} into input Email in Registration form in Login page")
    public void iEnterIntoInputEmailInRegistrationFormInLoginPage(String email) {
        pageProvider.loginPage().enterTextIntoInputEmailRegistration(email);
    }

    @When("I enter {string} into input Password in Registration form in Login page")
    public void iEnterIntoInputPasswordInRegistrationFormInLoginPage(String password) {
        if (password.equalsIgnoreCase(LONG_PASSWORD)) {
            password = LONG_PASSWORD.repeat(30);
        }
        pageProvider.loginPage().enterTextIntoInputPasswordRegistration(password);
    }

    @Then("I see warning messages {string} in the  fields.")
    public void iSeeWarningMessagesInTheFields(String expectedMessage) {
        pageProvider.loginPage().checkErrorsMessages(expectedMessage);
    }
}
