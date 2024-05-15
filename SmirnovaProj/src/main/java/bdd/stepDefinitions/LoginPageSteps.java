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

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        pageProvider.loginPage().openLoginPage();
    }

    @When("I enter valid credentials")
    public void i_enter_valid_credentials() {
        pageProvider.loginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();
    }

    @When("I enter {string} into input Login in Login page")
    public void iEnterLoginIntoInputLoginInLoginPage(String login) {
        pageProvider.loginPage().enterTextIntoInputLogin(login);
    }

    @And("I enter {string} into input Password in Login page")
    public void iEnterPasswordIntoInputPasswordInLoginPage(String password) {
        pageProvider.loginPage().enterTextIntoInputPassword(password);
    }

    @And("I click on button Login in Login page")
    public void iClickOnButtonLoginInLoginPage() {
        pageProvider.loginPage().clickOnButtonSignIn();
    }

    @Then("I see error message with text {string}")
    public void iSeeErrorMessageWithTextInvalidUsernamePassword(String errorMessage) {
        pageProvider.loginPage().checkErrorsMessagesLogin(errorMessage);
    }
}
