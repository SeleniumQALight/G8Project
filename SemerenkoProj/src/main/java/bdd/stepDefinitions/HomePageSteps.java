package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomePageSteps extends MainSteps {
    final String DEFAULT = "default";

    public HomePageSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I see avatar on Home page")
    public void iSeeAvatarOnHomePage() {
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
    }

    @Given("I open Home page as {string} user with {string} password")
    public void iOpenHomePageAsDefaultUserWithDefaultPassword(String userName, String password) {
        if (DEFAULT.equalsIgnoreCase(userName)){
            userName = TestData.PERSONAL_LOGIN_UI;
        }
        if (DEFAULT.equalsIgnoreCase(password)){
            password = TestData.VALID_PASSWORD_UI;
        }
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(userName);
        pageProvider.loginPage().enterTextIntoInputPass(password);
        pageProvider.loginPage().clickOnButtonSignIn();
    }

    @When("I click on MyProfile button")
    public void iClickOnMyProfileButton() {
        pageProvider.homePage().getHeader().clickOnButtonProfile(TestData.PERSONAL_LOGIN_UI);
    }
}
