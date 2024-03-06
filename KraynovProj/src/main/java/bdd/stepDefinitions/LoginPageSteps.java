package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import data.TestData;
import gherkin.lexer.Pa;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PageProvider;

public class LoginPageSteps extends MainSteps {
    final String LONG_PASSWORD = "tr_x30";
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
        pageProvider.loginPage().enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();
    }

    @When("I enter {string} into input Login in Login page")
    public void iEnterIntoInputLoginInLoginPage(String login) {
        pageProvider.loginPage().enterTextIntoInputLogin(login);
    }

    @And("I enter {string} into input Password in Login page")
    public void i_enter_into_input_password_in_login_page(String password) {
        pageProvider.loginPage().enterTextIntoInputPassword(password);
    }

    @And("I click on button SignIn in Login page")
    public void i_click_on_button_sign_in_in_login_page() {
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
