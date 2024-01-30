package postsTests;

import baseTast.BaseTest;
import junitparams.JUnitParamsRunner;
import libs.ConfigProvider;
import libs.DB_seleniumUsers;
import libs.ExcelDriver;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;


@RunWith(JUnitParamsRunner.class)
public class UpdatePostTest extends BaseTest {

    final String LOGIN = "newqaauto";

    DB_seleniumUsers db_seleniumUsers = new DB_seleniumUsers();

    String TITLE;
    String NAME = "Bulakh";
    String DATA = Util.getDateAndTimeFormatted();
    String POST_BODY = "Post body";
    String POST_CHECKBOX;
    String CHECKBOX_STATUS;
    String DROPDOWN;
    String UPDATE_POST_TITLE;
    String POST_VALUE;

    @Before
    public void createPost() throws SQLException, ClassNotFoundException, IOException  {


        Map<String, String> dataForPost = ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "newPost");

        POST_VALUE = dataForPost.get("checkBoxValue");
        TITLE =  String.format(dataForPost.get("title"), NAME, DATA);
        POST_BODY = String.format(dataForPost.get("body"), NAME, DATA);
        CHECKBOX_STATUS = dataForPost.get("checkBoxStatus");
        DROPDOWN = dataForPost.get("option");

        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterLoginInToInputLogin(LOGIN);
        pageProvider.getLoginPage().enterPasswordInToInputPassword(db_seleniumUsers.getPassForLogin(LOGIN))
                .clickOnButtonSingIn();
        pageProvider.homePage().checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleInToInputTitle(TITLE)
                .enterTextInToInputBody(POST_BODY)
                .setCheckboxState(CHECKBOX_STATUS)
                .selectValueInDropDown(DROPDOWN)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsPostUniqueStateConfirmExpectedValue(POST_VALUE)
//                .checkIsThisPostUniqueTextPresent(POST_CHECKBOX)
                .checkIsCreatedPostHasTitle(TITLE)
                .checkIsCreatedPostHasBody(POST_BODY)
                .checkIsCreatedPostHasValueInDropDown("All users")
        ;

        pageProvider.getPostPage().getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(TITLE);
    }



       @Test
       public void updatePost() throws SQLException, ClassNotFoundException{
           UPDATE_POST_TITLE = db_seleniumUsers.getAliasForLogin(LOGIN);


         pageProvider.getPostPage().clickOnPostTitle(TITLE)
                .clickOnEditButton()
                .enterTitleInToInputTitle(UPDATE_POST_TITLE)
                .clickOnSaveUpdatesButton()
                .checkTextInSuccessMessage("Post successfully updated.");
        pageProvider.getPostPage().getHeader()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(UPDATE_POST_TITLE);




    }

    @After
    public void deletePost() {
        pageProvider.homePage().openHomePageAndLoginIfNeeded()
                .getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostTillPresent(UPDATE_POST_TITLE);

    }



}
