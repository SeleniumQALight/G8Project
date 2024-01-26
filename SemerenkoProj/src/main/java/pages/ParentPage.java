package pages;

import libs.ConfigProvider;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

// все загальне для всіх сторінок
abstract public class ParentPage extends CommonActionsWithElements {
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }
String env = System.getProperty("env", "aqa");
    final String baseUrl =
            ConfigProvider.configProperties.base_url().replace("[env]", env);

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
