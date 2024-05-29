package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.Then;

public class MyProfileSteps extends MainSteps{
    public MyProfileSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }


    @Then("I was redirected to MyProfile page")
    public void i_was_redirected_to_my_profile_page() {
        pageProvider.myProfilePage().checkIsRedirectToMyProfilePage();
    }

    @Then("I see {int} posts in Post list on Profile Page")
    public void i_see_posts_in_post_list_on_profile_page(int numberOfPosts) {
        pageProvider.myProfilePage().checkNumberOfPosts(numberOfPosts);
    }
}
