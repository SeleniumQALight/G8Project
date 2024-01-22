package postsTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ConfigProvider;
import libs.ExcelSpreadsheetData;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

@RunWith(JUnitParamsRunner.class)

public class CreateNewPostTestWithExcel extends BaseTest {
    @Test
    @Parameters(method = "parametersForCreatePostTests")
    public void TC_001_createNewPost(String title, String body, String state, String valueInDropDown, String expectedMessages, String uniqueState) {
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleIntoInputTitle(title)
                .enterTextIntoInputBody(body)
                .selectValueInDropDown(valueInDropDown)
                .selectCheckboxUniqueState(state)
                //.selectTextInDropDown("Приватне повідомлення")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkSuccessCreatePostMessages(expectedMessages)
                .checkPostTitleText(title)
                .checkPostBodyText(body)
                .checkPostUniqueState(uniqueState)
        ;

//        pageProvider.getPostPage().getHeader().clickOnMyProfileButton()
//                .checkIsRedirectToMyProfilePage()
//                .checkPostWithTitleIsPresent(title)
//        ;
    }

    public Collection parametersForCreatePostTests() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "createPostWithExcelSerhata";
        final boolean skipFirstRow = false; // if true - skip first row in excel file
        logger.info("data file path:" + pathToDataFile + "\n Sheet name:" + sheetName + "\n Skip first row:" + skipFirstRow);

        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }
}
