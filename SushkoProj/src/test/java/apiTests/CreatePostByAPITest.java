package apiTests;

import api.ApiHelper;
import api.EndPoints;
import api.dto.responseDto.AuthorDto;
import api.dto.responseDto.CreatePostDto;
import api.dto.responseDto.PostsDto;
import io.restassured.http.ContentType;
import org.apache.hc.core5.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static data.TestData.VALID_LOGIN_API;
import static io.restassured.RestAssured.given;

public class CreatePostByAPITest {
    String token;
    ApiHelper apiHelper = new ApiHelper();

    @Before
    public void deletePost(){
        token = apiHelper.getToken();
        apiHelper.deleteAllPostsTillPresent(VALID_LOGIN_API, token);
//        System.out.println("----" + token + "----");
    }

    @Test
    public void createPostByAPI(){
        // int numberOfPosts = apiHelper.getAllPostsByUserAsDto(VALID_LOGIN_API).length;
        CreatePostDto createPostDto =
                CreatePostDto.builder()
                        .title("Post API")
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
                        .post(EndPoints.CREATE_POST)
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .log().all()
                        .extract().body().asString();

        Assert.assertEquals("Message in response ", "\"Congrats.\"", actualResponse);

        PostsDto[] actualPostsByUser = apiHelper.getAllPostsByUserAsDto(VALID_LOGIN_API);
        Assert.assertEquals("Number of posts ", 1, actualPostsByUser.length);

        PostsDto expectedPostsDto =
                PostsDto.builder()
                        .title(createPostDto.getTitle().trim())
                        .body(createPostDto.getBody())
                        .select(createPostDto.getSelect1())
                        .uniquePost(createPostDto.getUniquePost())
                        .isVisitorOwner(false)
                        .author(AuthorDto.builder().username(VALID_LOGIN_API).build())
                        .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualPostsByUser[0])
                .usingRecursiveComparison()
                .ignoringFields("id", "createdDate", "author.avatar")
                .isEqualTo(expectedPostsDto);
        softAssertions.assertAll();
    }
}
