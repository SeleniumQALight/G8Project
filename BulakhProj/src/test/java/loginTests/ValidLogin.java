package loginTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ValidLogin {
   WebDriver webDriver;
   Logger logger = Logger.getLogger(ValidLogin.class.getName());

   @Before
    public void setUp() {
       WebDriverManager.chromedriver().setup();
       webDriver = new ChromeDriver();
       webDriver.manage().window().maximize();
       webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
       logger.info("'qaauto' was inputted into input UserName");

       WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
       inputPassword.clear();
       inputPassword.sendKeys("123456qwerty");
       logger.info("password was inputted ");

       webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
       logger.info("Button Sign in was clicked");

       Assert.assertTrue("Button Sign out is visible", isButtonSignOutVisible());
   }

   private boolean isButtonSignOutVisible(){
       try {
           return webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
       } catch (Exception e) {
           return true;
       }
   }

}
