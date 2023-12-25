package BaseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.PageProvider;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

//parent class for all tests
public class BaseTest {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    protected PageProvider pageProvider;

    //will be executed before each test (open browser)
   @Before
    public void setup() {
        WebDriverManager.chromedriver().setup(); // download driver
        webDriver = new ChromeDriver();//create browser
        webDriver.manage().window().maximize();//maximize window
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));//wait 5 sec
        logger.info("Browser was opened");
        pageProvider = new PageProvider(webDriver);//start pageProvider
    }

    @After
    public void tearDown() {
        webDriver.quit();
        logger.info("Browser was closed");
    }

}
