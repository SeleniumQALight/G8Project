package apiTests;

import api.EndPoints;
import api.dto.responseDto.AuthorDto;
import api.dto.responseDto.PostsDto;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;

public class ApiTests {
    final String USER_NAME = "autoapi";
    Logger logger = Logger.getLogger(getClass());

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
                new PostsDto("test2", "test body2", "All Users", "no", new AuthorDto(USER_NAME), false),
                new PostsDto("test", "test body", "All Users", "no", new AuthorDto(USER_NAME), false)
        };

        Assert.assertEquals("Number of posts ", expectedDto.length, actualResponseAsDto.length);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualResponseAsDto)
                .usingRecursiveComparison()
                .ignoringFields("_id", "createdDate", "author.avatar")
                .isEqualTo(expectedDto);
        softAssertions.assertAll();
    }
}
