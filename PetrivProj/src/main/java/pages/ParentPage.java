package pages;

import libs.ConfigProvider;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

// all general for pages
abstract public class ParentPage extends CommonActionsWithElements {
    String env = System.getProperty("env", "aqa");
    final String baseUrl = ConfigProvider.configProperties.base_url().replace("[env]", env);
    // constructor
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    // method for getting relative url
    abstract protected String getRelativeUrl();

    // метод для перевірки чи відкрита потрібна сторінка
    protected void checkUrl() {
        Assert.assertEquals("Invalid page ", baseUrl + getRelativeUrl(), webDriver.getCurrentUrl());
    }

    // метод для перевірки чи відкрита потрібна сторінка по патерну
    // https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
    // regex for 64d21e84903640003414c338
    // [a-zA-Z0-9]{24}
    // https://aqa-complexapp.onrender.com/post/[a-zA-Z0-9]
    public void checkUrlWithPattern() {
        Assert.assertTrue("Invalid page \n"
                + "Expected result: " + baseUrl + getRelativeUrl() + "\n"
                + "Actual result:" + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl())
        );
    }
}
