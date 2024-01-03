package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//опис методів для сторінки логін
public class LoginPage extends ParentPage{
    @FindBy(xpath = "//button[contains(text(),'Sign In')]") //all elements of this page described here //цей створиться PageFactory в CommonActionsWithElements
    private WebElement buttonSignIn; // all methods will be working with this element (buttonSignIn)

    @FindBy(xpath = ".//*[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//*[@placeholder='Password']")
    private WebElement inputPassword;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://aqa-complexapp.onrender.com/");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not open Login Page");
            Assert.fail("Can not open Login Page");
        }
    }

    public void enterTextInToInputLogin(String login) {
      //  WebElement inputLogin = webDriver.findElement(By.xpath(".//*[@placeholder='Username']"));
        enterTextInToInput(inputLogin, login);

    }
    public void enterTextInToInputPassword(String password) {
       // WebElement inputPassword = webDriver.findElement(By.xpath(".//*[@placeholder='Password']"));
        enterTextInToInput(inputPassword, password);
    }
    public void clickOnButtonSingIn() {
    //    WebElement buttonSingIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
        clickOnElement(buttonSignIn);
    }
    //is button Sign In visible
    public boolean isButtonSignInVisible() {
      //  WebElement buttonSignIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
        return isElementDisplayed(buttonSignIn);
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCred(){
        openLoginPage();
        enterTextInToInputLogin(TestData.VALID_LOGIN_UI);
        enterTextInToInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSingIn();
        return new HomePage(webDriver);
    }
    public boolean isButtonSignInVisible() {
        try {
            WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[text()='Sign In']"));
            return isElementDisplayed(buttonSignIn);
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isInvalidUserNamePasswordMessageVisible() {
        WebElement invalidMassage = webDriver.findElement(By.xpath("//div[contains(text(),'Invalid username/password.')]"));
        return isElementDisplayed(invalidMassage);
    }
}

