package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomePageSteps extends MainSteps{
    final String DEFAULT = "default";

    public HomePageSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I see avatar on Home page")
    public void iSeeAvatarOnHomePage() {
        pageProvider.homePage().getHeader().isButtonSignOutVisble();

    }


    @Given("I open Home page as {string} user with {string} password")
    public void iOpenHomePageAsDefaultUserWithDefaultPassword(String userName, String password) {
        if(DEFAULT.equalsIgnoreCase(userName)){
            userName = TestData.VALID_LOGIN_API;
        }
        if(DEFAULT.equalsIgnoreCase(password)){
            password = TestData.VALID_PASSWORD_API;
        }
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(userName);
        pageProvider.loginPage().enterTextIntoInputPassword(password);
        pageProvider.loginPage().clickOnButtonSignIn();
        pageProvider.homePage().checkIsRedirectToHomePage();
    }

    @When("I click on MyProfile button")
    public void iClickOnMyProfileButton() {
        pageProvider.homePage().getHeader().clickOnButtonMyProfile();
    }
}
