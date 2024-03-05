package bdd.stepDefinitions;

import api.ApiHelper;
import bdd.helpers.WebDriverHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hook {

    WebDriverHelper webDriverHelper;
    ApiHelper apiHelper = new ApiHelper();
    public Hook(WebDriverHelper webdriverHelper){
        this.webDriverHelper = webdriverHelper;
    }
    @Before(order = 0)
    public void setUp() {

    }

    @After(order = 0)
    public void tearDown() {
        webDriverHelper.quitDriver();
    }

    @Before(value = "@deleteAllPostsForDefaultUser", order = 50)
    @After(value = "@deleteAllPostsForDefaultUser", order = 50)
    public void deletePostsForDefaultUser() {
        apiHelper.deleteAllPostsTillPresent();
    }

}
