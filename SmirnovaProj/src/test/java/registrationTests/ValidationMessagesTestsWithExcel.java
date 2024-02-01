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
    @Parameters(method = "parametersForValidationMessagesTests")
    public void validationMessagesTest(String username, String email, String password, String expectedMessages) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage()
                .enterTextIntoInputUsernameRegister(username)
                .enterTextIntoInputEmailRegister(email)
                .enterTextIntoInputPasswordRegister(password);
        pageProvider.loginPage().checkErrorsMessages(expectedMessages);
    }

    public Collection<Object[]> parametersForValidationMessagesTests() throws IOException {
        final String pathToFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "/testDataSuit.xls";
        final String sheetName = "registrationErrors";
        final boolean skipFirstLine = false;//пропустить первую строку
        logger.info("Path to file: " + pathToFile +
                " Sheet name: " + sheetName + " Skip first line: " + skipFirstLine);
        return new ExcelSpreadsheetData(new FileInputStream(pathToFile), sheetName, skipFirstLine).getData();
    }
}
