package pages;

import libs.ConfigProvider;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

// All common methods for all pages
abstract public class ParentPage extends CommonActionsWithElements {
    final String baseUrl = ConfigProvider.configProperties.base_url();
    // Constructor
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    // Method for getting url part
    abstract protected String getRelativeUrl();

    // Method for checking if page is opened
    protected void checkCurrentUrl() {
        Assert.assertEquals("Invalid page",
                baseUrl + getRelativeUrl(),
                webDriver.getCurrentUrl()
        );
    }

    //Method for checking if needed page is opened with pattern
    // Example: https://aqa-complexapp.onrender.com/post/5f3b3d7d9d9d1d0017b6d5f2
    // regex for 5f3b3d7d9d9d1d0017b6d5f2:
    // [a-zA-Z0-9]{24}
    // https://aqa-complexapp.onrender.com/post/[a-zA-Z0-9]{24}
    protected void checkCurrentUrlWithPattern() {
        Assert.assertTrue("Invalid page \n"
                        + "Expected: " + baseUrl + getRelativeUrl() + "\n"
                        + "Actual: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl())
        );
    }
}
