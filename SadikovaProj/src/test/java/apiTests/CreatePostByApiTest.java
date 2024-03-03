package apiTests;

import api.ApiHelper;
import api.EndPoints;
import api.dto.requestDTO.CreatePostDTO;
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

    @Before
    public void deletePost() {
        token = apiHelper.getToken();
        System.out.println("--" + token + "--");
        apiHelper.deleteAllPostTillPresent(TestData.LOGIN_API, token);

    }

    @Test
    public void createPostByApi() {
            //int numberOfPosts = apiHelper.getAllPostsByUserAsDTO(TestData.LOGIN_API).length;
            CreatePostDTO createPostDto =
                    CreatePostDTO.builder()
                            .title("Post from API12 ")
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
                            .extract().response().body().asString()
                    ;

            Assert.assertEquals("Message in response ", "\"Congrats.\"", actualResponse);

            PostDto[] actualPostsByUser = apiHelper.getAllPostsByUserAsDTO(TestData.LOGIN_API);
            Assert.assertEquals("Number of posts " , 1, actualPostsByUser.length);

            PostDto expectedPostDto =
                    PostDto.builder()
                            .title(createPostDto.getTitle().trim())
                            .body(createPostDto.getBody())
                            .select(createPostDto.getSelect1())
                            .uniquePost(createPostDto.getUniquePost())
                            .isVisitorOwner(false)
                            .author(AuthorDTO.builder().username(TestData.LOGIN_API).build())
                            .build();

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(actualPostsByUser[0])
                    .usingRecursiveComparison()
                    .ignoringFields("id", "createdDate", "author.avatar")
                    .isEqualTo(expectedPostDto);
            softAssertions.assertAll();



    }
}
