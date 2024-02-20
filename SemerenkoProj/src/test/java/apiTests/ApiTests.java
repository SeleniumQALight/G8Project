package apiTests;

import api.ApiHelper;
import api.EndPoints;
import api.dto.responseDto.AuthorDto;
import api.dto.responseDto.PostsDto;
import com.github.dockerjava.transport.DockerHttpClient;
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
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
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
                        .log().all()
                        .when()
                        .get(EndPoints.POST_BY_USER, USER_NAME)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .assertThat()
                        .body("[0].title", equalTo("test2"))
                        .body("author.username", everyItem(equalTo(USER_NAME)))
                        .extract().body().as(PostsDto[].class);
        logger.info(actualResponseAsDto[0].toString());
        logger.info("Size: " + actualResponseAsDto.length);
        logger.info("Title[0] " + actualResponseAsDto[0].getTitle());
        logger.info("UserName[0] " + actualResponseAsDto[0].getAuthor().getUsername());

        for (int i = 0; i < actualResponseAsDto.length; i++) {
            Assert.assertEquals(""
                    , USER_NAME
                    , actualResponseAsDto[i].getAuthor().getUsername());
        }

        //expectedResult

        PostsDto[] expectedDto = {
                //без білдеру
//                new PostsDto("test2", "test body2", "All Users", "no", new AuthorDto(USER_NAME), false),
//                new PostsDto("test", "test body", "All Users", "no", new AuthorDto(USER_NAME), false)
                PostsDto.builder()
                        .title("test2").body("test body2").select1("All Users")
                        .uniquePost("no").author(AuthorDto.builder().username(USER_NAME).build())
                        .isVisitorOwner(false)
                        .build(),
                PostsDto.builder()
                        .title("test").body("test body").select1("All Users")
                        .uniquePost("no").author(AuthorDto.builder().username(USER_NAME).build())
                        .isVisitorOwner(false)
                        .build()
        };

        Assert.assertEquals("Number of posts ", expectedDto.length, actualResponseAsDto.length);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualResponseAsDto)
                .usingRecursiveComparison()
                .ignoringFields("_id", "createdDate", "author.avatar")
                .isEqualTo(expectedDto);
        softAssertions.assertAll();
    }

    @Test
    public void getAllPostsByUserNegative() {
        final String INVALID_USER = "invalid user";
        String actualResponse =
                apiHelper.getAllPostsByUserRequest(INVALID_USER, 400)
                        .extract().response().body().asString();

        Assert.assertEquals("Message in response ", "\"Sorry, invalid user requested. Wrong username - " + INVALID_USER + " or there is no posts. Exception is undefined\"", actualResponse);
    }

    @Test
    public void getAllPostsByUserPath() {
        Response actualResponse = apiHelper.getAllPostsByUserRequest(USER_NAME).extract().response();
        SoftAssertions softAssertions = new SoftAssertions();
        List<String> actualListOfTitles = actualResponse.jsonPath().getList("title", String.class);
        for (int i = 0; i < actualListOfTitles.size(); i++) {
            softAssertions.assertThat(actualListOfTitles.get(i)).as("Item number " + i).contains("test");
        }
        List<Map> actualAuthorList = actualResponse.jsonPath().getList("author", Map.class);
        for (int i = 0; i < actualAuthorList.size(); i++) {
            softAssertions.assertThat(actualAuthorList.get(i).get("username")).as("Item number " + i).isEqualTo(USER_NAME);
        }

        softAssertions.assertAll();
    }
    @Test
    public void getAllPostByUserSchema(){
        apiHelper.getAllPostsByUserRequest(USER_NAME)
                .assertThat().body(matchesJsonSchemaInClasspath("response.json"));
    }
}
