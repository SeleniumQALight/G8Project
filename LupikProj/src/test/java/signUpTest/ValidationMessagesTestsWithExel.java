package signUpTest;

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
    @Parameters(method = "parameterForValidationMessageTests")
    public void validationMessagesTest( String userName, String email,String password, String expectedMessage) {
           pageProvider.loginPage().openLoginPage();
           pageProvider.loginPage().enterTextIntoRegisterLogin(userName);
           pageProvider.loginPage().enterTextIntoRegisterEmail(email);
           pageProvider.loginPage().enterTextIntoRegisterPassword(password);
         pageProvider.loginPage().checkErrorMessages(expectedMessage);


 }

    public Collection parameterForValidationMessageTests() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "registrationErrors";
        final boolean skipFirstRow = true; //if true - first row will be skipped in excel file
        logger.info("Path to data file: " + pathToDataFile + "\nSheet name: " + sheetName + "\nSkip first row: " + skipFirstRow);

        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
 }
}
