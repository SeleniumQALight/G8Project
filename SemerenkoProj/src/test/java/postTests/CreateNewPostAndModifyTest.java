package postTests;

import baseTest.BaseTest;
import data.TestData;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

import static data.TestData.NEW_LOGIN_UI;


public class CreateNewPostAndModifyTest extends BaseTest {
    private DB_Util_seleniumUsers dbUtilSeleniumUsers = new DB_Util_seleniumUsers();
    private Map<String, String> dataForValidLogin =
            ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "NewPost");
    private String postTitles = dataForValidLogin.get("postTitle") + Util.getDateAndTimeFormatted();
    ;
    private String postTitlesNew = postTitles + dbUtilSeleniumUsers.getAliasForLogin(NEW_LOGIN_UI);
    private String password = dbUtilSeleniumUsers.getPassForLogin(NEW_LOGIN_UI);

    public CreateNewPostAndModifyTest() throws SQLException, ClassNotFoundException, IOException {
    }

    @Before
    public void createNewPost() throws SQLException, ClassNotFoundException, IOException {


        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred(NEW_LOGIN_UI, password)
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(postTitles)
                .enterTextIntoInputBody(dataForValidLogin.get("postBody"))
                .setStateOfCheckBox(dataForValidLogin.get("stateOfCheckBox"))
                .selectValueInDropDown(dataForValidLogin.get("dropDownValue"))
                .clickOnSavePostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(dataForValidLogin.get("massage"))
                .checkIsTitleVisible(postTitles)
                .checkIsBodyVisible(dataForValidLogin.get("postBody"))
                .checkNoteCreatePostMessage(dataForValidLogin.get("dropDownValue"))
                .checkStateOfUniquePost(dataForValidLogin.get("stateOfUniquePost"))
                .getHeader().clickOnButtonProfile(NEW_LOGIN_UI)
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(postTitles);
    }


    @Test
    public void SearchAndModifyPost() throws SQLException, ClassNotFoundException {
        pageProvider.homePage()
                .openHomePageLoginIfNeeded(NEW_LOGIN_UI, password)
                .checkIsRedirectToHomePage()
                .getHeader()
                .clickOnButtonProfile(NEW_LOGIN_UI)
                .checkPostWithTitleIsPresent(postTitles)
                .clickOnPostWithTitle(postTitles)
                .clickOnEditButton()
                .enterTextIntoInputTitle(postTitlesNew)
                .clickOnSaveUpdates()
                .checkIsSuccessPostUpdateMessageVisible()
                .getHeader()
                .clickOnButtonProfile(NEW_LOGIN_UI)
                .checkPostWithTitleIsPresent(postTitlesNew)

        ;

    }

    @After
    public void deletePost() {
        pageProvider.homePage().openHomePageLoginIfNeeded(NEW_LOGIN_UI, password)
                .getHeader().clickOnButtonProfile(NEW_LOGIN_UI)
                .checkIsRedirectToMyProfilePage()
                .deletePostTillPresent(postTitles)
                .deletePostTillPresent(postTitlesNew);
    }
}
