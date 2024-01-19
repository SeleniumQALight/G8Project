package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.log4j.Logger;
import pages.PageProvider;

import java.time.Duration;

// Parent class for all tests
public class BaseTest {
    WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected PageProvider pageProvider;

    // Will be executed before each test
    @Before
    public void setup(){
        logger.info("----- " + testName.getMethodName() + " was started -----");
        WebDriverManager.chromedriver().setup(); // download driver
        webDriver = new ChromeDriver(); // create driver
        webDriver.manage().window().maximize(); // maximize window
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logger.info("Browser was opened");
        pageProvider = new PageProvider(webDriver);
    }

    @After
    public void tearDown(){
        webDriver.quit();
        logger.info("Browser was closed");
        logger.info("----- " + testName.getMethodName() + " was finished -----");
    }

    @Rule
    public TestName testName = new TestName();

}
