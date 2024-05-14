package api;

import api.dto.responseDto.PostDto;
import data.TestData;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiHelper {


    Logger logger = Logger.getLogger(getClass());

    //метод с прошлого курса
//    public ValidatableResponse getAllPostByUserRequest(String userName, int statusCode) {
//        return given()
//                .spec(requestSpecification)
//                .when()
//                .get(EndPoints.POSTS_BY_USER, userName)
//                .then()
//                .spec(responseSpecification.statusCode(statusCode));
//
//
//    }

    //response
public ValidatableResponse getAllPostByUserRequest(String userName, int statusCode) {
    return given()
            .contentType(ContentType.JSON)
            .log().all()
            .when()
            .get(EndPoints.POSTS_BY_USER, userName)
            .then()
            .log().all()
            .statusCode(statusCode);


}


    //response 200
    public ValidatableResponse getAllPostByUserRequest(String userName) {
        return getAllPostByUserRequest(userName, HttpStatus.SC_OK);
    }

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .addFilter(new AllureRestAssured())
            .log(LogDetail.ALL)
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();




    public PostDto[] getAllPostsByUserAsDTO(String userName){
        return getAllPostByUserRequest(userName).extract().response().getBody().as(PostDto[].class);
    }



    public String getToken(){
        return getToken(TestData.LOGIN_API, TestData.VALID_PASSWORD_API);
    }

    public String getToken(String userName, String password){
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", userName);
        requestBody.put("password", password);

        ResponseBody responseBody =
                given()
                        .spec(requestSpecification)
                        .body(requestBody.toMap())
                        .when()
                        .post(EndPoints.LOGIN)
                        .then()
                        .spec(responseSpecification)
                        .extract().response().getBody();
        return responseBody.asString().replace("\"", "");
    }

    public void deleteAllPostTillPresent(String loginApi, String token) {
        PostDto[] listOfPosts = getAllPostsByUserAsDTO(loginApi);
        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(token, listOfPosts[i].getId());
            logger.info(String.format("Post with id %s and title %s was deleted", listOfPosts[i].getId(), listOfPosts[i].getTitle()));

        }
       getAllPostByUserRequest(loginApi, HttpStatus.SC_OK);


    }

    private void deletePostById(String token, String id) {
        HashMap<String, String> bodyRequest = new HashMap<>();
        bodyRequest.put("token", token);
        String actualResponse =
                given()
                        .spec(requestSpecification)
                        .body(bodyRequest)
                        .when()
                        .delete(EndPoints.DELETE_POST, id)
                        .then()
                        .spec(responseSpecification)
                        .extract().response().body().asString();

    }

}
