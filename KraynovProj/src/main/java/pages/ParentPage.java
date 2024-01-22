package pages;

import libs.ConfigProvider;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

// всё общее для всех страниц
abstract public class ParentPage extends CommonActionsWithElements {
    final String baseUrl = ConfigProvider.configProperties.base_url();

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    // метод для получения части url
    abstract protected String getRelativeUrl();

    // метод для проверки открыта ли необходимая страница
    protected void checkUrl() {
        Assert.assertEquals("Invalid page "
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl()
        );
    }

    // метод для проверки открты ли страница по паттерну
    //https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
    // regex for 64d21e84903640003414c338
    // [a-zA-Z0-9]{24}
    //https://aqa-complexapp.onrender.com/post/[a-zA-Z0-9]
    protected void checkUrlWithPattern() {
        Assert.assertTrue("Invalid page "
                + "Expected result: \n" + baseUrl + getRelativeUrl() + "\n"
                + "Actual result: " + webDriver.getCurrentUrl()
                , webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl())
        );
    }
}
