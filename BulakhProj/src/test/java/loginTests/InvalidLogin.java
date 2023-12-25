package loginTests;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class InvalidLogin {

    WebDriver webDriver;
    Logger logger = Logger.getLogger(InvalidLogin.class.getName());

    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("Browser was opened");

    }

    @After
    public void tearDown(){
        webDriver.quit();
        logger.info("Browser was closed");
    }



    @Test
    public void invalidLogin(){
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("invalid_user");
        logger.info("Invalid user was inputted into input UserName");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("invalid_password");
        logger.info("invalid password was inputted ");


        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("Button Sign in was clicked");

        Assert.assertTrue("Button Sign out is visible",isButtonSignOutNotVisible());
        Assert.assertTrue("Button Sign in is not visible",isButtonSignInVisible());
        Assert.assertTrue("Alert Danger is not visible",isAlertDangerVisible());

    }

private boolean isButtonSignOutNotVisible() { // method for checking that button Sign Out is not visible
        try {
            return webDriver.findElements(By.xpath("//button[contains(text(),'Sign Out')]")).size() == 0;
        }catch (Exception e){
            return true;
        }
    }

    private boolean isButtonSignInVisible() { // method for checking that button Sign In is visible
        try {
            return webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    private boolean isAlertDangerVisible() { // method for checking that Alert Danger is visible
        try {
            return webDriver.findElement(By.xpath("//div[@class=\"alert alert-danger text-center\" and contains(text(),\"Invalid username/password.\")]"))
                    .isDisplayed();
        }catch (Exception e){
            return false;
        }
    }


}
