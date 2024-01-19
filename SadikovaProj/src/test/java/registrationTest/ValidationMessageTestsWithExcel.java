package registrationTest;

import baseTest.BaseTest;
import data.TestData;
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
public class ValidationMessageTestsWithExcel extends BaseTest {

    @Test
    @Parameters(method = "parametersForValidationMessageTests")
    public void validationMessageTest(String userName, String email, String password, String expectedMessages) {
        pageProvider.mainPage().openMainPage();
        pageProvider.mainPage().fillRegistrationForm(userName, email, password);
        pageProvider.mainPage().checkErrorsMessage(expectedMessages);

    }

    public Collection parametersForValidationMessageTests() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "registrationErrors";
        final boolean skipFirstRow = true; //if true - skip first row inn excel file
        logger.info("Data file patch" + pathToDataFile + "\nSheet name: " + sheetName + "\n Skip first row: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();

    }
}


