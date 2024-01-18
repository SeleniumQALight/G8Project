package pages;

import libs.ConfigProvider;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

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

    public void openNewTab() {
        ((org.openqa.selenium.JavascriptExecutor) webDriver).executeScript("window.open()");
        logger.info("New tab was opened");
    }

    public void refreshPage() {
        webDriver.navigate().refresh();
    }

    public void closeTab() {
        webDriver.close();
        logger.info("Tab was closed");
    }

    public void switchToTabByIndex(int tabIndex) {
        List<String> allWindows = new ArrayList<>(webDriver.getWindowHandles());
        if (tabIndex >= 0 && tabIndex < allWindows.size()) {
            webDriver.switchTo().window(allWindows.get(tabIndex));
            logger.info("Switched to tab with index " + tabIndex);
        } else {
            logger.error("Invalid tab index");
        }
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
