package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

//все загальне для сторінок
abstract public class ParrentPage extends CommonActionsWithElements{
    final String baseUrl = "https://aqa-complexapp.onrender.com";
    public ParrentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract protected String getRelativeUrl();
    protected void checkUrl(){
        Assert.assertEquals("Invalid page "
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl()
        );
    }
// метод для перевірки чи відкрита потрібна сторінка
    protected void checkUrlWithPattern(){
        Assert.assertTrue("Invalid page \n"
                        + "Expected result: " + baseUrl + getRelativeUrl() + "\n"
                        + "Actual result: " + webDriver.getCurrentUrl()
                , webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl())
        );
    }

}
