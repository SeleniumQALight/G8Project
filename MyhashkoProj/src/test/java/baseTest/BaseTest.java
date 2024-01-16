package baseTest;

// Parent class for all tests

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.PageProvider;

import java.time.Duration;

public class BaseTest {
    WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected PageProvider pageProvider;

    // Will be executed before each test
    @Before
    public void setUp() {
        logger.info("------ " + testName.getMethodName() + " was started ------");
        WebDriverManager.chromedriver().setup(); // Download and setup ChromeDriver
        webDriver = new ChromeDriver(); // Create driver
        webDriver.manage().window().maximize(); // Maximize window
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // Wait for 10 seconds
        logger.info("Browser opened");
        pageProvider = new PageProvider(webDriver);
    }

    @After
    public void tearDown() {
        webDriver.quit(); // Close browser
        logger.info("Browser closed");
        logger.info("------ " + testName.getMethodName() + " was ended ------");
    }


    @Rule
    public TestName testName = new TestName();
}
