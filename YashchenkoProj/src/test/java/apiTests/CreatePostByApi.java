package apiTests;

import api.ApiHelper;
import org.junit.Before;
import org.junit.Test;

public class CreatePostByApi {
    String token;
    ApiHelper apiHelper = new ApiHelper();

    @Before
    public void deletePosts() {
        token = apiHelper.getToken();
        System.out.println("---" + token + "---");
    }

    @Test
    public void createPostByApi() {

    }
}
