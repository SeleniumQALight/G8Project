package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

public class LoginPageSteps extends MainSteps{

    public LoginPageSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I open Login page")
    public void iOpenLoginPage() {
    pageProvider.loginPage().openLoginPage();
    }

    @When("I login with valid cred")
    public void iLoginWithValidCred() {
    pageProvider.loginPage().enterTextInToInputLogin(VALID_LOGIN_UI);
    pageProvider.loginPage().enterTextInToInputPassword(VALID_PASSWORD_UI);
    pageProvider.loginPage().clickOnButtonSignIn();
    }

    @When("I enter{string} into input Login in Login page")
    public void iEnterLoginIntoInputLoginInLoginPage(String login) {
    pageProvider.loginPage().enterTextInToInputLogin(login);
    }

    @When("I enter {string} into input Password in Login page")
    public void iEnterPasswordIntoInputPasswordInLoginPage(String password) {
    pageProvider.loginPage().enterTextInToInputPassword(password);
    }

    @When("I click on button SignIn in Login page")
    public void iClickOnButtonSignInInLoginPage() {
    pageProvider.loginPage().clickOnButtonSignIn();
    }

    @Then("I see alert message with text {string}")
    public void iSeeAlertMessageWithTextInvalidUsernamePassword(String message) {
        pageProvider.loginPage().checkTextInAlertInCenter(message);
    }

    @When("I enter{string} into input Username in Login page")
    public void iEnterIntoInputUsernameInLoginPage(String username) {
    pageProvider.loginPage().enterTextInToInputUserName(username);
    }

    @When("I enter {string} into input Password in Login page for registry")
    public void iEnterIntoInputPasswordInLoginPageForRegistry(String password) {
    pageProvider.loginPage().enterTextInToInputPasswordForSignUp(password);
    }

    @When("I enter {string} into input Email in Login page")
    public void iEnterIntoInputEmailInLoginPage(String email) {
    pageProvider.loginPage().enterTextInToInputEmail(email);
    }

    @When("I click on button SignUp in Login page")
    public void iClickOnButtonSignUpInLoginPage() {
    pageProvider.loginPage().clickOnButtonSignUp();
    }

    @Then("I see error messages with text {string}")
    public void iSeeErrorMessageWithText(String expectedMessages) {
    pageProvider.loginPage().checkErrorMessages(expectedMessages);
    }
}