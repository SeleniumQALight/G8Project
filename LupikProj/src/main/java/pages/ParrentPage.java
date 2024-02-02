package pages;

import libs.ConfigProvider;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


import java.util.ArrayList;

//все загальне для сторінок
abstract public class ParrentPage extends CommonActionsWithElements{
    String env = System.getProperty("env", "aqa");
    final String baseUrl = ConfigProvider.configProperties.base_url().replace("[env]", env);
    public ParrentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract protected String getRelativeUrl();
    protected void checkUrl(){
        Assert.assertEquals("Invalid page "
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl()
        );
    }
    // метод для перевірки чи відкрита потрібна сторінка
    protected void checkUrlWithPattern(){
        Assert.assertTrue("Invalid page \n"
                        + "Expected result: " + baseUrl + getRelativeUrl() + "\n"
                        + "Actual result: " + webDriver.getCurrentUrl()
                , webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl())
        );
    }

    public void openLinkInNewTab() {
        try {
            ((JavascriptExecutor) webDriver).executeScript("window.open();");
            logger.info("Link was opened in new tab");
        } catch (Exception e) {
            logger.error("Can not open link in new tab");
            Assert.fail("Can not open link in new tab");
        }
    }

    public void switchToTab(int i) {
        try {
            webDriver.switchTo().window(new ArrayList<>(webDriver.getWindowHandles()).get(i));
            logger.info("Switched to tab #" + i);
        } catch (Exception e) {
            logger.error("Can not switch to tab #" + i);
            Assert.fail("Can not switch to tab #" + i);
        }


    }

    public void closeTab(int i) {

        try {
            webDriver.switchTo().window(new ArrayList<>(webDriver.getWindowHandles()).get(i));
            webDriver.close();
            logger.info("Tab #" + i + " was closed");
        } catch (Exception e) {
            logger.error("Can not close tab #" + i);
            Assert.fail("Can not close tab #" + i);
        }
    }

    public void refreshPage() {
        try {
            webDriver.navigate().refresh();
            logger.info("Page was refreshed");
        } catch (Exception e) {
            logger.error("Can not refresh page");
            Assert.fail("Can not refresh page");
        }
    }
}
