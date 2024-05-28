package api;

import api.dto.responseDto.PostDto;
import data.TestData;
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

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public ValidatableResponse getAllPostByUserRequest(String userName, int statusCode) {
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPoints.POSTS_BY_USER, userName)
                .then()
                .spec(responseSpecification.statusCode(statusCode));


    }

    public ValidatableResponse getAllPostByUserRequest(String userName) {
        return getAllPostByUserRequest(userName, HttpStatus.SC_OK);
    }

    /**
     * GET ALL posts for default user api
     *
     * @return
     */
    public ValidatableResponse getAllPostByUserRequest() {
        return getAllPostByUserRequest(TestData.LOGIN_API, HttpStatus.SC_OK);
    }

    public PostDto[] getAllPostsByUser(String userName) {
        return getAllPostByUserRequest(userName)
                .extract().response().getBody().as(PostDto[].class);
    }


    public String getToken() {
        return getToken(TestData.LOGIN_API, TestData.VALID_PASSWORD_API);
    }

    /**
     * GET TOKEN FOR DEFAULT USER
     *
     * @param userName
     * @param password
     * @return
     */
    public String getToken(String userName, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", userName);
        requestBody.put("password", password);
        return given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(EndPoints.LOGIN) //URL
                .then()//CHECK
                .spec(responseSpecification)
                .extract().response().getBody().asString().replace("\"", "");

    }

    public void deleteAllPostsTillPresent(String validLoginApi, String token) {
        PostDto[] listOfPosts = getAllPostsByUser(validLoginApi);
        for (int i = 0; i < listOfPosts.length; i++) {
            deleteByPostById (token, listOfPosts[i].getId());
            logger.info(String.format("Post with id %s and title '%s' was deleted", listOfPosts[i].getId(), listOfPosts[i].getTitle()));
        }
    }

    public void deleteByPostById(String token, String id) {
        HashMap<String, String> bodyRequest = new HashMap<>();
        bodyRequest.put("token", token);
        given()
                .spec(requestSpecification)
                .body(bodyRequest)
                .when()
                .delete(EndPoints.DELETE_POST, id)
                .then()
                .spec(responseSpecification);

    }
}
