package registrationFormTest;

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
    public void validationMassagesTest(String userName, String email, String password, String expectedMessages) {
        pageProvider.loginPage()
                .openLoginPage()
                .getRegistrationForm()
                .enterTextIntoInputUsername(userName)
                .enterTextIntoInputEmail(email)
                .enterTextIntoInputPassword(password)
                .checkErrorMessages(expectedMessages)
        ;
    }

    public Collection parametersForValidationMessagesTests() throws IOException {
        final String pathToFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "registrationErrors";
        final boolean skipFirstRow = false;
        logger.info("Data file path" + pathToFile + "\nSheet name: " + sheetName + "\nSkip first row: " + skipFirstRow);

        return new ExcelSpreadsheetData(new FileInputStream(pathToFile), sheetName, skipFirstRow).getData();
    };
}
