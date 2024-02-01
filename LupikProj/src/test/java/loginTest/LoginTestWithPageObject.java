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
        pageProvider.loginPage().enterTextInToInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextInToInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Button Create Post is not visible", pageProvider.homePage().getHeader().isButtonCreatePostVisible());
        Assert.assertTrue("My profile icon is not visible", pageProvider.homePage().getHeader().isButtonMyProfileVisible());
        pageProvider.homePage().getHeader().checkUserNameIsPresent(VALID_LOGIN_UI);

        Assert.assertFalse("Field Username is visible", pageProvider.loginPage().isInputLoginFieldVisible());
        Assert.assertFalse("Field Password is visible", pageProvider.loginPage().isInputPasswordFieldVisible());


    }

    @Test
    public void invalidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin("qaauto1");
        pageProvider.loginPage().enterTextInToInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button SignOut is not visible", pageProvider.loginPage().isButtonSignInVisible());
        Assert.assertTrue("Text 'Invalid username/password' is not visible", pageProvider.loginPage().isTextInvalidLoginOrPasswordDisplayed());
        Assert.assertFalse("Button SignOut is visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());

    }

    @Test
    public void validLoginWithExel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(),
                "validLogOn");


        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin(dataForValidLogin.get("login"));
        pageProvider.loginPage().enterTextInToInputPassword(dataForValidLogin.get("pass"));
        pageProvider.loginPage().clickOnButtonSignIn();

       Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
      //  Assert.assertFalse("Button Create Post is not visible", pageProvider.homePage().getHeader().isButtonCreatePostVisible());
       // Assert.assertFalse("My profile icon is not visible", pageProvider.homePage().getHeader().isButtonMyProfileVisible());
        //pageProvider.homePage().getHeader().checkUserNameIsPresent(VALID_LOGIN_UI);

       // Assert.assertFalse("Field Username is visible", pageProvider.loginPage().isInputLoginFieldVisible());
       // Assert.assertFalse("Field Password is visible", pageProvider.loginPage().isInputPasswordFieldVisible());


    }
}