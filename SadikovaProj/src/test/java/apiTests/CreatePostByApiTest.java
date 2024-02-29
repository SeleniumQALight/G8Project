package apiTests;

import api.ApiHelper;
import loginTests.SignOutTest;
import org.junit.Before;
import org.junit.Test;

public class CreatePostByApiTest {

    ApiHelper apiHelper = new ApiHelper();
    String token;

    @Before
    public void deletePost(){
        token = apiHelper.getToken();
        System.out.println("--" + token + "--");

    }

    @Test
    public void createPostByApi(){

    }
}
