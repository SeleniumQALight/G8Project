package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static libs.TestData.VALID_LOGIN_UI;
import static libs.TestData.VALID_PASSWORD_UI;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//button[contains(text(),'Sign In')]")
//цей елемент створиться PageFactory в CommonActionsWithElements
    private WebElement buttonSignIn;
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://aqa-complexapp.onrender.com");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not open Login Page");
            Assert.fail("Can not open Login Page");
        }
    }

    public void enterTextIntoInputLogin(String login) {
        enterTextIntoInput(inputLogin, login);
    }

    public void enterTextIntoInputPassword(String password) {
        enterTextIntoInput(inputPassword, password);
    }

    public void clickOnButtonSignIn() {
        //  WebElement buttonSignIn =
        //          webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
        clickOnElement(buttonSignIn);
    }

    public LoginPage isInputPasswordPresent() {
        isElementDisplayed(inputPassword);
        return this;
    }

    public LoginPage isInputUsernamePresent() {
        isElementDisplayed(inputLogin);
        return this;
    }

    public LoginPage isButtonSignInPresent() {
        isElementDisplayed(buttonSignIn);
        return this;
    }

    public LoginPage isInputUsernameNotPresent() {
        checkElementIsNotVisible(inputLogin);
        return this;
    }

    public LoginPage isInputPasswordNotPresent() {
        checkElementIsNotVisible(inputPassword);
        return this;
    }

    public LoginPage isButtonSignInNotPresent() {
        checkElementIsNotVisible(buttonSignIn);
        return this;
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(VALID_LOGIN_UI);
        enterTextIntoInputPassword(VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public LoginPage checkIsRedirectToLoginPage() {
        //TODO check url
        Assert.assertTrue("Invalid page - not Login Page"
                , isElementDisplayed(buttonSignIn));
        return this;
    }


}
