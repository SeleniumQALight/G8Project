package bdd.stepDefinitions;

import api.ApiHelper;
import data.TestData;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;

import static data.TestData.VALID_LOGIN_API;

public class ApiSteps {
    final String DEFAULT = "default";
    private ApiHelper apiHelper= new ApiHelper();

//    I create '<numberOfPosts>' new posts via API for 'default' user and 'default' password
@Given("I create '{int}' new posts via API for {string} user and {string} password")
    public void iCreateNewPostsViaAPIForDefaultUserAndDefaultPassword(Integer numberOfPosts, String userName, String password, DataTable dataTable) {
        if (DEFAULT.equalsIgnoreCase(userName)) {
            userName = VALID_LOGIN_API;
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
