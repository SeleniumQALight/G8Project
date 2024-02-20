package apiTests;

import api.ApiHelper;
import api.EndPoints;
import api.dto.requestDto.CreatePostDto;
import api.dto.responseDto.AutorDto;
import api.dto.responseDto.PostsDto;
import data.TestData;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static data.TestData.VALID_LOGIN_API;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;

public class CreatePostByApiTest {
    String token;
    ApiHelper apiHelper = new ApiHelper();

    @Before
    public void deletePosts() {
        token = apiHelper.getToken();
        apiHelper.deleteAllPostsTillPresent(VALID_LOGIN_API, token);
//       System.out.println("--" + token + "---");

    }

    @Test
    public void createPostByApi() {

//        int numberOfPosts = apiHelper.getAllPostsByUserAsDTO(VALID_LOGIN_API).length;

        CreatePostDto createPostDto =
                CreatePostDto.builder()
                        .title("Post from Ol")
                        .body("Body")
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
                        .statusCode(SC_OK)
                        .log().all()
                        .extract().response().body().asString();

        Assert.assertEquals("Message in response", "\"Congrats.\"", actualResponse);

        PostsDto[] actualPostsByUser = apiHelper.getAllPostsByUserAsDTO(VALID_LOGIN_API);
        Assert.assertEquals("Number of posts ", 1, actualPostsByUser.length);

        PostsDto expectedPostDto =
                PostsDto.builder()
                        .title(createPostDto.getTitle())
                        .body(createPostDto.getBody())
                        .select(createPostDto.getSelect1())
                        .uniquePost(createPostDto.getUniquePost())
                        .isVisitorOwner(false)
                        .author(AutorDto.builder().username(VALID_LOGIN_API).build())
                        .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions
                .assertThat(actualPostsByUser[0])
                .usingRecursiveComparison()
                .ignoringFields("id", "createdDate", "author.avatar")
                .isEqualTo(expectedPostDto);

    }
}
