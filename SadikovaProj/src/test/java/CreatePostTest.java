import baseTest.BaseTest;
import libs.TestData;
import libs.Util;
import org.junit.After;
import libs.Urls;
import org.junit.Ignore;
import org.junit.Test;

public class CreatePostTest extends BaseTest {

    final String POST_TITLE = "test001" + Util.getDateAndTimeFormattedOnlyNumbers();

    @Test
    @Ignore
    public void createPost() {
        pageProvider.mainPage()
                .loginToProfile(TestData.VALID_LOGIN_UI, TestData.VALID_PASSWORD);
        pageProvider.homePage().checkIsRedirectToHomePage(Urls.HOME_PAGE_URL);
        pageProvider.headerElement().clickCreatePostButton();
        pageProvider.createPostPage().checkIsRedirectToCreatePostPage();
        pageProvider.createPostPage().enterTextInTitleInInputTitle(POST_TITLE);
        pageProvider.createPostPage().enterTextInBodyContentField("test tets");
        pageProvider.createPostPage().selectValueInDropdown("One Person");
        pageProvider.createPostPage().clickSaveNewPostButton();
        pageProvider.postPage().checkIsSuccessMessageDisplayes();
        pageProvider.postPage().checkTextPresent("New post successfully created.");


        pageProvider.postPage().getHeaderElement()
                .clickOnMyProfilePageButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE,1);
    }

  @After
    public void deletePost() {
        pageProvider.homePage().openHomePageAndLoginIfNeeded();
  }

    }

