package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//button[contains(text(),'Sign In')]")//цей елемент створиться PageFactory в CommonActionsWithElements
    public static WebElement buttonSignIn;
    @FindBy(xpath = ".//input[@placeholder='Username']")
    public static WebElement inputLogin;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    public static WebElement inputPassword;


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

    public void enterTextInToInputLogin(String login) {
        enterTextInToInput(inputLogin, login);
    }

    public void enterTextInToInputPassword(String password) {
        enterTextInToInput(inputPassword, password);
    }

    public void clickOnButtonSignIn() {
       // WebElement buttonSignIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
        clickOnElement(buttonSignIn);
    }

    public boolean isButtonSignInPresent() {
        try {
            boolean state = buttonSignIn.isDisplayed();
            logger.info(state + " is input Password visible");
            return state;
        } catch (Exception e){
            logger.info("Input Password is not displayed");
            return false;
        }
    }

    public boolean isErrorMessagePresent() {
        try {
            boolean state = webDriver.findElement(By.xpath("//div[@class='alert alert-danger text-center']")).isDisplayed();
            logger.info(state + " is error message visible");
            return state;
        } catch (Exception e){
            logger.info("Error message is not displayed");
            return false;
        }
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextInToInputLogin(TestData.VALID_LOGIN_UI);
        enterTextInToInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public boolean isInputLoginPresent() {
        try {
            boolean state = inputLogin.isDisplayed();
            logger.info(state + " is input Login visible");
            return state;
        } catch (Exception e){
            logger.info("Input Login is not displayed");
            return false;
        }
    }

    public boolean isInputPasswordPresent() {
        try {
            boolean state = inputPassword.isDisplayed();
            logger.info(state + " is input Password visible");
            return state;
        } catch (Exception e){
            logger.info("Input Password is not displayed");
            return false;
        }
    }

    public LoginPage checkIsRedirectToLoginPage() {
        Assert.assertTrue("Invalid page - not Login Page", isButtonSignInPresent());
        Assert.assertTrue("Invalid page - not Login Page", isInputLoginPresent());
        Assert.assertTrue("Invalid page - not Login Page", isInputPasswordPresent());
        return this;
    }

    public void checkIsLoginFieldIsNotVisible() {
        isInputLoginPresent();
        isInputPasswordPresent();
        isButtonSignInPresent();
        logger.info("Login fields are not visible");
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }
}
