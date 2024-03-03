package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.Then;

public class MyProfileSteps extends MainSteps {
    public MyProfileSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }
    @Then("I redirect to MyProfile page")
    public void iRedirectToMyProfilePage() {
        pageProvider.getMyProfilePage().checkIsRedirectToMyProfilePage();
    }

    @Then("I see '{int}' posts in Posts lists on myProfile page")
    public void iSeePostsInPostsListsOnMyProfilePage(Integer numberOfPosts) {
        pageProvider.getMyProfilePage().checkNumberOfTotalPosts(numberOfPosts);
    }
}