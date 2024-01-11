package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

// все загальне для всіх сторінок
abstract public class ParentPage extends CommonActionsWithElements {
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    final String baseUrl = "https://aqa-complexapp.onrender.com";

    abstract protected String getRelativeUrl();

    //метод для перевірки чи ми на потрібній сторінці

    protected void checkUrl() {
        Assert.assertEquals("Invalid page"
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl());
    }

    //метод для перевірки чи ми на потрібній сторінці по патерну
    protected void checkUrlWithPattern() {
        Assert.assertTrue("Invalid page \n"
                        + "Expected result: " + baseUrl + getRelativeUrl()+"\n"
                        + "Actual result: " + webDriver.getCurrentUrl()
                , webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
    }
}
