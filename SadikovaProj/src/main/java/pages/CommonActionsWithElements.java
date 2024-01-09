package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // инициализирует все элементы на странице отмеченные аннотацией @FindBy
    }

    /**
     * Actions
     */
    private String getElementName(WebElement webElement) {
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return "";
        }
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

    protected void clickOnElement(WebElement element) {
        try {
            String elementName = getElementName(element);
            element.click();
            logger.info("Element was clicked " + elementName);
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    public void goToWebPage(String url) {
        try {
            webDriver.get(url);
            logger.info("Page was opened: " + url);
        } catch (Exception e) {
            logger.error("Can not open page: " + url);
            Assert.fail("Can not open page");
        }
    }

    /**
     * Checkers
     */
    protected boolean isElementDisplayed(WebElement element) {
        try {
            boolean state = element.isDisplayed();
            logger.info("Element " + getElementName(element) + " is displayed -> " + state);
            return state;
        } catch (Exception e) {
            logger.error("Element is displayed -> " + false);
            return false;
        }
    }

    protected void selectCheckbox(WebElement element) {
        try {
            if (!element.isSelected()) {
                element.click();
                logger.info("Element " + getElementName(element) + " was clicked. Checkbox is selected");
            } else {
                logger.info("Element " + getElementName(element) + " is selected");
            }

        } catch (Exception e) {
            logger.error("Error: " + e);
        }
    }

    protected void unselectCheckbox(WebElement element) {
        try {
            if (element.isSelected()) {
                element.click();
                logger.info("Element " + getElementName(element) + " was clicked. Checkbox is unselected");
            } else {
                logger.info("Element wasn't " + getElementName(element) + " clicked. Checkbox is unselected");
            }

        } catch (Exception e) {
            logger.error("Error: " + e);
        }
    }

    protected void checkElementIsNotDisplayed(WebElement element) {
        Assert.assertFalse("Element is not visible", isElementDisplayed(element));
    }

    protected void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is visible", isElementDisplayed(webElement));
    }

    protected void assertUrl(String exepectedUrl) {
        String actualUrl = webDriver.getCurrentUrl();
        logger.info("Current url is: " + actualUrl);
        Assert.assertEquals(actualUrl, exepectedUrl);
        logger.info("Url is correct: " + exepectedUrl);
    }


    // select Text in dropDown
    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in DropDown" + getElementName(dropDown));
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    // select Value in dropDown
    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in DropDown" + getElementName(dropDown));
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    public void checkTextInElement(WebElement element, String expectedText) {
        String actualText = element.getText();
        Assert.assertEquals(actualText, expectedText);
        logger.info("Text visible: " + expectedText);

    }

    public void selectCheckbox(WebElement element, String exectedState) {
        if (exectedState.equals("check")) {
            selectCheckbox(element);
        } else {
            if (exectedState.equals("uncheck")) {
                unselectCheckbox(element);

            }
        }


    }
}
