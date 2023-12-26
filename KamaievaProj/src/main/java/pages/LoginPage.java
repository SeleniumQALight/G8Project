package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {

    @FindBy(xpath = "//button[contains(text(),'Sign In')]") //this element will be created by PageFactory in CommonActionsWithElements
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://aqa-complexapp.onrender.com/");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not open login page");
            Assert.fail("Can not open login page");
        }
    }

    public void enterTextIntoInputLogin(String login) {
        enterTextIntoInput(inputLogin, login);
    }

    public void enterTextIntoInputPassword(String password) {
        enterTextIntoInput(inputPassword, password);
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public boolean isButtonSignInVisible() {
        return isElementDisplayed(buttonSignIn);
    }

    public boolean isAlertInvalidUsernamePasswordVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//div[text() = 'Invalid username/password.']")).isDisplayed();
            logger.info(state + " is alert visible");
            return state;
        } catch (Exception e) {
            logger.info("Alert is not displayed");
            return false;
        }
    }
}
