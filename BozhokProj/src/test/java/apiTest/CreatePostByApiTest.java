package apiTest;

import api.ApiHelper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class CreatePostByApiTest {
    String token;
    ApiHelper apiHelper = new ApiHelper();

    @Before
    public void deletePosts() {
        token = apiHelper.getToken();
        System.out.println("----" + token + "----");

    }

    @Test
    @Ignore
    public void createPostByApi() {
    }
}


