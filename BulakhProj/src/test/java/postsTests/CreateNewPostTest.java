package postsTests;

import baseTast.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;


public class CreateNewPostTest  extends BaseTest {
    final String POST_TITLE = "TC_001_Bulakh" + Util.getDateAndTimeFormatted();
    final String POST_BODY = "body text";
    final String DROPDOWN_VALUE = "One Person";
    @Test

    public void TC_001_createNewPost() {
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleInToInputTitle(POST_TITLE)
                .enterTextInToInputBody("body text")
            //    .selectTextInDropDown("Приватне повідомлення")
                .selectValueInDropDown("One Person")
                .setCheckboxState("check")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsCreatedPostHasTitle(POST_TITLE)
                .checkIsCreatedPostHasBody(POST_BODY)
                .checkIsCreatedPostHasValueInDropDown(DROPDOWN_VALUE)
                .checkCheckboxStatus("no")



        ;


        pageProvider.getPostPage().getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE)
                .setCheckboxState("uncheck")

        ;

        pageProvider.homePage().openHomePageAndLoginIfNeeded();


    }

    @After
    public void deletePost(){
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded()
        ;
    }
}
