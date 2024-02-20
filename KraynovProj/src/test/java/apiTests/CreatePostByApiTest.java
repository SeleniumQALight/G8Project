package apiTests;

import api.ApiHelper;
import com.beust.ah.A;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import signOutTests.SignOutTest;

public class CreatePostByApiTest {

    String token;
    ApiHelper apiHelper = new ApiHelper();

    @Before
    public void deletePosts(){
        token = apiHelper.getToken();
        System.out.println("--" + token + "--");
    }

    @Test
    @After
    public void createPostByApi(){

    }
}
