package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // initialize all elements from this class by FindBy
    }

    protected void enterTextIntoInput(WebElement input, String text) {
        try {
            input.clear();
            input.sendKeys(text);
            logger.info(text + " was inputted into input: " + getElementName(input));
        } catch (Exception e) {
            logger.error("Can not work with element: " + getElementName(input));
            Assert.fail("Can not work with element: " + getElementName(input));
        }
    }

    private String getElementName(WebElement webElement) {
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return "";
        }
    }

    protected void clickOnElement(WebElement element) {
        try {
            String elementName = getElementName(element);
            element.click();
            logger.info("Element was clicked: " + elementName);
        } catch (Exception e) {
            logger.error("Can not work with element: " + getElementName(element));
            Assert.fail("Can not work with element: " + getElementName(element));
        }
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            boolean state = element.isDisplayed();
            logger.info("Is element "+ getElementName(element) + " displayed -> " + state);
            return state;
        } catch (Exception e) {
            logger.info("Element" + getElementName(element) + "is not displayed");
            return false;
        }
    }
}
