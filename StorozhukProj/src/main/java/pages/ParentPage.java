package pages;

import libs.ConfigProvider;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;


//everything that is common for all pages
abstract public class ParentPage extends CommonActionsWithElements {
    final String baseUrl = ConfigProvider.configProperties.base_url();




    // constructor
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    //метод для отримання частиги url
    abstract protected String getRelativeUrl();

    //метод для перевірки чи ми на потрібній сторінці
    protected void checkCurrentUrl() {
        Assert.assertEquals("Invalid page"
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl()
        );

    }

    //метод для перевірки чи вілкріта потрібній сторінці пр патерну
    protected void checkUrlWithPattern() {
        Assert.assertTrue("Invalid page \n"
                        + "Expected result: " + baseUrl + getRelativeUrl() + "\n"
                        + "Actual result: " + webDriver.getCurrentUrl()
                , webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl())
        );
    }
}
