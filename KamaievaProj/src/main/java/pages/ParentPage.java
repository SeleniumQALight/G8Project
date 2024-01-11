package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

//Parent class for all pages
abstract public class ParentPage extends CommonActionsWithElements {
    final String baseUrl = "https://aqa-complexapp.onrender.com";

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    //method for getting related url
    abstract protected String getRelatedUrl();

    //method for checking if we are on the page
    protected void checkCurrentUrl() {
        Assert.assertEquals("Invalid page"
                , baseUrl + getRelatedUrl()
                , webDriver.getCurrentUrl()
        );
    }

    //method for checking if we are on the page by pattern
    //https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
    // regex for 64d21e84903640003414c338
    // [a-zA-Z0-9]{24}
    //https://aqa-complexapp.onrender.com/post/[a-zA-Z0-9]
    protected void checkCurrentUrlWithPattern() {
        Assert.assertTrue("Invalid page \n"
                        + "Expected: " + baseUrl + getRelatedUrl() + "\n"
                        + "Actual: " + webDriver.getCurrentUrl()
                , webDriver.getCurrentUrl().matches(baseUrl + getRelatedUrl()));
    }
}
