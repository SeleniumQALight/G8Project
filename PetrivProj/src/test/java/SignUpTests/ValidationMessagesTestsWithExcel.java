package SignUpTests;

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
    public void validationMessagesTest(String userName, String email, String password, String expectedMessages) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoRegistrationUserNameField(userName);
        pageProvider.loginPage().enterTextIntoRegistrationEmailField(email);
        pageProvider.loginPage().enterTextIntoRegistrationPasswordField(password);
        pageProvider.loginPage().checkErrorMessages(expectedMessages);
    }

    public Collection parametersForValidationMessagesTests() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "registrationErrors";
        final boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToDataFile + "\nSheet name: " + sheetName + "\nSkip first row: " + skipFirstRow);

        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }
}
