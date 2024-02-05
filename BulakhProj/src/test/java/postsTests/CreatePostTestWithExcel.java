package postsTests;


import baseTast.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ConfigProvider;
import libs.ExcelSpreadsheetData;
import libs.Util;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;



@RunWith(JUnitParamsRunner.class)
public class CreatePostTestWithExcel extends BaseTest {

    String POST_TITLE;
    String POST_BODY;
    String TIME = Util.getDateAndTimeFormatted();
    String NAME = "Bulak";


    @Test
    @Parameters(method = "parametersForCreatePost")
    public void createPost(String postTitle, String textBody, String dropdownValue,
                           String checkboxValue, String expectedMessage, String uniquePostText) {
        POST_TITLE = String.format(postTitle, NAME,TIME);
        POST_BODY = String.format(textBody, TIME);



        pageProvider.loginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleInToInputTitle(POST_TITLE)
                .enterTextInToInputBody(POST_BODY)
                .setCheckboxState(checkboxValue)
                .selectValueInDropDown(dropdownValue)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(expectedMessage)
                .checkIsCreatedPostHasTitle(POST_TITLE)
                .checkIsCreatedPostHasBody(POST_BODY)
                .checkIsThisPostUniqueTextPresent(uniquePostText)
                .checkIsCreatedPostHasValueInDropDown(dropdownValue);


        pageProvider.getPostPage().getHeader().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE);



    }

    public Collection<Object[]> parametersForCreatePost() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "createPostWithExcel";
        final boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToDataFile + "\nSheet name: " + sheetName + "\nSkip first row: " + skipFirstRow);

        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }


}



