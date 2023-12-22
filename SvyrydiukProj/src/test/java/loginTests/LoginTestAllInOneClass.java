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

import java.util.Date;

public class LoginTestAllInOneClass {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());

    //Will be executed before each test
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup(); //.m2
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, java.util.concurrent.TimeUnit.SECONDS);
        logger.info("Browser was opened");
    }

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
        logger.info("Login 'qaauto' was entered into input UserName");
        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("Password was entered into input Password");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        logger.info("Button 'Sign In' was clicked");

        Assert.assertTrue("Button SignOut is visible", isButtonSignOutVisible());
    }


    @Test
    public void invalidLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputUserName.clear();
        Date date = new Date();
        inputUserName.sendKeys("qaauto" + date.getTime());
        logger.info("Login 'qaauto' was entered into input UserName");
        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("Password was entered into input Password");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        logger.info("Button 'Sign In' was clicked");

        Assert.assertFalse("Button SignOut is not visible", isButtonSignOutVisible());
        Assert.assertTrue("Button SignIn is visible", isButtonSignVisible());
        Assert.assertTrue("Warning message Invalid username/password is visible", isWarningMessageInvalidLoginVisible());
    }


    private boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
            logger.info(state + " Button SignOut is visible");
            return state;
        } catch (Exception e) {
            logger.info("Button SignOut is not visible");
            return false;
        }
}

        private boolean isButtonSignVisible() {
            try {
                boolean state = webDriver.findElement(By.xpath(".//button[text()='Sign In']")).isDisplayed();
                logger.info(state + " Button SignIn is visible");
                return state;
            } catch (Exception e) {
                logger.info("Button SignIn is not visible, exception error occurred");
                return false;
            }

        }
        //home work Sign in button is visible

            private boolean isWarningMessageInvalidLoginVisible() {
                try {
                    boolean state = webDriver.findElement(By.xpath(".//div[text()='Invalid username/password.']")).isDisplayed();
                    logger.info(state + "Warning message Invalid username/password is visible");
                    return state;
                } catch (Exception e) {
                    logger.info("Warning message Invalid username/password is not visible");
                    return false;
                }
    }

}
