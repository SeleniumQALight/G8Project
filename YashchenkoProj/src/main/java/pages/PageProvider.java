package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class PageProvider {
    private WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());

    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public LoginPage getLoginPage() {
        return new LoginPage(webDriver);
    }

    public HomePage homePage() {
        return new HomePage(webDriver);
    }

    public PostPage getPostPage() {
        return new PostPage(webDriver);
    }

    // Method for opening the new tab
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
}
