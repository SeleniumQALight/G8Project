package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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

    // Select text in dropdown
    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in dropdown: " + getElementName(dropDown));
        } catch (Exception e) {
            logger.error("Can not work with element: " + getElementName(dropDown));
            Assert.fail("Can not work with element: " + getElementName(dropDown));
        }
    }

    // Select value in dropdown
    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in dropdown: " + getElementName(dropDown));
        } catch (Exception e) {
            logger.error("Can not work with element: " + getElementName(dropDown));
            Assert.fail("Can not work with element: " + getElementName(dropDown));
        }
    }

    protected void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is not visible", isElementDisplayed(webElement));
    }

    protected void checkIsElementInvisible(WebElement webElement) {
        Assert.assertFalse("Element is visible", isElementDisplayed(webElement));
    }

    public void checkTextInElement(WebElement element, String expectedText) {
        try {
            String textFromElement = element.getText();
            Assert.assertEquals("Text in element not matched", expectedText, textFromElement);
        } catch (Exception e) {
            logger.error("Can not get text from element: " + getElementName(element));
            Assert.fail("Can not get text from element: " + getElementName(element));
        }
    }

}
