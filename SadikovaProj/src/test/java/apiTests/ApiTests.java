package apiTests;

import api.ApiHelper;
import api.EndPoints;
import api.dto.responseDto.AuthorDTO;
import api.dto.responseDto.PostDto;
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
        PostDto[] actualResponseAsDto =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(EndPoints.POSTS_BY_USER, USER_NAME) // URL
                        .then()
                        .log().all()
                        .statusCode(HttpStatus.SC_OK)
                        .assertThat()
                        .body("[0].title", equalTo("test2"))
                        .body("author.username", everyItem(equalTo(USER_NAME)))
                        .extract().body().as(PostDto[].class);

        logger.info(actualResponseAsDto[0].toString());
        logger.info("Size = " + actualResponseAsDto.length);
        logger.info("Title [0] = " + actualResponseAsDto[0].getTitle());
        logger.info("UserName [0] = " + actualResponseAsDto[0].getAuthor().getUsername());

        for (int i = 0; i < actualResponseAsDto.length; i++) {
            Assert.assertEquals("UserName is not matched is post: " + i,
                    USER_NAME,
                    actualResponseAsDto[i].getAuthor().getUsername());
        }

        //Except result
        PostDto[] expectedDto = {
//                new PostDto("test2", "test body2", "All Users", "no", new AuthorDTO(USER_NAME), false),
//                new PostDto("test", "test body", "All Users", "no", new AuthorDTO(USER_NAME), false)
                PostDto.builder()
                        .title("test2")
                        .body("test body2")
                        .select("All Users")
                        .uniquePost("no")
                        .author(AuthorDTO.builder().username(USER_NAME).build())
                        .isVisitorOwner(false)
                        .build(),
//                PostDto.builder()
//                        .title("test")
//                        .body("test body")
//                        .select("All Users")
//                        .uniquePost("no")
//                        .author(AuthorDTO.builder().username(USER_NAME).build())
//                        .isVisitorOwner(false)
//                        .build()

        };

        Assert.assertEquals("Number of post ", expectedDto.length, actualResponseAsDto.length);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions
                .assertThat(actualResponseAsDto)
                .usingRecursiveComparison()
                .ignoringFields("id", "createdDate", "author.avatar")
                .isEqualTo(expectedDto);
        softAssertions.assertAll();
    }

    @Test
    public void getAllPostByUserIsNegative() {
        final String notValidUser = "Not_Valid_User";
        String actualResponse = apiHelper.getAllPostByUserRequest(notValidUser, 400)
                .extract().response().body().asString();

        Assert.assertEquals("Message in response ", "\"Sorry, invalid user requested. Wrong username - " + notValidUser + " or there is no posts. Exception is undefined\"", actualResponse);

    }

    @Test
    public void getAllPostByUserPath(){
        Response actualResponse = apiHelper.getAllPostByUserRequest(USER_NAME).extract().response();
        SoftAssertions softAssertions = new SoftAssertions();
        List<String> actualListOfTitles = actualResponse.jsonPath().getList("title", String.class);
        for (int i = 0; i < actualListOfTitles.size(); i++) {
            softAssertions.assertThat(actualListOfTitles.get(i)).as("Item number " + i).contains("test");
            
        }
        List<Map> authorList = actualResponse.jsonPath().getList("author", Map.class);
        for (int i = 0; i < authorList.size(); i++) {



        }

        softAssertions.assertAll();
    }

    @Test
    public void getAllPostByUserSchema(){
        apiHelper.getAllPostByUserRequest(USER_NAME)
                .assertThat().body(matchesJsonSchemaInClasspath("response.json"));
    }


}
