package pages;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage  extends ParentPage{


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try{
            webDriver.get("https://aqa-complexapp.onrender.com");
            logger.info("Login page was opened");
        }catch (Exception e){
            logger.error("Can not open login page");
            Assert.fail("Can not open login page");
        }
    }

    public void enterTextInToInputLogin(String login) {
        WebElement inputLogin = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        enterTextInToInput(inputLogin, login);
    }

    public void enterTextInToInputPassword(String password) {
        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        enterTextInToInput(inputPassword, password);
    }

    public void clickOnButtonSingIn() {
        WebElement buttonSingIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
        clickOnElement(buttonSingIn);
    }

    public boolean isButtonSignInVisible(){
        WebElement buttonSignIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
        return isElementDisplayed(buttonSignIn);
    }

    public boolean isInvalidLoginMessageDisplayed(){
        WebElement invalidLoginMessage = webDriver.findElement(By.xpath("//div[@class=\"alert alert-danger text-center\" and contains(text(),\"Invalid username/password.\")]"));
        return isElementDisplayed(invalidLoginMessage );
    }


}
