package apiTests;

import api.EndPoints;
import api.dto.responseDto.AuthorDTO;
import api.dto.responseDto.PostsDto;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;

public class ApiTests {
    final String USER_NAME = "autoapi";
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllPostsByUser() {
        // Test get all posts by user api

        PostsDto[] actualResultAsDto =
                given()
                        .contentType(ContentType.JSON)
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
                new PostsDto("test2", "test body2", "All Users", "no", new AuthorDTO(USER_NAME), false),
                new PostsDto("test", "test body", "All Users", "no", new AuthorDTO(USER_NAME), false)
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
}
