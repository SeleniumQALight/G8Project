package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.Then;

public class MyProfileSteps extends MainSteps{
    public MyProfileSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I redirect to MyProfile")
    public void iRedirectToMyProfile() {
        pageProvider.myProfilePage().checkIsRedirectedToMyProfilePage();
    }
    @Then("I see '{int}' posts in Posts list on MyProfilePage")
    public void iSeePostsInPostsListOnMyProfilePage(Integer numberOfPosts) {
        pageProvider.myProfilePage().checkNumberOfPosts(numberOfPosts);
    }
}
