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
    public void setup() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Browser was opened");
    }

    @After
    public void tearDawn() {
        webDriver.quit();
        logger.info("Browser was closed");
    }

    @Test
    public void validLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com/");
        logger.info("Site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto");
        logger.info("'qaauto' was inputted into input UserName");

        WebElement inputUserPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputUserPassword.clear();
        inputUserPassword.sendKeys("123456qwerty");
        logger.info("'123456qwerty' was inputted into input Password");

        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("Button Sign in was clicked");

        boolean isSignInVisible = isButtonSignInVisible();
        boolean isSignOutVisible = isButtonSignOutVisible();

        Assert.assertFalse("Button sign in is not visible", isSignInVisible);
        Assert.assertTrue("Button sign out is visible", isSignOutVisible);
        Assert.assertFalse("Error message is not visible", isThereErrorMessage());
    }

    @Test
    public void invalidLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com/");
        logger.info("Site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto1");
        logger.info("'qaauto1' was inputted into input UserName");

        WebElement inputUserPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputUserPassword.clear();
        inputUserPassword.sendKeys("123456qwerty");
        logger.info("'123456qwerty' was inputted into input Password");

        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("Button Sign in was clicked");

        boolean isSignInVisible = isButtonSignInVisible();
        boolean isSignOutVisible = isButtonSignOutVisible();

        Assert.assertTrue("Button sign in is visible", isSignInVisible);
        Assert.assertFalse("Button sign out is not visible", isSignOutVisible);
        Assert.assertTrue("Error message is visible", isThereErrorMessage());
    }



    private boolean isButtonSignInVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"))
                    .isDisplayed();
            logger.info(state + " is button Sign In visible");
            return state;
        } catch (Exception e) {
            logger.info("Sign In is not displayed");
            return false;
        }
    }

    private boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[contains(text(),'Sign Out')]"))
                    .isDisplayed();
            logger.info(state + " is button Sign Out visible");
            return state;
        } catch (Exception e) {
            logger.info("Sign Out is not displayed");
            return false;
        }
    }

    private boolean isThereErrorMessage() {
        try {
            boolean state = webDriver.findElement(By.xpath("//div[contains(text(),'Invalid username/password.')]"))
                    .isDisplayed();
            logger.info(state + " is error message visible");
            return state;
        } catch (Exception e) {
            logger.info("Error message is not displayed");
            return false;
        }
    }
}
