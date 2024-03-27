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

    public Privat24HomePage getPrivat24HomePage()
    {
        return new Privat24HomePage(webDriver);
    }

    public LoginPage loginPage() {
        return new LoginPage(webDriver);
    }

    public HomePage homePage() {
        return new HomePage(webDriver);
    }

    public PostPage getPostPage() {return new PostPage(webDriver);
    }
    public MyProfilePage getMyProfilePage() {return new MyProfilePage(webDriver);
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

    public void switchToTab(String mainPageHandler) {
        try {
            webDriver.switchTo().window(mainPageHandler);
            logger.info("Switch to TAB ");
        } catch (Exception e) {
            logger.error("Can not switch to TAB");
            Assert.fail("Can not switch to TAB");
        }
    }

    public void closeNewTab() {
        try {
            webDriver.close();
            logger.info("New TAB was closed ");
        } catch (Exception e) {
            logger.error("Can not close new TAB");
            Assert.fail("Can not close new TAB");
        }
    }

    public void refreshPage() { webDriver.navigate().refresh();
    }
}
