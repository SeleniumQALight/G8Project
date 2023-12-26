package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver)
    {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    protected void enterTextIntoInput (WebElement element, String text) {
        try {
            element.clear();
            element.sendKeys(text);
            logger.info(text + " was inputted into input "+getElementName(element));
        } catch (Exception e) {
            logger.error("Can not work with element ");
            Assert.fail("Can not work with element");
        }
    }

    private String getElementName(WebElement webElement) {
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return "";
        }
    }

    protected void clickOnElement (WebElement element) {
        try {
            String elementName = getElementName(element);
            element.click();
            logger.info("Element was clicked "+ elementName);
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    protected boolean isElementDisplayed (WebElement element) {
        try {
            boolean state = element.isDisplayed();
            logger.info("Element "+ getElementName(element)+ " is displayed -> " + state);
            return state;
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
            return false;
        }
    }
}
