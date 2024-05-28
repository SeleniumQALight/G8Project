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
        pageProvider.mainPage().openMainPage();
    }

    @When("I login with valid cred")
    public void iLoginWithValidCred() {
        pageProvider.mainPage().enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        pageProvider.mainPage().enterTextIntoInputPassword(TestData.VALID_PASSWORD);
        pageProvider.mainPage().clickOnButtonSignIn();
    }

    @When("I enter {string} into input Login in Login page")
    public void iEnterLoginIntoInputLoginInLoginPage(String login) {
        pageProvider.mainPage().enterTextIntoInputLogin(login);

    }

    @And("I enter {string} into input Password in Login page")
    public void iEnterPasswordIntoInputPasswordInLoginPage(String pass) {
        pageProvider.mainPage().enterTextIntoInputPassword(pass);

    }

    @And("I click on button SignIn in Login page")
    public void iClickOnButtonSignInInLoginPage() {
        pageProvider.mainPage().clickOnButtonSignIn();

    }

    @Then("I see alert message with text {string}")
    public void iSeeAlertMessageWithTextInvalidUsernamePassword(String text) {
        pageProvider.mainPage().checkErrorMessageInLoginForm(text);
    }
}
