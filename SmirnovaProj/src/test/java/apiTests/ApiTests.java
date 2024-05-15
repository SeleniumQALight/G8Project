package apiTests;

import api.ApiHelper;
import api.Endpoints;
import api.dto.response.AuthorDto;
import api.dto.response.PostsDto;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;

public class ApiTests {
    final String USER_NAME = "autoapi";
    Logger logger = Logger.getLogger(getClass());
    ApiHelper apiHelper = new ApiHelper();

    @Test
    public void getAllPostsByUser() {
        PostsDto[] actualResponseAsDto =
                given()
                        .contentType(ContentType.JSON)
                        .filter(new AllureRestAssured())
                        .when()
                        .get(Endpoints.POST_BY_USER, USER_NAME)
                        .then()
                        .log().all()
                        .statusCode(HttpStatus.SC_OK)
                        .assertThat().body("[0].title", equalTo("test2"))
                        .body("author.username", everyItem(equalTo(USER_NAME)))
                        .extract().body().as(PostsDto[].class);
        logger.info(actualResponseAsDto[0].toString());
        logger.info("Size = " + actualResponseAsDto.length);
        logger.info("Title [0] = " + actualResponseAsDto[0].getTitle());
        logger.info("Username [0] = " + actualResponseAsDto[0].getAuthor().getUsername());

        for (int i = 0; i < actualResponseAsDto.length; i++) {
            Assert.assertEquals("Username is not as expected",
                    USER_NAME,
                    actualResponseAsDto[i].getAuthor().getUsername());
        }


        //Expected result:
        PostsDto[] expectedDto = {
//                new PostsDto("test2", "test body2", "All Users", "no", new AuthorDto(USER_NAME), false),
//                new PostsDto("test", "test body", "All Users", "no", new AuthorDto(USER_NAME), false),
                PostsDto.builder()
                        .title("test2")
                        .body("test body2")
                        .select("All Users")
                        .uniquePost("no")
                        .author(AuthorDto.builder().username(USER_NAME).build())
                        .isVisitorOwner(false)
                        .build(),
                PostsDto.builder()
                        .title("test")
                        .body("test body")
                        .select("All Users")
                        .uniquePost("no")
                        .author(AuthorDto.builder().username(USER_NAME).build())
                        .isVisitorOwner(false)
                        .build()
        };

        Assert.assertEquals("Number of posts ", expectedDto.length, actualResponseAsDto.length);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualResponseAsDto)
                .usingRecursiveComparison()
                .ignoringFields("id", "createdDate", "author.avatar")
                .isEqualTo(expectedDto);

        softAssertions.assertAll();


    }

    @Test
    public void getAllPostsByUserNegative() {
        final String INVALID_USER_NAME = "invalidUsername";
        String actualResponse =
                apiHelper.getAllPostsByUserRequest(INVALID_USER_NAME, 400)
                        .extract().response().body().asString();
        Assert.assertEquals("Message in response",
                "\"Sorry, invalid user requested. Wrong username - invalidUsername or there is no posts. Exception is undefined\"",
                actualResponse);
    }

    @Test
    public void getAllPostsByUserPath() {
        Response actualResponse = apiHelper.getAllPostsByUserRequest(USER_NAME).extract().response();
        SoftAssertions softAssertions = new SoftAssertions();

        List<String> actualTitles = actualResponse.jsonPath().getList("title", String.class);
        for (int i = 0; i < actualTitles.size(); i++) {
            softAssertions.assertThat(actualTitles.get(i)).as("Item number " + i)
                    .contains("test");
        }

        List<Map> actualAuthorsList = actualResponse.jsonPath().getList("author", Map.class);
        for (int i = 0; i < actualAuthorsList.size(); i++) {
            softAssertions.assertThat(actualAuthorsList.get(i).get("username")).as("Item number " + i)
                    .isEqualTo(USER_NAME);
        }

        softAssertions.assertAll();
    }

    @Test
    public void getAllPostsByUserSchema() {
        apiHelper.getAllPostsByUserRequest(USER_NAME)
                .assertThat().body(matchesJsonSchemaInClasspath("response.json"));
    }
}
