package api;

import api.dto.responseDto.PostsDto;
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

import java.util.Map;

import static data.TestData.VALID_LOGIN_API;
import static io.restassured.RestAssured.given;

public class ApiHelper {
    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .addFilter(new AllureRestAssured())
            .log(LogDetail.ALL)
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public ValidatableResponse getAllPostsByUserRequest (String userName, int statusCode){
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPoints.POSTS_BY_USER, userName)
                .then()
                .spec(responseSpecification.statusCode(statusCode));
    }

    public ValidatableResponse getAllPostsByUserRequest (String userName){
        return getAllPostsByUserRequest(userName, HttpStatus.SC_OK);
    }

    public PostsDto[] getAllPostsByUserAsDto(String userName){
        return getAllPostsByUserRequest(userName).extract().response().getBody().as(PostsDto[].class);
    }

    public String getToken() {
        return getToken(VALID_LOGIN_API, TestData.VALID_PASSWORD_API);
    }

    public String getToken(String userName, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", userName);
        requestBody.put("password", password);

        ResponseBody responseBody =
                given()
                        .spec(requestSpecification)
                        .body(requestBody.toMap())
                        .when()
                        .post(EndPoints.LOGIN) //URL
                        .then()
                        .spec(responseSpecification)
                        .extract().response().getBody();
        return responseBody.asString().replace("\"", "");
    }

    public void deleteAllPostsTillPresent(String validLoginApi, String token) {
        PostsDto[] listOfPosts = getAllPostsByUserAsDto(validLoginApi);
        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(token, listOfPosts[i].getId());
            logger.info(String.format("Post with id %s and title '%s' was deleted"
                    , listOfPosts[i].getId(), listOfPosts[i].getTitle()));
        }
        getAllPostsByUserRequest(validLoginApi, HttpStatus.SC_OK);

    }

    private void deletePostById(String token, String id) {
        HashMap<String, String> bodyRequest = new HashMap<>();
        bodyRequest.put("token", token);

        String actualResponse =
                given()
                        .spec(requestSpecification)
                        .body(bodyRequest)
                        .when()
                        .delete(EndPoints.DELETE_POST, id) //URL with id
                        .then()
                        .spec(responseSpecification)
                        .extract().response().body().asString();


    }

    public void createPost(String token, Map<String, String> postData, Integer indexOfPost) {
        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("title", postData.get("title") + indexOfPost);
        requestBody.put("body", postData.get("body"));
        requestBody.put("select1", postData.get("select"));
        requestBody.put("uniquePost", "no");
        requestBody.put("token", token);

        given()
                .spec(requestSpecification)
                .body(requestBody)
                .when()
                .post(EndPoints.CREATE_POST)
                .then()
                .spec(responseSpecification);
    }

    public void deleteAllPostsTillPresent() {
        String token = getToken();
        deleteAllPostsTillPresent(VALID_LOGIN_API, token);
    }
}
