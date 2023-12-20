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

public class LoginTestAllInOneClass {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());



    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();  // .m2 папка куди завантажиться
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Browser was opened");


    }

    @After
    public void tearDawn(){
        webDriver.quit();
        logger.info("Browser was closed");

    }

    @Test
    public void validLogin(){
        webDriver.get("https://aqa-complexapp.onrender.com/");
        logger.info("Site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("aszyre");
        logger.info("'aszyre' was inputted into input UserName");

        WebElement inputUserPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputUserPassword.clear();
        inputUserPassword.sendKeys("aszyre592915");
        logger.info("'aszyre592915' was inputted into input Password");

        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("Button Sign in was clicked");

        Assert.assertTrue("Button sign out is not visible", isButtonSignOutVisible());


    }

    private boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[contains(text(),'Sign Out')]"))
                    .isDisplayed();
            logger.info(state + " is button visible");
            return state;
        } catch (Exception e){
         logger.info("Element is not displayed");
         return false;
        }
    }
}
