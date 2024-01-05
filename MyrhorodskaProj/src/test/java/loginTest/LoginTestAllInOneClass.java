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
    Logger logger = Logger.getLogger(getClass());;

// буде виконуватись перед кожним тестом (відкривати браузер)

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup(); // .m2
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Browser was opened");



    }

    @After
    public void tearDown() {
        webDriver.quit();
        logger.info("Browser was closed");

    }


    @Test
    public void validLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com/");
        logger.info("Site was opened");
        WebElement inputUserName = webDriver.findElement(By.xpath(".//*[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto");
        logger.info("'qaauto' was inputted into input UserName");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//*[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("password was inputted");

        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("Button Sign in was clicked");

        Assert.assertTrue("Button sign out is not visible", isButtonSignOutVisible());
    }

    @Test
    public void notValidLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com/");
        logger.info("Site was opened");
        WebElement inputUserName = webDriver.findElement(By.xpath(".//*[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto1");
        logger.info("'qaauto' was inputted into input UserName");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//*[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("password was inputted");

        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("Button Sign in was clicked");

        Assert.assertFalse("Button sign out is not visible", isButtonSignOutVisible());

        Assert.assertTrue("Button Sign In is not visible", isButtonSignInVisible());


        WebElement errorMessage = webDriver.findElement(By.xpath(".//div[contains(text(),'Invalid username/password.')]"));
        Assert.assertEquals("Invalid username/password. message is not displayed", "Invalid username/password.", errorMessage.getText());


    }

    private boolean isButtonSignInVisible() {
        try {
            return webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).isDisplayed();
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }
    }

    private boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[contains(text(),'Sign Out')]")).isDisplayed();
            logger.info(state + "is button visible");
            return state;
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }

    }
    private boolean isInvalidUsernameOrPasswordMessageVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath(".//div[contains(text(),'Invalid username/password.')]")).isDisplayed();
            logger.info(state + " is error message visible");
            return state;
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }
    }
}


