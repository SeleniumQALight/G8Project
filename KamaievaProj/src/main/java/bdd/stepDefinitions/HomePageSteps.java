package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.Then;

public class HomePageSteps extends MainSteps{
    public HomePageSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I see avatar on the Home page")
    public void iSeeAvatarOnTheHomePage() {
        pageProvider.getHomePage().getHeader().checkIsButtonSignOutVisible();
    }
}
