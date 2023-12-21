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

public class InvalidLoginInOneClass {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());

    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();//.m2
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Browser was open");
    }

    @After
    public void tearDown(){
        webDriver.quit();
        logger.info("Browser was closed");
    }

    @Test
    public void validLogin(){
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qa_test");
        logger.info("'qa_test' was inputted into input UserName ");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qw777777");
        logger.info("password was inputted");

        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("Button Sign In was clicked");

        Assert.assertTrue("Button Sign In is visible", isButtonSignInVisible());
        Assert.assertTrue("Text Invalid username/password. is visible", isErrorMessageVisible());
        Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());

    }

    private boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[contains(text(),'Sign Out')]")).isDisplayed();
            logger.info(state + " is button Sign Out visible");
            return state;
        } catch (Exception e){
            logger.info("Sign Out button is not displayed");
            return true;
        }
    }

    private boolean isErrorMessageVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//div[contains(text(), 'Invalid username/password.')]")).isDisplayed();
            logger.info(state + " is error text visible");
            return state;
        } catch (Exception e){
            logger.info("Error text is not displayed");
            return false;
        }
    }

    private boolean isButtonSignInVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).isDisplayed();
            logger.info(state + " is button Sign In visible");
            return state;
        } catch (Exception e){
            logger.info("Sign In is not displayed");
            return false;
        }
    }
}
