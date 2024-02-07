package loginTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ConfigProvider;
import libs.ExcelDriver;
import libs.ExcelSpreadsheetData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObject extends BaseTest {
    @Test
    @Category(SmokeTestFilter.class)
    public void validLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInput(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button SignOutTest is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Button CreatePost is not visible", pageProvider.homePage().getHeader().isButtonCreatePostVisible());
        Assert.assertTrue("Button MyProfile is not visible", pageProvider.homePage().getHeader().isButtonMyProfileVisible());
        Assert.assertTrue("Username is not visible", pageProvider.homePage().getHeader().isUserNameVisible());
        pageProvider.homePage().getHeader().checkTextInUserName(VALID_LOGIN_UI);
        Assert.assertFalse("Input Username is visible", pageProvider.loginPage().isInputUserNameVisible());
        Assert.assertFalse("Input Password is visible", pageProvider.loginPage().isInputPasswordVisible());

    }

    @Test
    @Parameters(method = "parametersForInvalidLogin")
    public void invalidLogin(String userName, String password,boolean signInVisibility, boolean signOutVisibility, boolean alertVisibility) {

        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInput(userName);
        pageProvider.loginPage().enterTextIntoInputPassword(password);
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertEquals("Button SignIn is not visible", signInVisibility, pageProvider.loginPage().isButtonSignInVisible());
        Assert.assertEquals("Button SignOutTest is visible", signOutVisibility, pageProvider.homePage().isButtonSignOutVisible());
        Assert.assertEquals("Alert is not visible", alertVisibility, pageProvider.loginPage().isAlertTextVisible());
    }

    public Collection parametersForInvalidLogin() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "LoginTestData.xls";
        final String sheetName = "invalidLogin";
        final boolean skipFirstRow = true; // skip first row in file (header)
        logger.info("Data file path:" + pathToDataFile + "\nSheet name: " + sheetName + "\nSkip first row: "  + skipFirstRow);

        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }

    @Test
    public void validLoginWithExel() throws IOException {
        Map<String, String> dataForValidLogin =
                ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInput(dataForValidLogin.get("login"));
        pageProvider.loginPage().enterTextIntoInputPassword(dataForValidLogin.get("pass"));
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button SignOutTest is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
    }

    @Test
    public void checkLoggedUserInNewTab() {
        pageProvider.loginPage().openLoginPage();
        String mainPageHandler = pageProvider.loginPage().getPageHandler();
        pageProvider.loginPage().enterTextIntoInput(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();
        pageProvider.homePage().checkIsRedirectToHomePage();
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
        String newTabHandler = pageProvider.openNewTab();
        pageProvider.loginPage().openLoginPage();
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.switchToTab(mainPageHandler);
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.switchToTab(newTabHandler);
        pageProvider.closeNewTab();
        pageProvider.switchToTab(mainPageHandler);
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
    }
    @Test
    public void checkToRefreshPage() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInput(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.refreshPage();
        pageProvider.loginPage().checkIsFieldLoginEmpty();
        pageProvider.loginPage().checkIsFieldPasswordEmpty();
        pageProvider.loginPage().checkIsButtonSignInVisible();

    }

}
