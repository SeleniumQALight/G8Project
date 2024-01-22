package pages;

//всі загальні методи для всіх сторінок
import libs.ConfigProvider;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

abstract public class ParentPage extends CommonActionsWithElements{
    final String baseUrl = ConfigProvider.configProperties.base_url();
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    //method for checking if we are on the page

    abstract protected String getRelativUrl();
    protected void checkUrl() {
        //TODO
        Assert.assertEquals("Invalid page"
                ,baseUrl + getRelativUrl(),
                webDriver.getCurrentUrl());
    }
    protected void checkUrlWithPattern() {
        Assert.assertTrue("Invalid page \n" +
                        "Expected: " + baseUrl + getRelativUrl() +"\n" +
                        "Actual: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativUrl()));
    }
}
