package loginTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

//

public class LoginTestAllInOneClass {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup(); //.m2
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Browser was opened");

    }

    @Test
    public void validLogin(){
        webDriver.get("https://aqa-complexapp.onrender.com/");
        logger.info("Website was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto");
        logger.info("qaauto was inputted into input UserName");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("password was inputted");

        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("Sign In was clicked");

        Assert.assertTrue("Button Sign Out is not Visible", isButtonSignOutVisible());
    }

    @Test
    public void inValidLogin(){
        webDriver.get("https://aqa-complexapp.onrender.com/");
        logger.info("Website for invalid login test was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaavto");
        logger.info("Invalid login was inputted");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("password for invalid login was inputted");

        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("Sign In for invalid login was clicked");

        Assert.assertTrue("Button Sign In is visible", isButtonSignInVisible());

        Assert.assertFalse("Sign Out button is not visible", isButtonSignOutVisible());

        Assert.assertTrue("Invalid username/password alert is present", isAlertInvalidUsernamePasswordVisible());
    }

    private boolean isAlertInvalidUsernamePasswordVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//div[contains(text(),'Invalid username/password.')]")).isDisplayed();
            logger.info("Invalid username/password alert visibility is: " + state);
            return state;
        } catch (Exception e) {
            logger.info("Invalid username/password alert is not displayed");
            return false;
        }
    }

    private boolean isButtonSignInVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath(".//form[@action='/login']//button")).isDisplayed();
            logger.info("Sign In button visibility is: " + state);
            return state;
        } catch (Exception e) {
            logger.info("Sign In is not displayed");
            return false;
        }
    }

    private boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[contains(text(),'Sign Out')]")).isDisplayed();
            logger.info("Sign Out button visibility is: " + state);
            return state;
        } catch (Exception e) {
            logger.info("Sign Out is not displayed");
            return false;
        }
    }

    @After
    public void tearDown(){
        webDriver.quit();
        logger.info("Browser was closed");

    }
}
