package apiTests;

import api.DemoQaApiHelper;
import api.dto.responseDto.*;
import data.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;

public class AddBookToProfileByApiTest {
    String token;
    String userId;
    DemoQaApiHelper demoQaApiHelper = new DemoQaApiHelper();

    @Before
    public void deleteBooks() {
        token = demoQaApiHelper.getToken().getString("token");
        userId = demoQaApiHelper.getToken().getString("userId");
        demoQaApiHelper.deleteAllUserBooksTillPresent(token, userId);
    }

    @Test
    public void addBookByApi() {
        BooksDto allBooksResponseAsDto = demoQaApiHelper.getAllBooksAsDto(token);

        String bookIsbn = allBooksResponseAsDto.getBooks()[0].getIsbn();

        demoQaApiHelper.addBookToUser(userId, bookIsbn, token);

        UsersBooksDto allUserBooksResponseAsDto = demoQaApiHelper.getAllUserBooksAsDto(token, userId);

        UsersBooksDto userBooksExpectedDto =
                UsersBooksDto.builder()
                        .userId(userId)
                        .username(TestData.VALID_LOGIN_DEMO_QA_API)
                        .books(new BookDetailsDto[]{BookDetailsDto.builder()
                                .isbn(bookIsbn)
                                .title(allBooksResponseAsDto.getBooks()[0].getTitle())
                                .subTitle(allBooksResponseAsDto.getBooks()[0].getSubTitle())
                                .author(allBooksResponseAsDto.getBooks()[0].getAuthor())
                                .publish_date(allBooksResponseAsDto.getBooks()[0].getPublish_date())
                                .publisher(allBooksResponseAsDto.getBooks()[0].getPublisher())
                                .pages(allBooksResponseAsDto.getBooks()[0].getPages())
                                .description(allBooksResponseAsDto.getBooks()[0].getDescription())
                                .website(allBooksResponseAsDto.getBooks()[0].getWebsite())
                                .build()})
                        .build();

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(allUserBooksResponseAsDto.getBooks().length).isEqualTo(1);

        softAssertions
                .assertThat(allUserBooksResponseAsDto)
                .usingRecursiveComparison()
                .ignoringFields("title", "subTitle", "author", "publish_date", "publisher", "pages", "description", "website")
                .isEqualTo(userBooksExpectedDto);

        softAssertions.assertAll();
    }
}
