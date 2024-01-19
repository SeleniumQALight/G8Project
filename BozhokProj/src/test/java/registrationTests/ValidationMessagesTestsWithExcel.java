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
public class ValidationMessagesTestsWithExcel extends BaseTest {

    @Test
    @Parameters(method = "parametersForValidationMessageTests")
    public void validationMessagesTests(String userName, String email, String password, String expectedMessage) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextRegistrationUserNameField(userName);
        pageProvider.loginPage().enterTextRegistrationEmailField(email);
        pageProvider.loginPage().enterTextRegistrationPasswordField(password);
        pageProvider.loginPage().checkErrorsMessages(expectedMessage);
    }

    public Collection<Object[]> parametersForValidationMessageTests() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "registrationErrors";
        final boolean skipFirstRow = false; // if true - skip first row in excel file
        logger.info("Data file path: " + pathToDataFile + "\nSheet name: " + sheetName + "\nSkip first row: " + skipFirstRow);

        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }
}

