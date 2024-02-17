package apiTests;

import api.ApiHelper;
import org.junit.Before;
import org.junit.Test;

public class CreatePostByAPITest {
    String token;
    ApiHelper apiHelper = new ApiHelper();

    @Before
    public void deletePost(){
        token = apiHelper.getToken();
        System.out.println("--" + token + "--");
    }

    @Test
    public void createPostByAPI(){

    }
}
