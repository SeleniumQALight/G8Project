package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "TC_001_Sushko " + Util.getDateAndTimeFormatted();
    @Test
    public void TC_001_createNewPost(){
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCreds()
                .checkIsRedirectToHimePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleInToInputTitle(POST_TITLE)
                .enterTextIntoInputBody("body text")
                //.selectTextInDropDown("Приватне повідомлення")
                .setUniquePostCheckboxSelected()
                .selectValueInDropDown("One Person")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkTextThisPostUnique()
        ;

        pageProvider.getPostPage().getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE)
        ;
    }

    @After
    public void deletePost(){
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded()
        ;
    }
}

//        4. Зробити перевірку на сторінці postPage що ми бачимо текст тайтла, боді - такіж, з якими ми створювали пост
//        (порівняти текс з них з очикуваним, тим який передавали при створені).
//        А також перевірити що бачите текст Note: This post was written for One Person і Is this post unique? : yes/no
//        — це зробити параметризованим локатором (параметр буде yes чи no).