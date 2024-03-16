package apiTests;

import api.ApiHelper;
import api.Endpoints;
import api.dto.request.CreatePostDto;
import api.dto.response.AuthorDto;
import api.dto.response.PostsDto;
import data.TestData;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CreatePostByApiTest {
    String token;
    ApiHelper apiHelper = new ApiHelper();

    @Before
    public void deletePosts() {
        token = apiHelper.getToken();
        apiHelper.deleteAllPostsTillPresent(TestData.VALID_LOGIN_API, token);
    }

    @Test
    public void createPostByApi() {
       // int numberOfPosts = apiHelper.getAllPostsByUserAsDto(TestData.VALID_LOGIN_API).length;

        CreatePostDto createPostDto =
                CreatePostDto.builder()
                        .title("Post from API")
                        .body("Post body")
                        .select1("One Person")
                        .uniquePost("yes")
                        .token(token)
                        .build();

        String actualResponse =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(createPostDto)
                        .when()
                        .post(Endpoints.CREATE_POST)
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .log().all()
                        .extract().response().body().asString();

        Assert.assertEquals("Message in response", "\"Congrats.\""
                , actualResponse);

        PostsDto[] actualPostsByUser =
                apiHelper.getAllPostsByUserAsDto(TestData.VALID_LOGIN_API);

        Assert.assertEquals("Number of posts by user", 1, actualPostsByUser.length);

        PostsDto expectedPostDto = PostsDto.builder()
                .title(createPostDto.getTitle())
                .body(createPostDto.getBody())
                .select(createPostDto.getSelect1())
                .uniquePost(createPostDto.getUniquePost())
                .isVisitorOwner(false)
                .author(AuthorDto.builder().username(TestData.VALID_LOGIN_API).build())
                .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualPostsByUser[0])
                .usingRecursiveComparison()
                .ignoringFields("id", "createdDate", "author.avatar")
                .isEqualTo(expectedPostDto);
        softAssertions.assertAll();
    }
}
