package registrationTest;

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
    public void validationMessagesTest(String userName, String email, String password, String expectedMessages) {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoRegistrationInputLogin(userName);
        pageProvider.getLoginPage().enterTextIntoRegistrationInputEmail(email);
        pageProvider.getLoginPage().enterTextIntoRegistrationInputPassword(password);
        pageProvider.getLoginPage().checkErrorMessages(expectedMessages);
    }

    public Collection<Object[]> parametersForValidationMessagesTests() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "registrationErrors";
        final boolean skipFirstRow = false; // skip first row in Excel file
        logger.info("Data file path: " + pathToDataFile + ",\n Sheet name: " + sheetName + ", \nSkip first row: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }
}
