import baseTest.BaseTest;
import libs.TestData;
import libs.Urls;
import org.junit.Ignore;
import org.junit.Test;

public class CreatePostTest extends BaseTest {

    @Test
    @Ignore
    public void createPost() {
        pageProvider.mainPage()
                .loginToProfile(TestData.VALID_LOGIN_UI, TestData.VALID_PASSWORD);
        pageProvider.homePage().checkIsRedirectToHomePage(Urls.HOME_PAGE_URL);
        pageProvider.headerElement().clickCreatePostButton();
        pageProvider.createPostPage().checkIsRedirectToCreatePostPage();
        pageProvider.createPostPage().enterTextInTitleInInputTitle("test001");
        pageProvider.createPostPage().enterTextInBodyContentField("test tets");
        pageProvider.createPostPage().selectValueInDropdown("One Person");
        pageProvider.createPostPage().clickSaveNewPostButton();
        pageProvider.postPage().checkIsSuccessMessageDisplayes();
        pageProvider.postPage().checkTextPresent("New post successfully created.");




    }
}
