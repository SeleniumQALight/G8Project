package BaseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.PageProvider;

import java.time.Duration;

//Parent class for all tests
public class BaseTest {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    protected PageProvider pageProvider;

    //Will be executed before each test
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup(); // download driver
        webDriver = new ChromeDriver(); // create driver
        webDriver.manage().window().maximize(); // open browser in full screen
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // wait 5 seconds
        logger.info("Browser was opened");
        pageProvider = new PageProvider(webDriver);
    }

    @After
    public void tearDown() {
        webDriver.quit();
        logger.info("Browser was closed");
    }

}
