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
public class ValidationMessegesTestsWithExcel extends BaseTest{

    @Test
    @Parameters(method = "parametersForValidationMessegesTests")
    public void ValidationMessegesTests(String username, String email, String password, String expectedMesseges) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoRegistrationUsernameField(username);
        pageProvider.loginPage().enterTextIntoRegistrationEmailField(email);
        pageProvider.loginPage().enterTextIntoRegistrationPasswordField(password);
        pageProvider.loginPage().checkErrorMessage(expectedMesseges);
    }

    public Collection parametersForValidationMessegesTests() throws IOException {
        final String pathToFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "registrationErrors";
        final boolean skipFirstRow =false;  //if true - first row in excel file will be skipped
        logger.info("Data file path: " + pathToFile + "\nSheet name: " + sheetName + "\nSkip first row: " + skipFirstRow);

        return new ExcelSpreadsheetData(new FileInputStream(pathToFile), sheetName, skipFirstRow).getData();
    }
}
