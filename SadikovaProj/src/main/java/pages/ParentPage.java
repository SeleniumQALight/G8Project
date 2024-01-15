package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.NoSuchElementException;

// Все общее для всех страниц
abstract public class ParentPage extends CommonActionsWithElements {
   final String baseUrl = "https://aqa-complexapp.onrender.com";

    // Конструктор
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract protected String getRelativeUrl();

    protected void assertUrl() {
        Assert.assertEquals("Invalid url " + webDriver.getCurrentUrl(), baseUrl + getRelativeUrl(), webDriver.getCurrentUrl());
        logger.info("Url is correct");
    }
    // Метод для проверки, что открыта нужная страница по паттерну
    //https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
    // regex for 64d21e84903640003414c338
    // [a-zA-Z0-9]{24}
    //https://aqa-complexapp.onrender.com/post/[a-zA-Z0-9]
    protected void checkUrlWithPatternUrl() {
            Assert.assertTrue("Invalid page \n"
                            + "Expected result: " + baseUrl + getRelativeUrl() + "\n"
                            + "Actual result: " + webDriver.getCurrentUrl()
                    , webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
        }

    public void switchTab(int tab) {
        try {
            ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
            webDriver.switchTo().window(tabs.get(tab));
            logger.info("Tab is switch and opened: " + tab);
        }
        catch (Exception e) {
            webDriver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
            logger.info("Was used hot keys " + tab);

        }
    }

    public void openWindow() throws InterruptedException {
        ((JavascriptExecutor) webDriver).executeScript("window.open();");
        logger.info("New window is open");
    }

    public void closeTab(int tabNumber) {
        try {
            ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
            webDriver.switchTo().window(tabs.get(tabNumber));
            webDriver.close();
            logger.info("Page is closed");
        } catch (Exception e) {
            webDriver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
            logger.info("Was used hot key. Page is closed");
        }
    }

    public void refreshPage() {
        webDriver.navigate().refresh();
        logger.info("Page is refreshed");
    }
}
