package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

// все загальне для сторінок
abstract public class ParentPage extends CommonActionsWithElements {
    final String baseUrl = "https://aqa-complexapp.onrender.com";

    // буде викликатися в кожному класі
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }
    // метод для отримання частини url
    abstract protected String getRelativeUrl();

    // метод для перевірки чи відкрита потрібна сторінка
    protected void checkUrl() {
        Assert.assertEquals("Invalid page"
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl()
        );
    }

    // метод для перевірки чи відкрита потрібна сторінка по патерну
    //https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
    // regex for 64d21e84903640003414c338
    // [a-zA-Z0-9]{24}
    //https://aqa-complexapp.onrender.com/post/[a-zA-Z0-9]
    protected void checkUrlWithPattern() {
        Assert.assertTrue("Invalid page \n"
                        + "Expected result: " + baseUrl + getRelativeUrl() + "\n"
                        + "Actual result: " + webDriver.getCurrentUrl()
                , webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl())
        );
} }
