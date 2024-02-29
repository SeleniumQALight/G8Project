package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.RegistrationFormElement;

import static data.TestData.*;

public class LoginPage extends ParentPage{
    @FindBy(xpath = ".//button[contains(text(),'Sign In')]") // цей елемент створиться в PageFactory в CommonActionsWithElements
    private WebElement buttonSingIn;

    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//div[text() = 'Invalid username/password.']")
    private WebElement validationMessage;

    @FindBy(xpath = ".//div[text() = 'Invalid username/password.']")
    private WebElement alertMessageInCenter;

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

    @Step
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

    @Step
    public LoginPage enterTextIntoInputLogin(String login) {
        enterTextIntoInput(inputLogin, login);
        return this;
    }

    @Step
    public LoginPage enterTextIntoInputPassword(String password) {
        enterTextIntoInput(inputPassword, password);
        return this;
    }

    @Step
    public void clickOnButtonSingIn() {
//        WebElement buttonSingIn =
//                webDriver.findElement(By.xpath(".//button[contains(text(),'Sign In')]"));
        clickOnElement(buttonSingIn);
    }

    @Step
    public void clickOnButtonSingInWithEnterButton() {
        pressTheEnterKey(buttonSingIn);
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

    @Step
    public void checkAllElementsFromLoginFormAreInvisible() {
        checkIsElementNotVisible(inputLogin);
        checkIsElementNotVisible(inputPassword);
        checkIsElementNotVisible(buttonSingIn);
    }

    public LoginPage openLoginPageInNewTabWithJS(int num_of_tab){
        openNewTabWithJS();
        switchToTab(num_of_tab);
        openLoginPage();

        return this;
    }

    public LoginPage checkTextInAlertInCenter(String message) {
        checkTextInElement(alertMessageInCenter, message);
        //Assert.assertEquals("Message in center of page is not displayed", message, alertMessageInCenter.getText());
        return this;
    }
}
