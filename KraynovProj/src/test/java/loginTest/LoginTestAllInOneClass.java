package loginTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class LoginTestAllInOneClass {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());

    // @Before - аннотация, которая указывает, что метод будет выполняться перед каждым тестом
    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup(); // .m2
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Browser was opened");
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
        inputUserName.sendKeys("qaauto");
        logger.info("'qaauto' was inputted into input UserName ");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("password was inputted ");

        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("Button Sing In was clicked");

        Assert.assertTrue("Button sign out in not visible", isButtonSignOutVisible());
    }

    @Test
    public void invalidLogin(){
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("INCORRECT");
        logger.info("'qaauto' was inputted into input UserName ");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("password was inputted ");

        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("Button Sing In was clicked");

        Assert.assertTrue("Button Sign In is not displayed", isButtonSignInDisplayed());
        Assert.assertTrue("Text 'Invalid username/password' is not displayed", isTextInvalidLoginOrPasswordDisplayed());
        Assert.assertFalse("Button Sign Out is displayed", isButtonSignOutVisible());
    }

    private boolean isButtonSignOutVisible() {
        try {
            boolean state =
                    webDriver.findElement(By.xpath("//button[contains(text(),'Sign Out')]")).isDisplayed();
            logger.info(state + " is button visible");
            return state;
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }
    }

    private boolean isButtonSignInDisplayed() {
        try {
            boolean state = webDriver.findElement(By.xpath(".//button[contains(text(),'Sign In')]")).isDisplayed();
            logger.info("Button Sign In is displayed -> " + state);
            return state;
        } catch (Exception e) {
            logger.info("Button Sign In is displayed -> false");
            return false;
        }
    }

    private boolean isTextInvalidLoginOrPasswordDisplayed() {
        try {
            boolean state = webDriver.findElement(By.xpath(".//div[contains(text(),'Invalid username/password.')]")).isDisplayed();
            logger.info("Text 'Invalid username/password.' is displayed -> " + state);
            return state;
        } catch (Exception e) {
            logger.info("Text 'Invalid username/password.' is displayed -> false");
            return false;
        }
    }

}
