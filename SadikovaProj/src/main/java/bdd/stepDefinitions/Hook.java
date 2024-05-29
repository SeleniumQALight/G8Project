package bdd.stepDefinitions;

import api.ApiHelper;
import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hook {
    WebDriverHelper webDriverHelper;
    private ApiHelper apiHelper = new ApiHelper();

    public Hook(WebDriverHelper webDriverHelper) {
        this.webDriverHelper = webDriverHelper;
    }


    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
        webDriverHelper.quitDriver();
    }

    @Before( value = "@deleteAllPostsForDefaultUser", order = 50)
    @After( value = "@deleteAllPostsForDefaultUser", order = 50)
    public void deletePostForDefaultUser() {
        apiHelper.deleteAllPostsTillPresent(TestData.LOGIN_API, apiHelper.getToken(TestData.LOGIN_API, TestData.VALID_PASSWORD_API));
    }
}
