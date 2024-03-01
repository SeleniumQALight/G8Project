package bdd.stepDefinitions;

import api.ApiHelper;
import data.TestData;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;

public class ApiSteps {

    final String DEFAULT = "default";
    private ApiHelper apiHelper = new ApiHelper();

    @Given("I create {int} new posts via API for {string} ser and {string} password")
    public void iCreateNewPostsViaAPIForDefaultSerAndDefaultPassword(Integer numberOfPosts, String userName, String password, DataTable dataTable) {
        if (DEFAULT.equalsIgnoreCase(userName)) {
            userName = TestData.VALID_LOGIN_API;
        }
        if (DEFAULT.equalsIgnoreCase(password)) {
            password = TestData.VALID_PASSWORD_API;
        }
        String token = apiHelper.getToken(userName, password);
        for (int i = 0; i < numberOfPosts; i++) {
            apiHelper.createPost(token, dataTable.asMap(), i);
        }
    }
}
