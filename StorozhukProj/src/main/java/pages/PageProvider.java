package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class PageProvider {
    private WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());

    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public LoginPage loginPage() {
        return new LoginPage(webDriver);
    }

    public HomePage homePage() {
        return new HomePage(webDriver);
    }

    public PostPage getPostPage() {
        return new PostPage(webDriver);
    }

    public String openNewTab() {
        try {
            webDriver.switchTo().newWindow(WindowType.TAB);
            logger.info("New TAB was opened ");
            return webDriver.getWindowHandle();
        } catch (Exception e) {
            logger.error("Can not open new TAB");
            Assert.fail("Can not open new TAB");
        }

        return null;
    }

    public void switchToMainTab(String mainPageHandler) {
        webDriver.switchTo().window(mainPageHandler);
    }

    public void closeNewTab() {
        webDriver.close();
    }

    public void refreshPage() { webDriver.navigate().refresh();
    }
}
