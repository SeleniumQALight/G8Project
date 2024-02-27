package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.Given;
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
}
