package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import io.qameta.allure.*;
import libs.ConfigProvider;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.Map;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTestWithPageObject extends BaseTest {
    @Test
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Story("Base support for bdd annotations")
    @Category(SmokeTestFilter.class)
    public void validLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();

       Assert.assertTrue("Button SignOut is visible", pageProvider.homePage().getHeader().isButtonSignOutVisble());
       Assert.assertTrue("Button Create Post is visible", pageProvider.homePage().getHeader().isButtonCreatePostVisble());
       Assert.assertTrue("Button MyProfile is visible", pageProvider.homePage().getHeader().isButtonMyProfileVisble());
       Assert.assertTrue("Login is visible", pageProvider.homePage().getHeader().isLoginVisible());
       Assert.assertFalse("Field for input login is not visible", pageProvider.loginPage().isInputLoginVisible());
       Assert.assertFalse("Field for input password is not visible", pageProvider.loginPage().isInputPasswordVisible());
    }
    @Test
    @Category(SmokeTestFilter.class)
    public void invalidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin("1qaauto");
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button SignIn is visible", pageProvider.loginPage().isButtonSignInVisble());
        Assert.assertFalse("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisble());
        Assert.assertTrue("Error message is visible", pageProvider.loginPage().isErrorMessageVisible());
    }
    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin =
                ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(dataForValidLogin.get("login"));
        pageProvider.loginPage().enterTextIntoInputPassword(dataForValidLogin.get("pass"));
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button SignOut is visible", pageProvider.homePage().getHeader().isButtonSignOutVisble());

    }
}
