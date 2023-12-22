package loginTest;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
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


    // Буде виконано перед кожним тестом
    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Browser was opened");

    }

    // Буде виконано після кожного тесту
    @After
    public void tearDown() {
        webDriver.quit();
        logger.info("Browser was closed");
    }

    @Test
    public void validLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto");
        logger.info("'qaauto' was inputted into input UserName");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("password was inputted");

        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("Button Sign In was clicked");

        Assert.assertTrue("Button sign out is not visible", isButtonSignOutVisible());
    }

    @Test
    public void invalidLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qalogin");
        logger.info("'login' was inputted into input UserName");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("password was inputted");

        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("Button Sign In was clicked");

        Assert.assertFalse("Button sign out is not visible", isButtonSignOutVisible());
        Assert.assertTrue("Button sign in is visible", isButtonSignInInvisible());
        Assert.assertTrue("Message is visible", isMessageInvalidUsernamePasswordInVisible());

    }

    private boolean isMessageInvalidUsernamePasswordInVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center']")).isDisplayed();
            logger.info(state + " is message visible");
            return state;
        } catch (Exception e) {
            logger.info("Message is not displayed");
            return false;
        }
    }

    private boolean isButtonSignInInvisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).isDisplayed();
            logger.info(state + " is button visible");
            return state;
        } catch (Exception e) {
            logger.info("Element is displayed");
            return false;
        }
    }

    private boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[contains(text(),'Sign Out')]")).isDisplayed();
            logger.info(state + " is button visible");
            return state;
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }
    }
}
