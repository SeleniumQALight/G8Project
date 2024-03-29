package bdd.stepDefinitions;

import api.ApiHelper;
import data.TestData;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;

public class ApiSteps {
    final String DEFAULT = "default";
    private ApiHelper apiHelper = new ApiHelper();

    @Given("I create '{int}' new posts via Api for {string} user and {string} password")
    public void iCreateNumberOfPostsNewPostsViaApiForDefaultUserAndDefaultPassword(
            Integer numberOfPosts, String userName, String password, DataTable dataTable) {
        if (DEFAULT.equalsIgnoreCase(userName)) {
            userName = TestData.PERSONAL_LOGIN_UI;
        }
        if (DEFAULT.equalsIgnoreCase(password)) {
            password = TestData.VALID_PASSWORD_UI;
        }
        String token = apiHelper.getToken(userName, password);
        for (int i = 0; i < numberOfPosts; i++) {
            apiHelper.createPost(token, dataTable.asMap(), i);
        }
    }
}
