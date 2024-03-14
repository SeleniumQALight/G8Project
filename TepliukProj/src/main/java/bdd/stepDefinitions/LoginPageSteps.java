package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import data.TestData;
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
        pageProvider.loginPage().enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();
    }

    @When("I enter {string} into input Login in Login page")
    public void iEnterIntoInputLoginInLoginPage(String login) {
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


    @When("I enter {string} into input Username of Registration form on Login page")
    public void iEnterUsernameIntoInputUsernameOfRegistrationFormOnLoginPage(String userName) {
        pageProvider.loginPage().enterTextIntoRegistrationUserNameField(userName);
    }

    @When("I enter {string} into input Email of Registration form on Login page")
    public void iEnterEmailIntoInputEmailOfRegistrationFormOnLoginPage(String email) {
        pageProvider.loginPage().enterTextIntoRegistrationEmailField(email);
    }

    @When("I enter {string} into input Password of Registration form on Login page")
    public void iEnterPasswordIntoInputPasswordOfRegistrationFormOnLoginPage(String password) {
        pageProvider.loginPage().enterTextIntoRegistrationPasswordField(password);
    }

    @Then("I see {string} error message")
    public void iSeeError_messageErrorMessage(String message) {
        pageProvider.loginPage().checkErrorsMessage(message);
    }
}
