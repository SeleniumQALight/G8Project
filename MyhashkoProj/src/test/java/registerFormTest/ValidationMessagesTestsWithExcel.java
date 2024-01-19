package registerFormTest;

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
public class ValidationMessagesTestsWithExcel extends BaseTest {

    @Test
    @Parameters(method = "parametersForValidationMessagesTests")
    public void ValidationMessagesTests(String userName, String email, String password, String expectedMessages) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputUsernameRegister(userName);
        pageProvider.loginPage().enterTextIntoInputEmailRegister(email);
        pageProvider.loginPage().enterTextIntoInputPasswordRegister(password);
        pageProvider.loginPage().checkErrorMessages(expectedMessages);
    }

    private Collection parametersForValidationMessagesTests() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "registrationErrors";
        final boolean skipFirstRow = true; // if true - skip first row in Excel file
        logger.info("Data file path: " + pathToDataFile + "\nSheet name: " + sheetName + "\nSkip first row: " + skipFirstRow);

        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }
}
