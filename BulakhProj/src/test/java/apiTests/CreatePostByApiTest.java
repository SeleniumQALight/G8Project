package apiTests;

import api.ApiHelper;
import api.EndPoints;
import api.dto.requestDto.CreatePostDTO;
import api.dto.responseDto.AuthorDTO;
import api.dto.responseDto.PostsDto;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static data.TestData.VALID_LOGIN_API;
import static io.restassured.RestAssured.given;

public class CreatePostByApiTest {
    String token;
    ApiHelper apiHelper = new ApiHelper();

    @Before
    public void deletePosts(){
        token = apiHelper.getToken();
        apiHelper.deleteAllPostsTillPresent(VALID_LOGIN_API.toLowerCase(Locale.ROOT), token);
//        System.out.println("--" + token + "--");


    }
    @Test
    public void createPostByApi(){
//        int numberOfPosts = apiHelper.getAllPostsByUserAsDto(VALID_LOGIN_API.toLowerCase(Locale.ROOT)).length;

        CreatePostDTO createPostDTO =
        CreatePostDTO.builder()
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
                        .body(createPostDTO)
                        .when()
                        .post(EndPoints.CREATE_POST)
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .log().all()
                        .extract().response().body().asString();
                ;

        Assert.assertEquals("Message in response ", "\"Congrats.\"", actualResponse);

        PostsDto[] actualPostsByUser = apiHelper.getAllPostsByUserAsDto(VALID_LOGIN_API.toLowerCase(Locale.ROOT));
        Assert.assertEquals("Number of posts ", 1, actualPostsByUser.length);

        PostsDto expectedPostsDto =
                PostsDto.builder()
                        .title(createPostDTO.getTitle())
                        .body(createPostDTO.getBody())
                        .select(createPostDTO.getSelect1())
                        .uniquePost(createPostDTO.getUniquePost())
                        .isVisitorOwner(false)
                        .author(AuthorDTO.builder().username(VALID_LOGIN_API.toLowerCase()).build())
                        .build();

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actualPostsByUser[0])
                .usingRecursiveComparison()
                        .ignoringFields("id", "createdDate", "author.avatar")
                                .isEqualTo(expectedPostsDto);

        softAssertions.assertAll();






    }
}
