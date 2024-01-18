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

    public void validationMessagesTests(String userName, String email, String password, String expectedMessage) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoRegistrationUserNameField(userName);
        pageProvider.loginPage().enterTextIntoRegistrationEmailField(email);
        pageProvider.loginPage().enterTextIntoRegistrationPasswordField(password);
        pageProvider.loginPage().checkErrorsMessages(expectedMessage);
               // (ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD);

    }

    public Collection  parametersForValidationMessagesTests() throws IOException {
        final String pathToFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "registrationErrors";
        final boolean skipFirstRow = true;// if true skip first row in exel file
        logger.info("Data file path: " + pathToFile + "\nsheet name: " + sheetName + "\nskip first row: " + skipFirstRow);

        return new ExcelSpreadsheetData (new FileInputStream(pathToFile), sheetName, skipFirstRow).getData();

        }
    }
