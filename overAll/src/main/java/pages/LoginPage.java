package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage{
    @FindBy(xpath = ".//button[contains(text(),'Sign In')]") // цей елемент створиться PageFactory в CommonActionsWithElements
    private WebElement buttonSingIn;

    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public void openLoginPage() {
        try{
            webDriver.get(baseUrl);
            logger.info("Login page was opened " + baseUrl);
        }catch (Exception e){
            logger.error("Can not open login page");
            Assert.fail("Can not open login page");
        }
    }

    public void checkIsRedirectToLoginPage() {
        checkUrl();
    }

    public void enterTextIntoInputLogin(String login) {
        enterTextIntoInput(inputLogin, login);
    }

    public void enterTextIntoInputPassword(String password) {
        enterTextIntoInput(inputPassword, password);
    }

    public void clickOnButtonSingIn() {
//        WebElement buttonSingIn =
//                webDriver.findElement(By.xpath(".//button[contains(text(),'Sign In')]"));
        clickOnElement(buttonSingIn);
    }

    // is button Sign In visible
    public boolean isButtonSignInVisible() {
        return isElementDisplayed(buttonSingIn);
    }


    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSingIn();
        return new HomePage(webDriver);
    }
}
