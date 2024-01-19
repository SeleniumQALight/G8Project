package regestrationTests;

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

public class ValidationMessagesTestsWithExel extends BaseTest {


    @Test
    @Parameters(method = "parametersForValidationMessagesTests")
    public void TC_001_checkValidationMessages(String userName, String email, String password, String expectedMessage) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoRegistrationUserNameField(userName);
        pageProvider.loginPage().enterTextIntoRegistrationEmailField(email);
        pageProvider.loginPage().enterTextIntoRegistrationPasswordField(password);
        pageProvider.loginPage().checkErrorMessages(expectedMessage);
    }

    public Collection parametersForValidationMessagesTests() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "registrationErrors";
        final boolean skipFirstRow = false; // skip first row in file (header)
        logger.info("Data file path:" + pathToDataFile + "\nSheet name: " + sheetName + "\nSkip first row: "  + skipFirstRow);

        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }
}
