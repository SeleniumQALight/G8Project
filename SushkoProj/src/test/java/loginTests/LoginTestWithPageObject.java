package loginTests;

import baseTest.BaseTest;
import categories.SmokeTestsFilter;
import io.qameta.allure.*;
import libs.ConfigProvider;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.Map;

import static data.TestData.*;

@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTestWithPageObject extends BaseTest {
    @Test
    @Category(SmokeTestsFilter.class)
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Story("Base support for bdd annotations")
    public void validLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSingIn();

        pageProvider.homePage().getHeader().checkAllElementsInHeaderAreVisible();
        pageProvider.loginPage().checkAllElementsFromLoginFormAreInvisible();

//I did it first via assert, then replaced with new created methods, but they do more checks than specified in the task.
// it looks better through the methods, but I commented the first option just in case
//
//        Assert.assertTrue("Button SignOut is not visible",
//                pageProvider.homePage().getHeader().isButtonSignOutVisible());
//        Assert.assertTrue("Button Create Post is not visible",
//                pageProvider.homePage().getHeader().isButtonCreatePostIsVisible());
//        Assert.assertTrue("Button My Profile is not visible",
//                pageProvider.homePage().getHeader().isButtonMyProfileIsVisible());
//        Assert.assertTrue("User profile name is not visible",
//                pageProvider.homePage().getHeader().isSpanUserProfileNameIsVisible());

//        Assert.assertFalse("Field for login is visible",
//                pageProvider.loginPage().isInputLoginIsVisible());
//        Assert.assertFalse("Field for password is visible",
//                pageProvider.loginPage().isInputPasswordIsVisible());

    }

    @Test
    public void validLoginWithTabAndEnterButtons() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().pressTheTabKey();
        pageProvider.loginPage().pressTheTabKey();


        pageProvider.loginPage().enterTextWithoutGettingElement(VALID_LOGIN_UI);
        pageProvider.loginPage().pressTheTabKey();
        pageProvider.loginPage().enterTextWithoutGettingElement(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSingInWithEnterButton();

        Assert.assertTrue("Button SignOut is not visible",
                pageProvider.homePage().getHeader().isButtonSignOutVisible());
    }

    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(dataForValidLogin.get("login"));
        pageProvider.loginPage().enterTextIntoInputPassword(dataForValidLogin.get("pass"));
        pageProvider.loginPage().clickOnButtonSingIn();

        pageProvider.homePage().getHeader().checkAllElementsInHeaderAreVisible();
        pageProvider.loginPage().checkAllElementsFromLoginFormAreInvisible();
    }

    @Test
    public void invalidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin("qaauto_1");
        pageProvider.loginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSingIn();

        Assert.assertTrue("Button SignIn is not visible",
                pageProvider.loginPage().isButtonSignInIsVisible());
        Assert.assertFalse("Button SignOut is visible",
                pageProvider.homePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Validation message is not displayed",
                pageProvider.loginPage().isValidationMessageIsDisplayed());
    }

    @Test
    public void loginAndOpenLoginPageInNewTab() {
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCreds()
                .getHeader()
                .checkIsButtonSignOutVisible()
        ;

        pageProvider.loginPage()
                .openLoginPageInNewTabWithJS(1)
        ;

        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.loginPage().switchToMainTab();

        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();

        pageProvider.loginPage().closeTabAndSwitchToMainTab(1);
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
    }

    @Test
    public void loginAndRefreshPage() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);

        pageProvider.loginPage().refreshPage();
        pageProvider.loginPage().clickOnButtonSingIn();

        Assert.assertFalse("Button SignOut is visible",
                pageProvider.homePage().getHeader().isButtonSignOutVisible());
    }
}
