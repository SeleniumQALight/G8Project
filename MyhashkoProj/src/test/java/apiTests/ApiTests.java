package apiTests;

import api.ApiHelper;
import api.EndPoints;
import api.dto.responseDto.AuthorDTO;
import api.dto.responseDto.PostsDto;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;

public class ApiTests {
    final String USER_NAME = "autoapi";
    Logger logger = Logger.getLogger(getClass());

    ApiHelper apiHelper = new ApiHelper();

    @Test
    public void getAllPostsByUser() {
        PostsDto[] actualResultAsDto =
                given()
                        .contentType(ContentType.JSON)
                        .filter(new AllureRestAssured())
                        .log().all()
                        .when()
                        .get(EndPoints.POSTS_BY_USER, USER_NAME)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .assertThat()
                        .body("[0].title", equalTo("test2"))
                        .body("author.username", everyItem(equalTo(USER_NAME)))
                        .extract().body().as(PostsDto[].class);

        logger.info(actualResultAsDto[0].toString());
        logger.info("Size = " + actualResultAsDto.length);
        logger.info("Title = " + actualResultAsDto[0].getTitle());
        logger.info("UserName = " + actualResultAsDto[0].getAuthor().getUsername());

        for (int i = 0; i < actualResultAsDto.length; i++) {
            Assert.assertEquals("Username is not matched is post #" + i
                    , USER_NAME
                    , actualResultAsDto[i].getAuthor().getUsername());
        }

        // expected result
        PostsDto[] expectedDto = {
//                new PostsDto("test2", "test body2", "All Users", "no", new AuthorDTO(USER_NAME), false),
//                new PostsDto("test", "test body", "All Users", "no", new AuthorDTO(USER_NAME), false)
                PostsDto.builder()
                        .title("test2")
                        .body("test body2")
                        .select("All Users")
                        .uniquePost("no")
                        .author(AuthorDTO.builder().username(USER_NAME).build())
                        .isVisitorOwner(false)
                        .build(),
                PostsDto.builder()
                        .title("test")
                        .body("test body")
                        .select("All Users")
                        .uniquePost("no")
                        .author(AuthorDTO.builder().username(USER_NAME).build())
                        .isVisitorOwner(false)
                        .build()
        };

        Assert.assertEquals("Number of posts", expectedDto.length, actualResultAsDto.length);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions
                .assertThat(actualResultAsDto)
                        .usingRecursiveComparison()
                .ignoringFields("id", "createdDate", "author.avatar")
                                .isEqualTo(expectedDto);

        softAssertions.assertAll();
    }

    @Test
    public void getPostByUserNegative() {
        final String NOT_VALID_USER = "notValidUser";
        String actualResponse = apiHelper.getAllPostsByUserRequest(NOT_VALID_USER, 400)
                .extract().body().asString();

        Assert.assertEquals("Message in response "
                , "\"Sorry, invalid user requested. Wrong username - "+NOT_VALID_USER+" or there is no posts. Exception is undefined\""
                , actualResponse);
    }

    @Test
    public void getAllPostsByUserPath() {
        Response actualResponse = apiHelper.getAllPostsByUserRequest(USER_NAME).extract().response();
        SoftAssertions softAssertions = new SoftAssertions();

        List<String> actualListTitles = actualResponse.jsonPath().getList("title", String.class);
        for (int i = 0; i < actualListTitles.size(); i++) {
            softAssertions.assertThat(actualListTitles.get(i)).as("Item number " + i).contains("test");
        }

        List<Map> actuaAuthorsList = actualResponse.jsonPath().getList("author", Map.class);

        for (int i = 0; i < actuaAuthorsList.size(); i++) {
            softAssertions.assertThat(actuaAuthorsList.get(i).get("username")).as("Item number " + i).isEqualTo(USER_NAME);
        }

        softAssertions.assertAll();
    }

    @Test
    public void getAllPostByUserScheme() {
        apiHelper.getAllPostsByUserRequest(USER_NAME)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("response.json"));
    }
}
