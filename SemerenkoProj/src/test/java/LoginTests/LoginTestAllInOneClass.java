package LoginTests;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class LoginTestAllInOneClass {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());

    @Before
    public void setup() {
//        WebDriverManager.chromedriver().setup();
//        webDriver = new ChromeDriver();
//        WebDriverManager.edgedriver().setup();
//        webDriver = new EdgeDriver();
        File fileFF = new File("./src/drivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", fileFF.getAbsolutePath());
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

        WebElement inputUserName =
                webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto");
        logger.info("'qaauto' input into userName");

        WebElement inputPassword =
                webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("pass input into userPass");

        webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']")).click();
        logger.info("button click succes");

        Assert.assertTrue("Button SignOut is absent", isButtonSignOutVisible());


    }

    private boolean isButtonSignOutVisible() {
        try{

        boolean state = webDriver.findElement(By.xpath(".//button[@class='btn btn-sm btn-secondary']")).isDisplayed();
        logger.info(state + "SignOut button");
        return state;}
        catch(Exception e){
            logger.info("Element is not displayed");
        }
        return false;
    }
}
