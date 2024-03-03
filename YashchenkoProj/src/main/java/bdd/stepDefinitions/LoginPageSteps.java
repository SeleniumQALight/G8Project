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
        pageProvider.getLoginPage().openLoginPage();
    }

    @When("I login with valid credentials")
    public void iLoginWithValidCredentials() {
        pageProvider.getLoginPage().enterTextIntoInputLogin(TestData.DEFAULT_VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword(TestData.DEFAULT_VALID_PASSWORD_UI);
        pageProvider.getLoginPage().clickOnButtonSignIn();
    }

    @When("I enter {string} into input Login on Login page")
    public void iEnterLoginIntoInputLoginOnLoginPage(String login) {
        pageProvider.getLoginPage().enterTextIntoInputLogin(login);
    }

    @When("I enter {string} into input Password on Login page")
    public void iEnterPasswordIntoInputPasswordOnLoginPage(String password) {
        pageProvider.getLoginPage().enterTextIntoInputPassword(password);
    }

    @When("I click on button SignIn on Login page")
    public void iClickOnButtonSignInOnLoginPage() {
        pageProvider.getLoginPage().clickOnButtonSignIn();
    }

    @Then("I see invalid alert message with text {string}")
    public void iSeeInvalidAlertMessageWithTextInvalidUsernamePassword(String message) {
        pageProvider.getLoginPage().checkTextInAlertInCenter(message);

    }
}
