package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class HomePageSteps extends MainSteps {
    final private String DEFAULT = "default";
    public HomePageSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I see avatar on Home page")
    public void i_see_avatar_on_home_page() {
        pageProvider.headerElement().checkMyProfileIconIsVisible();
    }

    @Given("I open Home Page as {string} user and {string} password")
    public void iOpenHomePageAsUserAndPassword(String userName, String password) {
        if(DEFAULT.equalsIgnoreCase(userName)){
            userName = TestData.LOGIN_API;
        }
        if(DEFAULT.equalsIgnoreCase(password)){
            password = TestData.VALID_PASSWORD_API;
        }
        pageProvider.mainPage().openMainPage();
        pageProvider.mainPage().enterTextIntoInputLogin(userName);
        pageProvider.mainPage().enterTextIntoInputPassword(password);
        pageProvider.mainPage().clickOnButtonSignIn();
        pageProvider.homePage().checkIsRedirectToHomePage();
    }
}
