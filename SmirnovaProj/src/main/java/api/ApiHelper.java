package api;

import api.dto.response.PostsDto;
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
                .get(Endpoints.POST_BY_USER, userName)
                .then()
                .spec(responseSpecification.statusCode(statusCode))
                ;
    }

    public ValidatableResponse getAllPostsByUserRequest(String userName) {
        return getAllPostsByUserRequest(userName, HttpStatus.SC_OK);
    }

    public PostsDto[] getAllPostsByUserAsDto(String userName) {
        return getAllPostsByUserRequest(userName)
                .extract().response().getBody().as(PostsDto[].class);
    }

    public String getToken() {
        return getToken(TestData.VALID_LOGIN_API, TestData.VALID_PASSWORD_API);
    }

    public String getToken(String username, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", username);
        requestBody.put("password", password);

        ResponseBody responseBody = given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(Endpoints.LOGIN)// URL
                .then()
                .spec(responseSpecification)
                .extract().response().getBody();

        return responseBody.asString().replace("\"", "");
    }

    public void deleteAllPostsTillPresent(String validLoginApi, String token) {
        PostsDto[] allPostsByUserAsDto = getAllPostsByUserAsDto(validLoginApi);
        for (int i = 0; i < allPostsByUserAsDto.length; i++) {
            deletePostById(allPostsByUserAsDto[i].getId(), token);
            logger.info(String.format("Post with id %s and title '%s' was deleted"
                    , allPostsByUserAsDto[i].getId()
                    , allPostsByUserAsDto[i].getTitle())
            );
        }
        getAllPostsByUserRequest(validLoginApi, HttpStatus.SC_OK);
    }

    private void deletePostById(String id, String token) {
        HashMap<String, String> bodyRequest = new HashMap<>();
        bodyRequest.put("token", token);

        String actualResponse =
                given()
                        .spec(requestSpecification)
                        .body(bodyRequest)
                        .when()
                        .delete(Endpoints.BASE_URL + "/api/post/" + id)
                        .then()
                        .spec(responseSpecification)
                        .extract().response().body().asString();
    }
}
