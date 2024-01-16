package pages;

import libs.ConfigProvider;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

//все загальне для сторінок
abstract public class ParentPage extends CommonActionsWithElements {

    final String baseUrl = ConfigProvider.configProperties.base_url();

    //все загальне для сторінок
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    // метод для отримання частини URL
    protected abstract String getRelativeUrl();

    // метод для перевірки чи відкрита потрібна сторінка
    protected void checkUrl() {
        Assert.assertEquals("Invalid page"
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl()
        );
    }

    //метод для перевірки чи відкрита потрібна сторінка по патерну
    //https://aqa-complexapp.onrender.com/post/5f3b3d3d9d6f6d0017b1e3f0
    //regex for 5f3b3d3d9d6f6d0017b1e3f0
    //[a-zA-Z0-9]{24}
    //https://aqa-complexapp.onrender.com/post/[a-zA-Z0-9]
    protected void checkUrlWithPattern() {
        Assert.assertTrue("Invalid page \n"
                + "Expected url: " + baseUrl + getRelativeUrl() + "\n"
                + "Actual url: " + webDriver.getCurrentUrl()
                , webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl())
        );
    }

}
