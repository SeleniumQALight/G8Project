package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.Then;


public class MyProfileSteps extends MainSteps {

    public MyProfileSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I redirect to MyProfile page")
    public void iRedirectToMyProfilePage() {
        pageProvider.getMyProfilePage().chckIsRedirectToMyProfilePage();
    }

    @Then("I see '{int}' posts in Posts list on MyProfile page")
    public void iSeePostsInPostsListOnMyProfilePage(Integer numberOfPosts) {
        pageProvider.getMyProfilePage().checkNumberOfPosts(numberOfPosts);
    }
}
