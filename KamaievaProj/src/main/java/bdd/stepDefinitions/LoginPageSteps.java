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
        pageProvider.getLoginPage().openLoginPage();
    }

    @When("I login with valid credentials")
    public void iLoginWithValidCredentials() {
        pageProvider.getLoginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        pageProvider.getLoginPage().clickOnButtonSignIn();
    }

    @When("I enter {string} into input Login in Login page")
    public void iEnterLoginIntoInputLoginInLoginPage(String login) {
        pageProvider.getLoginPage().enterTextIntoInputLogin(login);
    }

    @When("I enter {string} into input Password in Login page")
    public void i_enter_into_input_password_in_login_page(String password) {
        pageProvider.getLoginPage().enterTextIntoInputPassword(password);
    }

    @When("I click on button SignIn in Login page")
    public void i_click_on_button_sign_in_in_login_page() {
        pageProvider.getLoginPage().clickOnButtonSignIn();
    }

    @Then("I see alert message with text {string}")
    public void iSeeAlertMessageWithTextInvalidUsernameOrPassword(String message) {
        pageProvider.getLoginPage().checkTextInAlertInCenter(message);
    }

    @Then("I see error message with text {string}")
    public void iSeeErrorMessageWithText(String message) {
        pageProvider.getLoginPage().checkErrorsMessages(message);
    }

    @When("I enter {string} into input Username Register in Login page")
    public void iEnterIntoInputUsernameRegisterInLoginPage(String username) {
        pageProvider.getLoginPage().enterTextIntoInputUsernameRegister(username);
    }

    @When("I enter {string} into input Email Register in Login page")
    public void iEnterIntoInputEmailRegisterInLoginPage(String email) {
        pageProvider.getLoginPage().enterTextIntoInputEmailRegister(email);
    }

    @When("I enter {string} into input Password Register in Login page")
    public void iEnterIntoInputPasswordRegisterInLoginPage(String password) {
        pageProvider.getLoginPage().enterTextIntoInputPasswordRegister(password);
    }
}

