package registrationTests;

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

public class ValidationMessagesTestWithExcel extends BaseTest {

    @Test
    @Parameters(method = "parametersForValidationMessagesTests")
    public void validationMessagesTests(String userName, String email, String password, String expectedMessages) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputUserName(userName);
        pageProvider.loginPage().enterTextInToInputEmail(email);
        pageProvider.loginPage().enterTextInToInputPasswordForSignUp(password);
        pageProvider.loginPage().checkErrorMessages(expectedMessages);
    }

    public Collection parametersForValidationMessagesTests() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "registrationErrors";
        final boolean skipFirstRow = false; // if true - skip first row in excel file
        logger.info("data file path:" + pathToDataFile + "\n Sheet name:" + sheetName + "\n Skip first row:" + skipFirstRow);

        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }

}
