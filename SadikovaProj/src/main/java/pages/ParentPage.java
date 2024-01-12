package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

// Все общее для всех страниц
abstract public class ParentPage extends CommonActionsWithElements {
   final String baseUrl = "https://aqa-complexapp.onrender.com";
   protected String homeUrl;

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
        } catch (Exception e) {
            webDriver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
        }
    }

    public void openWindow() throws InterruptedException {
        ((JavascriptExecutor) webDriver).executeScript("window.open();");
    }

    public void closeTab(int tabNumber) {
        try {
            ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
            webDriver.switchTo().window(tabs.get(tabNumber));
            webDriver.close();
        } catch (Exception e) {
            webDriver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
        }
    }

    public void refreshPage() {
        webDriver.navigate().refresh();
    }
}
