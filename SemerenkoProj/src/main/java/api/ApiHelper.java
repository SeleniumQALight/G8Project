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
import org.apache.hc.core5.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.HashMap;

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


    public ValidatableResponse getAllPostsByUserRequest(String userName, int statusCode) {
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPoints.POST_BY_USER, userName)
                .then()
                .spec(responseSpecification.statusCode(statusCode));
    }

    public ValidatableResponse getAllPostsByUserRequest(String userName) {
        return getAllPostsByUserRequest(userName, HttpStatus.SC_OK);
    }

    public String getToken() {
        return getToken(TestData.PERSONAL_LOGIN_UI, TestData.VALID_PASSWORD_API);
    }

    public String getToken(String username, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", username);
        requestBody.put("password", password);

        ResponseBody responseBody =
                given()
                        .spec(requestSpecification)
                        .body(requestBody.toMap())
                        .when()
                        .post(EndPoints.LOGIN) // URL
                        .then()
                        .spec(responseSpecification)
                        .extract().response().getBody();

        return responseBody.asString().replace("\"", "");
    }
    public PostsDto[] getAllPostByUserAsDto (String username){
        return getAllPostsByUserRequest(username).extract().response().getBody().as(PostsDto[].class);
    }

    public void deleteAllPostsTillPresent(String login, String token) {
        PostsDto[] listOfPosts = getAllPostByUserAsDto(login);

        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(token, listOfPosts[i].get_id());
            logger.info(String.format("Post with id %s and title '%s' was deleted"
            ,listOfPosts[i].get_id(), listOfPosts[i].getTitle()));
        }
        getAllPostsByUserRequest(login, HttpStatus.SC_OK);
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
