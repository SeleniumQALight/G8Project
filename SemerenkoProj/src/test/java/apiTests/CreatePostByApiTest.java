package apiTests;

import api.ApiHelper;
import api.EndPoints;
import api.dto.requestDto.CreatePostDto;
import api.dto.responseDto.AuthorDto;
import api.dto.responseDto.PostsDto;
import data.TestData;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.json.HTTP;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static data.TestData.*;
import static io.restassured.RestAssured.given;

public class CreatePostByApiTest {
    String token;
    ApiHelper apiHelper = new ApiHelper();
    @Before
    public void deletePosts(){
        token = apiHelper.getToken();
        apiHelper.deleteAllPostsTillPresent(PERSONAL_LOGIN_UI, token);
        //System.out.println("---"+ token +"---");
    }

    @Test
    public void createPostByApi(){
        //int numberOfPosts = apiHelper.getAllPostByUserAsDto(TestData.PERSONAL_LOGIN_UI).length;

        CreatePostDto createPostDto =
        CreatePostDto.builder()
                .title("Post from API 1")
                .body("Post body")
                .select1("One person")
                .uniquePost("yes")
                .token(token)
                .build();

        String actualResponse =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(createPostDto)
                        .when()
                        .post(EndPoints.CREATE_POST)
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .log().all()
                        .extract().response().body().asString();

        Assert.assertEquals("Message in response ","\"Congrats.\"", actualResponse);

        PostsDto[] actualPostByUser = apiHelper.getAllPostByUserAsDto(PERSONAL_LOGIN_UI);
        Assert.assertEquals("Number of posts ", 1, actualPostByUser.length);

        PostsDto expectedPostDto =
                PostsDto.builder()
                        .title(createPostDto.getTitle())
                        .body(createPostDto.getBody())
                        .select1(createPostDto.getSelect1())
                        .uniquePost(createPostDto.getUniquePost())
                        .isVisitorOwner(false)
                        .author(AuthorDto.builder().username(PERSONAL_LOGIN_UI).build())
                        .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualPostByUser[0])
                        .usingRecursiveComparison()
                                .ignoringFields("_id","createdDate", "author.avatar")
                                        .isEqualTo(expectedPostDto);

        softAssertions.assertAll();
    }
}
