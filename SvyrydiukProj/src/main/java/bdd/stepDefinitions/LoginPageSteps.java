package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageSteps extends MainSteps{
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
    public void iEnterLoginIntoInputLoginInLoginPage(String login) {
        pageProvider.loginPage().enterTextIntoInputLogin(login);
    }

    @When("I enter {string} into input Password in Login page")
    public void i_enter_into_input_password_in_login_page(String password) {
        pageProvider.loginPage().enterTextIntoInputPassword(password);
    }
    @When("I click on button SignIn in Login page")
    public void i_click_on_button_sign_in_in_login_page() {
        pageProvider.loginPage().clickOnButtonSignIn();
    }

    @Then("I see alert message with text {string}")
    public void iSeeAlertMessageWithTextInvalidUsernamePassword(String message) {
        pageProvider.loginPage().checkTextInAlertInCenter(message);

    }

    @When("I enter {string} into input UsernameRegistration in Login page")
    public void iEnterIntoInputUsernameRegistrationInLoginPage(String username) {
        pageProvider.loginPage().enterTextIntoInputUsernameRegistration(username);
    }

    @When("I enter {string} into input EmailRegistration in Login page")
    public void iEnterIntoInputEmailRegistrationInLoginPage(String email) {
        pageProvider.loginPage().enterTextIntoInputEmailRegistration(email);
    }

    @When("I enter {string} into input PasswordRegistration in Login page")
    public void iEnterIntoInputPasswordRegistrationInLoginPage(String password) {
        pageProvider.loginPage().enterTextIntoInputPasswordRegistration(password);
    }

    @When("I click on button SignUpForOurApp in Login page")
    public void iClickOnButtonSignUpForOurAppInLoginPage() {
        pageProvider.loginPage().clickOnButtonSignUp();
    }

    @Then("I see warning message with text {string}")
    public void iSeeWarningMessageWithText(String message) {
        pageProvider.loginPage().checkErrorsMessages(message);
    }
}
