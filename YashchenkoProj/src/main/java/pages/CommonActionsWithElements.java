package pages;

import libs.ConfigProvider;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait05, webDriverWait15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // initialize all elements from this class by FindBy
        webDriverWait05 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_DEFAULT_WAIT()));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
    }

    protected void enterTextIntoInput(WebElement input, String text) {
        try {
            input.clear();
            input.sendKeys(text);
            logger.info(text + " was inputted into input: " + getElementName(input));
        } catch (Exception e) {
            printErrorAndStopTest(input, e);
        }
    }

    private void printErrorAndStopTest(WebElement input, Exception e) {
        logger.error("Can not work with element: " + getElementName(input) + " Exception: " + e);
        Assert.fail("Can not work with element: " + getElementName(input) + " Exception: " + e);
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
            webDriverWait05.until(ExpectedConditions.elementToBeClickable(element));
            String elementName = getElementName(element);
            element.click();
            logger.info("Element was clicked: " + elementName);
        } catch (Exception e) {
            printErrorAndStopTest(element, e);
        }
    }

    protected void clickOnElement(String locator){
        try {
            clickOnElement(webDriver.findElement(By.xpath(locator)));
        } catch (Exception e) {
            logger.error("Can not work with element: " + locator + " Exception: " + e);
            Assert.fail("Can not work with element: " + locator + " Exception: " + e);
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
            printErrorAndStopTest(dropDown, e);
        }
    }

    // Select value in dropdown
    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in dropdown: " + getElementName(dropDown));
        } catch (Exception e) {
            printErrorAndStopTest(dropDown, e);
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
            logger.error("Can not get text from element: " + getElementName(element) + " Exception: " + e);
            Assert.fail("Can not get text from element: " + getElementName(element) + " Exception: " + e);
        }
    }
    protected void checkCheckbox(WebElement checkbox) {
        if (!checkbox.isSelected()) {
            checkbox.click();
            logger.info("Checkbox is selected");
        } else {
            logger.info("Checkbox is already selected");
        }
    }

    protected void uncheckCheckbox(WebElement checkbox) {
        if (checkbox.isSelected()) {
            checkbox.click();
            logger.info("Checkbox is unselected");
        } else {
            logger.info("Checkbox is already unselected");
        }
    }

}
