package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import static libs.ConfigProvider.configProperties;

// Все общее для всех страниц
abstract public class ParentPage extends CommonActionsWithElements {
    String env = System.getProperty("env", "aqa");

    final String baseUrl = configProperties.base_url().replace("[env]", env);
    final String BASE_PB_URL = "https://privatbank.ua/";

    // Конструктор
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    // Метод для получения части URL
    abstract protected String getRelativeUrl();

    // Метод для проверки, что открыта нужная страница
    protected void checkUrl() {
        Assert.assertEquals("Invalid page"
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl());
    }

    // Метод для проверки, что открыта нужная страница по паттерну
    //https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
    // regex for 64d21e84903640003414c338
    // [a-zA-Z0-9]{24}
    //https://aqa-complexapp.onrender.com/post/[a-zA-Z0-9]
    protected void checkUrlWithPattern() {
        Assert.assertTrue("Invalid page \n"
                        + "Expected result: " + baseUrl + getRelativeUrl() + "\n"
                        + "Actual result: " + webDriver.getCurrentUrl()
                , webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
    }
}
