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

import static data.TestData.VALID_PASSWORD;
import static data.TestData.WRONG_LOGIN;

public class LoginTestAllInOneClass {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());

    // Будет выполняться перед каждым тестом
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
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto");
        logger.info("'qaauto' was inputted into input UserName ");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("password was inputted ");

        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("Button Sign In was clicked");

        Assert.assertTrue("Button sign out is not visible", isElementVisible("//button[contains(text(),'Sign Out')]", "'Sign Out' button"));
    }

    private boolean isElementVisible(String xpath, String elementName) {
        try {
            boolean state = webDriver.findElement(By.xpath(xpath)).isDisplayed();
            logger.info(elementName + " is visible");
            return state;
        } catch (Exception e){
            logger.info(elementName + " is not displayed");
            return false;
        }
    }


    @Test
    public void invalidLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys(WRONG_LOGIN);
        logger.info(WRONG_LOGIN + " was inputted into input 'UserName'");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys(VALID_PASSWORD);
        logger.info(VALID_PASSWORD + " was inputted into input 'Password'");

        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("'Sign In' button was clicked");

        Assert.assertFalse("Button 'Sign Out' is not visible", isElementVisible(".//button[contains(text(),'Sign Out')]", "'Sign Out' button"));
        Assert.assertTrue("Button 'Sign In' is visible", isElementVisible(".//button[text()='Sign In']", "'Sign In' button"));
        Assert.assertTrue("Warning button Invalid username/password is visible", isElementVisible(".//div[text()='Invalid username/password.']", "Warning message"));
    }


}
