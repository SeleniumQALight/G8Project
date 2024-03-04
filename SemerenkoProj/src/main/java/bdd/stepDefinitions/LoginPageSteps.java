package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageSteps extends MainSteps {
    public LoginPageSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I open Login page")
    public void i_open_login_page() {
        pageProvider.loginPage().openLoginPage();

    }

    @When("I login with valid cred")
    public void iLoginWithValidCred() {
        pageProvider.loginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPass(TestData.VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();
    }

    @When("I enter {string} into input Login in Login page")
    public void iEnterLoginIntoInputLoginInLoginPage(String login) {
        pageProvider.loginPage().enterTextIntoInputLogin(login);
    }

    @When("I enter {string} into input Password in Login page")
    public void i_enter_into_input_password_in_login_page(String pass) {
        pageProvider.loginPage().enterTextIntoInputPass(pass);
    }

    @When("I click on button SignIn Login page")
    public void i_click_on_button_sign_in_login_page() {
        pageProvider.loginPage().clickOnButtonSignIn();
    }

    @Then("I see alert message with text {string}")
    public void iSeeAlertMessageWithTextInvalidUsernamePassword(String message) {
        pageProvider.loginPage().checkTextInAlertInCenter(message);
    }

    @When("I enter {string} into input Registration Login in Login page")
    public void iEnterLoginIntoInputRegistrationLoginInLoginPage(String userName) {
        pageProvider.loginPage().enterTextIntoRegistrationUsernameField(userName);
    }

    @When("I enter {string} into input Registration Email in login page")
    public void iEnterEmailIntoInputRegistrationEmailInLoginPage(String email) {
        pageProvider.loginPage().enterTextIntoRegistrationEmailField(email);
    }

    @When("I enter {string} into input Registration Password in Login page")
    public void iEnterPasswordIntoInputRegistrationPasswordInLoginPage(String password) {
        pageProvider.loginPage().enterTextIntoRegistrationPasswordField(password);
    }

    @Then("I see {string} in registration fields")
    public void iSeeValidationMessagesInRegistrationFields(String messages) {
        pageProvider.loginPage().checkErrorMessage(messages);
    }
}
