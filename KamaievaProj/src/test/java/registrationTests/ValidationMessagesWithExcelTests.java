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
public class ValidationMessagesWithExcelTests extends BaseTest {
    @Test
    @Parameters(method = "parametersForValidationMessagesTests")
    public void validationMessagesTests(String username, String email, String password, String errorMessage) {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputUsernameRegister(username);
        pageProvider.getLoginPage().enterTextIntoInputEmailRegister(email);
        pageProvider.getLoginPage().enterTextIntoInputPasswordRegister(password);
        pageProvider.getLoginPage().checkErrorsMessages(errorMessage);
    }

    public Collection<Object[]> parametersForValidationMessagesTests() throws IOException {
        final String pathToFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "registrationErrors";
        final boolean skipFirstLine = false; // skip first line in Excel file
        logger.info("Data file: " + pathToFile + ", sheet: " + sheetName + ", skip first line: " + skipFirstLine);
        return new ExcelSpreadsheetData(new FileInputStream(pathToFile), sheetName, skipFirstLine).getData();
    }
}
