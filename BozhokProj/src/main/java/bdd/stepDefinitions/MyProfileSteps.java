package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.Then;

public class MyProfileSteps extends MainSteps {
    public MyProfileSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I redirect to MyProfilePage page")
    public void iRedirectToMyProfilePagePage() {
        pageProvider.getMyProfilePage().checkIsRedirectOnMyProfilePage();
    }

    @Then("I see '{int}' posts in Posts lists on MyProfile page")
    public void iSeePostsInPostsListsOnMyProfilePage(Integer numberOfPosts) {
        pageProvider.getMyProfilePage().checkNumberOfPosts(numberOfPosts);
    }
}
