package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {

    @FindBy(xpath = "//button[contains(text(),'Sign In')]") //This element will be initialized in PageFactory
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = "//div[contains(text(),'Invalid username/password.')]")
    private WebElement invalidAlert;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://aqa-complexapp.onrender.com");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not open login page");
            Assert.fail("Can not open login page");
        }

    }

    public void enterTextIntoInputLogin(String login) {
//        WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        enterTextIntoInput(inputLogin, login);
    }

    public void enterTextIntoInputPassword(String password) {
//        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        enterTextIntoInput(inputPassword, password);
    }

    public void clickOnButtonSignIn() {
//        WebElement buttonSignIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
        clickOnElement(buttonSignIn);
    }

    public boolean isButtonSignInVisible() {
//        WebElement buttonSignIn
//                = webDriver.findElement(By.xpath("//button[text()='Sign In']"));
        return isElementDisplayed(buttonSignIn);
    }

    public boolean isInvalidUserNamePasswordAlertVisible() {
//        WebElement invalidAlert = webDriver.findElement(By.xpath("//div[contains(text(),'Invalid username/password.')]"));
        return isElementDisplayed(invalidAlert);
    }
}
