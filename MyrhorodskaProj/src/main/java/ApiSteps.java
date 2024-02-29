import api.ApiHelper;
import data.TestData;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;

public class ApiSteps {
    final String DEFAULT = "default";
    private ApiHelper apiHelper = new ApiHelper();
    @Given("I create {int} new posts via API for {string} user and {string} password")
    public void iCreateNewPostsViaAPIForDefaultUserAndDefaultPassword(
            Integer numberOfPosts, String username, String password, DataTable dataTable) {
        if (DEFAULT.equalsIgnoreCase(username)){
            username = TestData.VALID_LOGIN_API;
        }
        if(DEFAULT.equalsIgnoreCase(password)){
            password = TestData.VALID_PASSWORD_API;
        }

       String token = apiHelper.getToken(username,password);//get token every time
        for (int i = 0; i<numberOfPosts;i++)//щоб делілька разів пробігав
             { apiHelper.createPost(token,dataTable.asMap(),i);
             }
    }
}
