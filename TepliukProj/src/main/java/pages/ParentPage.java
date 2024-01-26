package pages;

import libs.ConfigProperties;
import libs.ConfigProvider;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import static libs.ConfigProvider.configProperties;

// все загальне для сторінок
abstract public class ParentPage extends CommonActionsWithElements {
    String env = System.getProperty("env", "aqa"); // отримуємо з консолі змінну env
    final String baseUrl = ConfigProvider.configProperties.base_url().replace("[env]", env); // отримуємо url з конфіга

    // конструктор
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }


    // метод для отримання частини url
    abstract protected String getRelativeUrl();


    // методи для перевірки чи відкрита потрібна сторінка
    protected void checkUrl() {
        Assert.assertEquals("Invalid page", baseUrl + getRelativeUrl(), webDriver.getCurrentUrl());
    }

    // метод для перевірки чи відкрита потрібна сторінка по патерну
    protected void checkUrlWithPattern() {
        Assert.assertTrue("Invalid page \n"
                + "Expected result: " + baseUrl + getRelativeUrl() + "\n"
                + "Actual result: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
    }

}
