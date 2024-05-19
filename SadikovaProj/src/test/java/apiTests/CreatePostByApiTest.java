package apiTests;

import api.ApiHelper;
import api.EndPoints;
import api.dto.requestDto.CreatePostDTO;
import api.dto.responseDto.AuthorDTO;
import api.dto.responseDto.PostDto;
import data.TestData;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CreatePostByApiTest {
    ApiHelper apiHelper = new ApiHelper();
    String token;
    //Logger logger = new Logger();

    @Before
    public void getTokenAndDeletePosts() {
        token = apiHelper.getToken();
        apiHelper.deleteAllPostsTillPresent(TestData.LOGIN_API, token);
    }


    @Before
    public void deletePost(){
        token = apiHelper.getToken();
        //System.out.println(token);

    }

    @Test
    public void createPostByApi() {
        int numberOfPosts = apiHelper.getAllPostByUserRequest().extract().response().as(PostDto[].class).length;
        // System.out.println(numberOfPosts);
        CreatePostDTO createPostDTOBody =
                CreatePostDTO.builder()
                        .title("post from api")
                        .body("post")
                        .select1("One Person")
                        .uniquePost("yes")
                        .token(token)
                        .build();

        String actualResponse =
                given()

                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(createPostDTOBody)
                        .when()
                        .post(EndPoints.CREATE_POST)
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .log().all()
                        .extract().response().body().asString();

        Assert.assertEquals("message in response ", "\"Congrats.\"", actualResponse);
        int numberAfterCreatedPost = apiHelper.getAllPostByUserRequest().extract().response().as(PostDto[].class).length;
        Assert.assertEquals("Number of posts ", numberOfPosts + 1, numberAfterCreatedPost);

        //ToDo post content
        PostDto expectedPostDto =
                PostDto.builder()
                        .title(createPostDTOBody.getTitle())
                        .body(createPostDTOBody.getBody())
                        .select(createPostDTOBody.getSelect1())
                        .uniquePost(createPostDTOBody.getUniquePost())
                        .isVisitorOwner(false)
                        .author(AuthorDTO.builder()
                                .username(TestData.LOGIN_API)
                                .build())

                        .build();


        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(apiHelper.getAllPostsByUser(TestData.LOGIN_API)[0])
                .usingRecursiveComparison()
                .ignoringFields("id", "createdDate", "author.avatar").isEqualTo(expectedPostDto);
        softAssertions.assertAll();


    }
}
