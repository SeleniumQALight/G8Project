package RegistrationTest;

import BaseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ConfigProvider;
import libs.ErrorMessage;
import libs.ExcelSpreadsheetData;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import static libs.ExcelDriver.getData;

@RunWith(JUnitParamsRunner.class)
public class ValidationMessageTestWithExcel extends BaseTest {
    @Test
    @Parameters(method = "parameterForValidationMessagesTests")
    public void validationMessageTest(String userName, String email, String password, String expectedMessages) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().inputTextIntoRegistrationLogin(userName);
        pageProvider.loginPage().inputTextIntoRegistrationEmail(email);
        pageProvider.loginPage().inputTextIntoRegistrationPassword(password);
        pageProvider.loginPage().checkErrorMessages(expectedMessages);
    }

    public Collection<Object[]> parameterForValidationMessagesTests() throws IOException { //collection of arrays of objects
        final String pathToExcelFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "registrationErrors"; //аркуш з якого ми беремо дані
        final boolean skipFirstRow = false; // чи потрібно пропускати перший рядок
        logger.info("Data file path :" + pathToExcelFile + "\nSheet Name :" + sheetName + "\nSkip first name :" + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToExcelFile), sheetName, skipFirstRow).getData();
    }
}

