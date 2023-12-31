package loginTests;

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


public class LoginTestAllInOneClass {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());

    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup(); // скачає з інету файл для хрому, що буде виконувати команди
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Browser was opened");

    }

    @After
    public void teraDown(){
        webDriver.quit();
        logger.info("Browser was closed");
    }
    @Test
    public void validLogin(){
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto");
        logger.info("'qaauto' was inputted into input UserName");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder=\"Password\"]"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("password was inputted");

        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("button Sign In was clicked");

        Assert.assertTrue("Button sign out is not visible", isButtonSignOutVisible());
    }

    @Test
    public void invalidLogin(){
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto_1");
        logger.info("'qaauto_1' was inputted into input UserName");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("password was inputted");

        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("button Sign In was clicked");

        Assert.assertTrue("Validation message is not displayed", isValidationMessageIsDisplayed());
        Assert.assertTrue("Button sign in is not visible", isButtonSignInVisible());
        Assert.assertFalse("Button sign out is visible", isButtonSignOutVisible());
    }

    private boolean isButtonSignInVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).isDisplayed();
            logger.info(state + " - Sign In button visible");
            return state;
        } catch (Exception e){
            logger.info("Element is not displayed");
            return false;
        }
    }

    private boolean isValidationMessageIsDisplayed() {
        try {
            boolean state = webDriver.findElement(By.xpath(".//div[text() = 'Invalid username/password.']")).isDisplayed();
            logger.info(state + " - Invalid username/password validation message visible");
            return state;
        } catch (Exception e){
            logger.info("Element is not displayed");
            return false;
        }
    }

    private boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[contains(text(),'Sign Out')]")).isDisplayed();
            logger.info(state + " - is button visible");
            return state;
        } catch (Exception e){
            logger.info("Element is not displayed");
            return false;
        }
    }
}
