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


public class LoginTestAllInOneClass {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());

    //Буде виконано перед кожним тестом


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
        logger.info("'qaauto' was inputted into input UserName");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("password was inputted ");

        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("Button Sign in was clicked");

        Assert.assertTrue("Button Sign out is not visible",isButtonSignOutVisible());


    }
    @Test
    public void invalidLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("invalid_user");
        logger.info("Invalid user was inputted into input UserName");

        WebElement inputPassword = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("invalid_password");
        logger.info("invalid password was inputted ");


        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("Button Sign in was clicked");

        Assert.assertFalse("Button Sign out is visible", isButtonSignOutVisible());
        Assert.assertTrue("Button Sign in is not visible", isButtonSignInVisible());
        Assert.assertTrue("Alert Danger is not visible", isAlertDangerVisible());
    }

    private boolean isButtonSignOutVisible() { // method for checking that button Sign Out is not visible
        try {
            return webDriver.findElement(By.xpath("//button[contains(text(),'Sign Out')]")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

//class work method for checking that button Sign Out is visible
//    private boolean isButtonSignOutVisible() {
//        try {
//            boolean state = webDriver.findElement(By.xpath("//button[contains(text(),'Sign Out')]")).isDisplayed();
//            logger.info(state + " is button visible");
//            return state;
//        } catch (Exception e){
//            logger.info("Element is not displayed");
//            return false;
//        }
//    }


    private boolean isButtonSignInVisible() { // method for checking that button Sign In is visible
        try {
            return webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    private boolean isAlertDangerVisible() { // method for checking that Alert Danger is visible
        try {
            return webDriver.findElement(By.xpath("//div[@class=\"alert alert-danger text-center\" and contains(text(),\"Invalid username/password.\")]"))
                    .isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

}
