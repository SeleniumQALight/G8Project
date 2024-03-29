package createPost;

import baseTest.BaseTest;
import data.TestData;
import libs.Util;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

public class CreatePostTest extends BaseTest {

    final String POST_TITLE = "test001" + Util.getDateAndTimeFormattedOnlyNumbers(),
            TEXT_BODY = "TEST TEST",
            ONE_PERSON = "One Person";

    @Test
    @Ignore
    public void createPost() {
        String postTitle = POST_TITLE;
        pageProvider.mainPage()
                .loginToProfile(TestData.VALID_LOGIN_UI, TestData.VALID_PASSWORD);
        pageProvider.homePage().checkIsRedirectToHomePage();
        pageProvider.headerElement().clickCreatePostButton();
        pageProvider.createPostPage().checkIsRedirectToCreatePostPage();
        pageProvider.createPostPage().enterTextInTitleInInputTitle(postTitle);
        pageProvider.createPostPage().enterTextInBodyContentField(TEXT_BODY);
        pageProvider.createPostPage().selectValueInDropdown(ONE_PERSON);
        pageProvider.createPostPage().checkSetStateCheckbox("check");
        pageProvider.createPostPage().clickSaveNewPostButton();
        pageProvider.postPage().checkIsSuccessMessageDisplayes();
        pageProvider.postPage().checkTextPresent("New post successfully created.")
                .checkTitleTextIsEquals(postTitle)
                .checkBodyTextIsEquals(TEXT_BODY)
                .checkThisIsPostUniqueWebElementIsPresent("yes")
                .checkNoteBlockWebElementIsPresent(ONE_PERSON);


        pageProvider.postPage().getHeaderElement()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1);
    }

    @After
    public void deletePost() {
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded()
                .getheaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE)

        ;
    }

}

