package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.RegistrationFormElement;

import static libs.TestData.*;

public class LoginPage extends ParentPage{
    @FindBy(xpath = ".//button[contains(text(),'Sign In')]") // цей елемент створиться в PageFactory в CommonActionsWithElements
    private WebElement buttonSingIn;

    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//div[text() = 'Invalid username/password.']")
    private WebElement validationMessage;

    private RegistrationFormElement registrationFormElement;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public void checkIsRedirectToLoginPage(){
        checkUrl();
    }

    public RegistrationFormElement getRegistrationForm() {
        return registrationFormElement = new RegistrationFormElement(webDriver);
    }

    public LoginPage openLoginPage() {
        try{
            webDriver.get(baseUrl);
            logger.info("Login page was opened " + baseUrl);
        }catch (Exception e){
            logger.error("Can not open login page");
            Assert.fail("Can not open login page");
        }
        return this;
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

    public boolean isInputLoginIsVisible() {
        return isElementDisplayed(inputLogin);
    }

    public boolean isInputPasswordIsVisible() {
        return isElementDisplayed(inputPassword);
    }

    public boolean isButtonSignInIsVisible(){
        return isElementDisplayed(buttonSingIn);
    }

    public boolean isValidationMessageIsDisplayed() {
        return isElementDisplayed(validationMessage);
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCreds() {
        openLoginPage();
        enterTextIntoInputLogin(VALID_LOGIN_UI);
        enterTextIntoInputPassword(VALID_PASSWORD_UI);
        clickOnButtonSingIn();
        return new HomePage(webDriver);
    }

    public void checkAllElementsFromLoginFormAreVisible() {
        isInputLoginIsVisible();
        isInputPasswordIsVisible();
        isButtonSignInIsVisible();

    }
    public void checkAllElementsFromLoginFormAreInvisible() {
        checkIsElementNotVisible(inputLogin);
        checkIsElementNotVisible(inputPassword);
        checkIsElementNotVisible(buttonSingIn);
    }
}
