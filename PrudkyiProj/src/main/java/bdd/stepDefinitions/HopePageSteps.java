package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class HopePageSteps extends MainSteps {
    public HopePageSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I see avatar on Home page")
    public void iSeeAvatarOnHomePage() {

        Assert.assertTrue(pageProvider.homePage().getHeader().isButtonSingOutVisible());

    }
}
