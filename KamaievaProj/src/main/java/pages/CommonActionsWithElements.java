package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);//initialization of elements on page which discribed in this class
    }

    protected void enterTextIntoInput(WebElement input, String text) {
        try {
            input.clear();
            input.sendKeys(text);
            logger.info(text + " was inputted into input" + getElementName(input));
        } catch (Exception e) {
            logger.error("Can not work with element");
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

    protected void clickOnElement(WebElement element) {
        try {
            String elementName = getElementName(element);
            element.click();
            logger.info("Element was clicked" + elementName);
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            boolean state = element.isDisplayed();
            String elementText = element.getText();
            String logMessage;
            if (!elementText.isEmpty()) {
                logMessage = "Element '" + elementText + "' is displayed -> " + state;
            } else {
                logMessage = "Element '" + getElementName(element) + "' is displayed -> " + state;
            }
            logger.info(logMessage);
            return state;
        } catch (Exception e) {
            logger.error("Can not work with element");
            return false;
        }
    }

    //select Text in dropdown
    protected void selectTextInDropdown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in dropdown" + getElementName(dropDown));
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    //select Value in dropdown
    protected void selectValueInDropdown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in dropdown" + getElementName(dropDown));
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    protected void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is not visible", isElementDisplayed(webElement));
    }

    //check text in element
    protected void checkTextInElement(WebElement element, String expectedText) {
        try {
            String textFromElement = element.getText();
            Assert.assertEquals("Text in element not matched", expectedText, textFromElement);
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    protected void selectCheckbox(WebElement checkbox, String elementName) {
        try {
            if (!checkbox.isSelected()) {
                checkbox.click();
                logger.info(elementName + " was selected");
            } else {
                logger.info(elementName + " is already selected.");
            }
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    protected void unselectCheckbox(WebElement checkbox, String elementName) {
        try {
            if (checkbox.isSelected()) {
                checkbox.click();
                logger.info(elementName + " was unselected");
            } else {
                logger.info(elementName + " is already unselected.");
            }
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    protected void setCheckboxState(WebElement checkbox, String elementName, String state) {
        try {
            if (state.toLowerCase().equals("check")) {
                selectCheckbox(checkbox, elementName);
            } else if (state.toLowerCase().equals("uncheck")) {
                unselectCheckbox(checkbox, elementName);
            }
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }
}
