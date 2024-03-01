package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

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
        pageProvider.loginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();
    }

    @When("I enter {string} ito input Login in Login page")
    public void iEnterLoginItoInputLoginInLoginPage(String login) {
        pageProvider.loginPage().enterTextIntoInputLogin(login);
    }

    @When("I enter {string} into input Password in Login page")
    public void iEnterPasswordIntoInputPasswordInLoginPage(String password) {
        pageProvider.loginPage().enterTextIntoInputPassword(password);
    }

    @When("I click button SignIn in Login page")
    public void i_click_button_sign_in_in_login_page() {
        pageProvider.loginPage().clickOnButtonSignIn();
    }

    @Then("I see alert message with text {string}")
    public void iSeeAlertMessageWithTextInvalidUsernamePassword(String message) {
        pageProvider.loginPage().checkTextInAlertInCenter(message);

    }

    @When("I enter {string} in username field and {string} in email field and {string} in password field on Login page")
    public void iEnterInUsernameFieldAndInEmailFieldAndInPasswordFieldOnLoginPage(String userName, String email, String password) {
        pageProvider.loginPage().enterTextRegistrationUserNameField(userName);
        pageProvider.loginPage().enterTextRegistrationEmailField(email);
        pageProvider.loginPage().enterTextRegistrationPasswordField(password);
    }


    @Then("I see '{int}' error message on Login page")
    public void iSeeErrorMessageOnLoginPage(Integer numberOfErrors) {
        pageProvider.loginPage().checkNumberOfErrorsMessagesInRegistrationForm(numberOfErrors);
    }

    @And("I click on Register button on Login page")
    public void iClickOnRegisterButtonOnLoginPage() {
        pageProvider.loginPage().clickOnButtonSignUpForOurApp();
    }
}

