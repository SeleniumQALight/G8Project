package LoginTests;
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


import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class LoginTestAllInOneClass {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());

    // Буде виконано перед кожним тестом
    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup(); // .m2
        webDriver = new ChromeDriver();
       //   WebDriverManager.edgedriver().clearDriverCache().setup(); // .m2
        //  webDriver = new EdgeDriver();
        webDriver.manage().window().maximize();
        //webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
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

        WebElement inputUserName =
                webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto");
        logger.info("'qaauto' was inputted into input UserName ");

        WebElement inputPassword =
                webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("password was inputted ");

        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("Button Sign In was clicked");

        Assert.assertTrue("Button sign out is not visible", isButtonSignOutVisible());

    }


    @Test
    public void invalidLogin(){
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserName =
                webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaautoFail");
        logger.info("'qaautoFail' was inputted into input UserName ");

        WebElement inputPassword =
                webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("password was inputted ");

        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("Button Sign In was clicked");

        Assert.assertTrue("Button sign out is not visible", !isButtonSignOutVisible());
        Assert.assertTrue("Massage failLogin is absent", isMessageFailLogin());
    }





    private boolean isButtonSignOutVisible() {
        try {
            boolean state =
                    webDriver.findElement(By.xpath("//button[contains(text(),'Sign Out')]"))
                            .isDisplayed();
            logger.info(state + " is button visible");
            return state;
        } catch (Exception e){
            logger.info("Element is not displayed");
            return false;
        }
    }

    private boolean isMessageFailLogin() {
        try {
            boolean state =
                    webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center' and contains(text(),'Invalid username/password.')]")).isDisplayed();
            logger.info(state + " Fail massage");
            return state;
        } catch (Exception e){
            logger.info("Message FailLogin is not displayed");
            return false;
        }
    }

}