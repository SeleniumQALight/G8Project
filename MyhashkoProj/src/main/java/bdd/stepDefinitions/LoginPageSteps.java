package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.en.And;
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

    @When("I enter {string} into input login in Login page")
    public void iEnterLoginIntoInputInLoginPage(String login) {
        pageProvider.loginPage().enterTextIntoInputLogin(login);

    }

    @When("I enter {string} into input password in Login page")
    public void iEnterPasswordIntoInputPasswordInLoginPage(String password) {
        pageProvider.loginPage().enterTextIntoInputPassword(password);

    }

    @When("I click on Button SignIn in Login page")
    public void iClickOnButtonSignInInLoginPage() {
        pageProvider.loginPage().clickOnButtonSignIn();
    }

    @Then("I see alert message with text {string}")
    public void iSeeAlertMessageWithTextInvalidUsernamePassword(String message) {
        pageProvider.loginPage().checkTextInAlertInCenter(message);
    }

    @When("I enter {string} into input UserNameRegistration in Login page")
    public void iEnterUserNameIntoInputUserNameRegistrationInLoginPage(String login) {
        pageProvider.loginPage().enterTextIntoInputUsernameRegister(login);
    }

    @When("I enter {string} into input EmailRegistration in Login page")
    public void iEnterEmailIntoInputEmailRegistrationInLoginPage(String email) {
        pageProvider.loginPage().enterTextIntoInputEmailRegister(email);
    }

    @And("I enter {string} into input PasswordRegistration in Login page")
    public void iEnterPasswordIntoInputPasswordRegistrationInLoginPage(String password) {
        pageProvider.loginPage().enterTextIntoInputPasswordRegister(password);
    }

    @And("I click on button Register in Login page")
    public void iClickOnButtonRegisterInLoginPage() {
        pageProvider.loginPage().clickOnButtonSignUpRegister();
    }

    @Then("I see error message {string} in Login page")
    public void iSeeErrorMessageErrorMessageInLoginPage(String errorMessage) {
        pageProvider.loginPage().checkErrorMessages(errorMessage);

    }
}
