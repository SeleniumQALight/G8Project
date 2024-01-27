package postsTests;

import baseTest.BaseTest;
import junitparams.Parameters;
import libs.*;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class EditPostTitle extends BaseTest {

    final String LOGIN = "newqaauto";
    final String TIME_STAMP = Util.getDateAndTimeFormatted();
    final String POST_CREATED_SUCCESS_MESSAGE = "New post successfully created.";
    String postTitle;
    String updatedPostTitle;



    public void createNewPostWithExcelData (String title, String body, String valueInDropDown, String state){
        pageProvider.loginPage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleIntoInputTitle(title)
                .enterTextIntoInputBody(body)
                .selectValueInDropDown(valueInDropDown)
                .selectCheckboxUniqueState(state)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(POST_CREATED_SUCCESS_MESSAGE)
                .checkPostTitleText(title)
                .checkPostBodyText(body)
                .checkIsNoteStateExpected(valueInDropDown)
                .checkPostUniqueState("yes");
    }

    @Test
    @Parameters(method = "parametersForCreatePostTestFromExcel()")
    public void testEditJustCreatedPost() throws SQLException, ClassNotFoundException, IOException {
        Map<String, String> dataForNewPostFromExcelFile = ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(),
                "create_new_post");

        DB_Util_seleniumUsers dbUtilSeleniumUsers = new DB_Util_seleniumUsers();
        postTitle = String.format(dataForNewPostFromExcelFile.get("title"), "Serhata " + TIME_STAMP);
        String postBody = String.format(dataForNewPostFromExcelFile.get("body"), "Serhata" + TIME_STAMP);
        String valueInDropDown = dataForNewPostFromExcelFile.get("option");
        String state = dataForNewPostFromExcelFile.get("checkBoxStatus");
        updatedPostTitle = postTitle + dbUtilSeleniumUsers.getAliasForLogin(LOGIN);

        pageProvider.loginPage().openLoginPageAndFillLoginFormWithPasswordFromDB();
        pageProvider.homePage().checkIsRedirectToHomePage();

        createNewPostWithExcelData(postTitle, postBody, valueInDropDown, state);

        pageProvider.getPostPage()
                .clickOnEditButton()
                .updateTextIntoInputTitle(updatedPostTitle)
                .clickOnSaveUpdatesButton();
        pageProvider.homePage()
                .getHeader()
                .clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(updatedPostTitle);
    }

    @After
    public void deletePost() {
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded()
                .getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(updatedPostTitle)
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(postTitle);
    }
}
