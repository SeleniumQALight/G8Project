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
        pageProvider.loginPage().openLoginPage();

    }

    @When("I login with valid credentials")
    public void iLoginWithValidCredentials() {
        pageProvider.loginPage().enterTextInToInputLogin(TestData.VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextInToInputPassword(TestData.VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSingIn();
    }

    @When("I enter {string} into input login in Login page")
    public void iEnterLoginIntoInputLoginInLoginPage(String login) {
        pageProvider.loginPage().enterTextInToInputLogin(login);
    }

    @When("I enter {string} into input Password in Login page")
    public void iEnterPasswordIntoInputPasswordInLoginPage(String password){
        pageProvider.loginPage().enterTextInToInputPassword(password);
    }


    @And("I click on button SignIn in Login page")
    public void iClickOnButtonSignInInLoginPage() {
        pageProvider.loginPage().clickOnButtonSingIn();

    }


    @Then("I see alert message with text {string}")
    public void iSeeAlertMessageWithTextInvalidUsernamePassword(String message) {
        pageProvider.loginPage().checkTextInAlertCenter(message);
    }
}
