package apiTests;

import api.ApiHelperDemoqa;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class ApiTestsDemoqa {
    ApiHelperDemoqa apiHelperDemoqa = new ApiHelperDemoqa();

    @Test
    public void LoginTest() {
        ValidatableResponse response = apiHelperDemoqa.login();
        String userID = response.extract().body().jsonPath().get("userId");
        String token = response.extract().body().jsonPath().get("token");
        System.out.println("userID: " + userID);
        System.out.println("token: " + token);
    }

    @Test
    public void getBooks (){
       List<Map> isdnList = apiHelperDemoqa.getBooksList();
        for (int i = 0; i < isdnList.size(); i++) {
            System.out.println(isdnList.get(i).get("isbn"));
        }
    }
}
