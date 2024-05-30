package bdd.stepDefinitions;

import api.ApiHelper;
import bdd.helpers.WebDriverHelper;
import io.cucumber.datatable.DataTable;
import data.TestData;
import io.cucumber.java.en.Given;

public class ApiSteps extends MainSteps {
    final private String DEFAULT = "default";
    private ApiHelper apiHelper = new ApiHelper();
    public ApiSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I create {int} new posts via API for {string} user and {string} password")
    public void iCreateNewPostsViaAPIForDefaultUserAndDefaultPassword(int numberOfPosts, String userName, String password, DataTable dataTable) {
        if (DEFAULT.equalsIgnoreCase(userName)){
            userName = TestData.LOGIN_API;
        }
        if (DEFAULT.equalsIgnoreCase(password)){
            password = TestData.VALID_PASSWORD_API;
        }
        String token = apiHelper.getToken(userName,password);
        apiHelper.createPosts(numberOfPosts, token, dataTable.asMap());


    }
}

