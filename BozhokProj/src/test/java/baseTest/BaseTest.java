package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.PageProvider;

import java.time.Duration;

public class BaseTest {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    protected PageProvider pageProvider;

    // Will be executed before each test
    @Before
    public void setup(){
        logger.info("------" + testName.getMethodName() + " was started ------");
        WebDriverManager.chromedriver().setup(); // Download driver
        webDriver = new ChromeDriver(); // Create driver
        webDriver.manage().window().maximize(); // Maximize window
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // Wait 5 seconds
        logger.info("Browser was opened");
        pageProvider = new PageProvider(webDriver);
    }

    @After
    public void tearDown(){
        webDriver.quit();
        logger.info("Browser was closed");
        logger.info("------" + testName.getMethodName() + " was ended ------");
    }

    @Rule
    public TestName testName = new TestName();
}
