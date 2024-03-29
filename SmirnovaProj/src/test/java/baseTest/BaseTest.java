package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import pages.PageProvider;
import pages.ParentPage;

import java.time.Duration;

public class BaseTest {
    WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected PageProvider pageProvider;
     ParentPage parentPage;
    //Буде виконуватися перед кожним тестом
    @Before
    public void setUp() {
        logger.info("----Start test----" + testName.getMethodName());//логуємо, що починається тест
//        WebDriverManager.chromedriver().setup();//встановлюємо драйвер
//        webDriver = new ChromeDriver();//створюємо екземпляр драйвера
        webDriver = initDriver();
        webDriver.manage().window().maximize();//розгортаємо вікно на весь екран
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));//задаємо неявне очікування
        logger.info("Browser was opened");//логуємо, що браузер відкрито
        pageProvider = new PageProvider(webDriver);
    }

    @After
    public void tearDown() {
        webDriver.quit();
        logger.info("Browser was closed");
        logger.info("----End test----" + testName.getMethodName());
    }

    @Rule
    public TestName testName = new TestName();
    private WebDriver initDriver(){
        String browser = System.getProperty("browser");
        if ((browser == null) || ("chrome".equals(browser.toLowerCase()))){ // default browser -Dbrowser=chrome
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        } else if ("firefox".equals(browser.toLowerCase())){ // -Dbrowser=firefox
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        } else if ("ie".equals(browser.toLowerCase())){
            WebDriverManager.iedriver().setup(); //zoom 100%
            webDriver = new InternetExplorerDriver(); //security level - Medium
        } else if ("safari".equalsIgnoreCase(browser)) {
            WebDriverManager.safaridriver().setup();
            webDriver = new SafariDriver();
        } else if ("edge".equalsIgnoreCase(browser)) {
            WebDriverManager.edgedriver().setup();
            webDriver = new EdgeDriver();
        }  else {
            throw new IllegalArgumentException("Browser " + browser + " is not supported");
        }
        return webDriver;
    }
}
