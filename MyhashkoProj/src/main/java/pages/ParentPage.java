package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;


// все загальне для сторінок
public abstract class ParentPage extends CommonActionsWhithElements{
    final String baseUrl = "https://aqa-complexapp.onrender.com";

    // конструктор
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    // метод для отримання частини URL
    abstract protected String getRelativeUrl();

    // метод для перевірки чи відкрита потрібна сторінка
    protected void checkUrl() {
        Assert.assertEquals("Invalid page"
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl()
        );
    }

    // метод для перевірки чи відкрита потрібна сторінка по патерну
    // https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
    // regex for 64d21e84903640003414c338
    // [a-zA-Z0-9]{24}
    // https://aqa-complexapp.onrender.com/post/[a-zA-Z0-9]
    protected void checkUrlWithPattern() {
        Assert.assertTrue("Invalid page /n"
                        + "Expected: " + baseUrl + getRelativeUrl() + "\n"
                        + "Actual result: " + webDriver.getCurrentUrl()
                , webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl())
        );
    }

    public void switchToTab(int tab) {
        try {
            ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
            webDriver.switchTo().window(tabs.get(tab));
        } catch (Exception e) {
            webDriver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
        }
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

    public void openNewTabInBrowser() {
        ((JavascriptExecutor) webDriver).executeScript("window.open();");
    }
}
