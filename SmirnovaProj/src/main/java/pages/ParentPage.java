package pages;

import libs.ConfigProvider;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

// все загальні методи і елементи сторінок будуть тут
abstract public class ParentPage extends CommonActionsWithElements {
    String env = System.getProperty("env", "aqa");
    final String baseUrl =
            ConfigProvider.configProperties.base_url().replace("[env]", env);

    //конструктор
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract protected String getRelativeUrl();

    //метод для перевірки відкриття сторінки
    protected void checkUrl() {
        Assert.assertEquals("Invalid page URL",
                baseUrl + getRelativeUrl(),
                webDriver.getCurrentUrl()
        );
    }

    //метод для перевірки відкриття сторінки з патерном
    protected void checkUrlWithPattern() {
        Assert.assertTrue("Invalid page \n" +
                        "Expected result: " + baseUrl + getRelativeUrl() + "\n" +
                        "Actual result: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl())
        );
    }

}
